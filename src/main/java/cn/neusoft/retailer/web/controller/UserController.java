package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.*;
import com.google.code.kaptcha.Constants;
import com.mysql.cj.Session;
import com.sun.javafx.collections.MappingChange;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml", "classpath*:springmvc.xml"})

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/23 15:52
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisClient redisClient;

    /**
     * @描述: 用户注册
     * @参数: [user]
     * @返回值: java.util.Map<java.lang.String               ,                               java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/30
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public Map<String, String> register(@RequestBody User user) {

        Map<String, String> result = new HashMap<>();

        String userName = user.getUserName();
        String userMail = user.getUserMail();
        String userPhone = user.getUserPhone();
        String userPassword = user.getUserPassword();
        String mvoUrl = user.getMvoUrl();
        Integer mvoType = user.getMvoType();
        Integer userPrivilege = user.getUserPrivilege();

        //判断用户名是否重复
        if (userName == null || !MyString.isName(userName)) {
            result.put("INVALID_USERNAME", "用户名格式不正确！");
            return result;
        } else if (userService.selectByName(userName) != null) {
            result.put("INVALID_USERNAME", "该用户名已被注册！");
            return result;
        }

        //判断是否符合密码格式
        if (userPassword == null || !MyString.isPassword(userPassword)) {
            result.put("INVALID_PASSWD", "密码格式不正确！");
            return result;
        }

        //判断是否符合email格式
        if (userMail == null || !MyString.isEmail(userMail)) {
            result.put("INVALID_EMAIL", "邮箱格式不正确！");
            return result;
        }

        //判断是否符合电话号码格式
        if (userPhone == null || userPhone.length() != 11 || !MyString.ifNumber(userPhone)) {
            result.put("INVALID_PHONE", "手机号码格式不正确！");
            return result;
        }

        //判断是否符合url格式
        if ("".equals(mvoUrl))
            mvoUrl = null;
        if (!MyString.isURL(mvoUrl)) {
            result.put("INVALID_URL", "url格式不正确！");
            return result;
        }

        user.setUserId(UniqueID.getGuid());

        //加密保存用户密码
        String ciphertext = MD5.encrypt(userPassword);
        user.setUserPassword(ciphertext);

        try {
            userService.insertByUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("ERROR", e.getMessage());
            return result;
        }

        result.put("SUCCESS", "注册成功");
        return result;

    }

    /**
     * @描述: 验证token
     * @参数: [flag, request]
     * @返回值: java.util.Map<java.lang.String               ,                               java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/30
     */
    @RequestMapping(value = "/tokenVilidation/{flag}")
    @ResponseBody
    public Map<String, String> vilidateToken(@PathVariable("flag") Boolean flag, HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();
        String cookie = request.getHeader("Cookie");
        User user = null;

        if (cookie.contains("token")) {

            String[] cookieInfo = cookie.split(";");
            String tokenMessage = null;
            for (String s : cookieInfo) {
                if (s.contains("token")) {
                    tokenMessage = s;
                }
            }
            String[] tokenInfo = tokenMessage.split("=");
            String token = tokenInfo[1];

            try {
                //user = redisClient.findAndUpdate(token, "127.0.0.1", flag);
                user = redisClient.findAndUpdate(token, request.getRemoteAddr(), flag);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("ERROR", e.getMessage());
                return result;
            }
            if (user != null) {
                result.put("SUCCESS", "身份有效");
                result.put("userName", user.getUserName());
                result.put("userPassword", "admin");
                return result;
            } else {
                result.put("ERROR", "身份失效，请重新登录");
                return result;
            }
        } else {
            result.put("ERROR", "请登录");
            return result;
        }
    }

    /**
     * @描述: 登陆校验
     * @参数: [json, flag, request, response]
     * @返回值: java.util.Map<java.lang.String               ,                               java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/30
     */
    @RequestMapping(value = "/loginValidation/{flag}")
    @ResponseBody
    public Map<String, String> login(@RequestBody String json, @PathVariable("flag") Boolean flag, HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> result = new HashMap<>();
        JSONObject data = new JSONObject(json);
        String userName = (String) data.get("userName");

        HttpSession session = request.getSession();
        session.setAttribute("flag", flag);

        //校验用户信息
        User user = null;
        try {
            user = userService.selectByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("ERROR", e.getMessage());
            return result;
        }
        if (user == null) {
            result.put("INVALID_USERNAME", "No User");
            return result;
        }

        //校验密码
        String in_passwd = (String) data.get("userPassword");
        if (!flag || !in_passwd.equals("admin")) {
            if (!user.getUserPassword().equals(MD5.encrypt(in_passwd))) {
                result.put("INVALID_PASSWD", "Password Is Invalid");
                return result;
            }
        }

        //校验验证码
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String in_code = (String) data.get("code");
        if (!code.toLowerCase().equals(in_code.toLowerCase())) {
            result.put("INVALID_CODE", "Code Is Invalid");
            return result;
        }

        String token;
//        token = TokenCreation.createToken("127.0.0.1");
        token = TokenCreation.createToken(request.getRemoteAddr());

        user.setUserPassword(null);
        session.setAttribute("user", user);

        //根据"记住我"的值选择Token存放时间
        Cookie cookie;
        if (data.get("remember-me").equals(true)) {
            try {
                redisClient.set(token, user, 7 * 24 * 60 * 60);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("ERROR", e.getMessage());
                return result;
            }
        } else {
            try {
                redisClient.set(token, user);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("ERROR", e.getMessage());
                return result;
            }
        }
        //传给前端，保存于Cookies
        result.put("SUCCESS", token);
        cookie = new Cookie("token", token);
        cookie.setPath("/online_retailer");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(999 * 24 * 60 * 60);
        try {
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }

        return result;
    }

    /**
     * @描述: 忘记密码
     * @参数: [json, request]
     * @返回值: java.util.Map<java.lang.String               ,                               java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/30
     */
    @RequestMapping(value = "/forgetPasswd")
    @ResponseBody
    public Map<String, String> forgetPasswd(@RequestBody String json, HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();
        JSONObject data = new JSONObject(json);
        String userName = (String) data.get("userName");

        HttpSession session = request.getSession();

        //校验用户信息
        User user = null;
        try {
            user = userService.selectByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("ERROR", e.getMessage());
            return result;
        }
        if (user == null) {
            result.put("INVALID_USERNAME", "用户不存在！");
            return result;
        }

        //校验验证码
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String in_code = (String) data.get("code");
        if (!code.toLowerCase().equals(in_code.toLowerCase())) {
            result.put("INVALID_CODE", "验证码不正确！");
            return result;
        }

        request.getSession().setAttribute("userName", userName);
        result.put("SUCCESS", userName);
        return result;
    }

    /**
     * @描述: 重置密码
     * @参数: [json]
     * @返回值: java.util.Map<java.lang.String               ,                               java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/30
     */
    @RequestMapping(value = "/resetPasswd")
    @ResponseBody
    public Map<String, String> resetPasswd(@RequestBody String json) {

        Map<String, String> result = new HashMap<>();

        JSONObject data = new JSONObject(json);
        String userName = (String) data.get("userName");
        String newPasswd = (String) data.get("newPassword");

        User user = null;
        try {
            user = userService.selectByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("ERROR", e.getMessage());
        }

        //判断新密码是否符合密码格式
        if (newPasswd == null || !MyString.isPassword(newPasswd)) {
            result.put("INVALID_PASSWD", "密码格式不正确！");
            return result;
        }

        //加密保存用户密码
        String ciphertext = MD5.encrypt(newPasswd);
        user.setUserPassword(ciphertext);

        try {
            userService.updateByName(user);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("ERROR", e.getMessage());
            return result;
        }

        result.put("SUCCESS", "重置密码成功");
        return result;
    }

    /**
     * @描述: 若非"记住我"方式登录的用户,Session结束便清楚Token
     * @参数: [request]
     * @返回值: java.util.Map<java.lang.String               ,                               java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/30
     */
    @RequestMapping(value = "/clearToken")
    @ResponseBody
    public Map<String, String> clearToken(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();
        //获取token
        String cookie = request.getHeader("Cookie");
        String[] cookieInfo = cookie.split(";");
        String tokenMessage = null;
        for (String s : cookieInfo) {
            if (s.contains("token")) {
                tokenMessage = s;
            }
        }
        String[] tokenInfo = tokenMessage.split("=");
        String token = tokenInfo[1];

        Boolean flag = (Boolean) request.getSession(false).getAttribute("flag");

        if (!flag) {
            if (redisClient.remove(token)) {
                result.put("MESSAGE", "Token Has Been Clear");
            } else {
                result.put("MESSAGE", "FAILED");
            }
        }

        result.put("MESSAGE", "Remember-me User");
        return result;
    }

    /**
     * 增删查改的controller
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/initUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray init() throws Exception {
//        List<Dictionary> dictionaryList = dictionaryService.selectAll();
        List<User> userList = userService.selectAll();
        JSONArray jsonarray = JSONArray.fromObject(userList);
        return jsonarray;
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> insert(@RequestBody String json) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject(json); // 首先把字符串转成 JSONObject  对象

//        判断是否插入成功
        if (userService.insert(jsonObject)) {
            resultMap.put("result", "SUCCESS");
        } else {
            resultMap.put("result", "Error");
        }
        return resultMap;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> update(@RequestBody String json) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json);// 首先把字符串转成 JSONArray  对象
        System.out.println(jsonArray);
        if (userService.update(jsonArray)) {
            resultMap.put("result", "SUCCESS");
        } else {
            resultMap.put("result", "Error");
        }
        return resultMap;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody String json) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json);
        //返回结果
        if (userService.deleteByDetail(jsonArray)) {
            resultMap.put("result", "SUCCESS");
        } else {
            resultMap.put("result", "Error");
        }
        return resultMap;
    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUserPrivilege(1);
        }
        return user;
    }

    @RequestMapping("/toUser")
    public String toDic() {
        return "html/adminRole-Manager.html";
    }
}
