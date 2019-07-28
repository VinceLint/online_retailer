package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.*;
import com.google.code.kaptcha.Constants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


//点击退出按钮，redis和cookies内token内容全删
//关闭浏览器，若非"记住我"方式登录，redis和cookies内token全删，否则不予处理

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
     * @参数: [user, request]
     * @返回值: java.util.List<java.lang.Boolean>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/25
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

        boolean token = true;

        //判断用户名是否重复
        if (userName == null || userService.selectByName(userName) != null) {
            result.put("INVALID_USERNAME", "该用户名已被注册！");
            token = false;
        }

        //判断是否符合email格式
        if (userMail == null || !MyString.isEmail(userMail)) {
            result.put("INVALID_EMAIL", "邮箱格式不正确！");
            token = false;
        }

        //判断是否符合电话号码格式
        if (userPhone == null || userPhone.length() > 11 || !MyString.ifNumber(userPhone)) {
            result.put("INVALID_PHONE", "手机号码格式不正确！");
            token = false;
        }

        //判断是否符合密码格式
        if (userPassword == null || !MyString.isPassword(userPassword)) {
            result.put("INVALID_PASSWD", "密码格式不正确！");
            token = false;
        }

        //判断是否符合url格式
        if ("".equals(mvoUrl))
            mvoUrl = null;
        if (!MyString.isURL(mvoUrl)) {
            result.put("INVALID_URL", "url格式不正确！");
            token = false;
        }

        if (token) {
            user.setUserId(UniqueID.getGuid());

            //加密保存用户密码
            String ciphertext = MD5.encrypt(userPassword);
            user.setUserPassword(ciphertext);
            userService.insertByUserInfo(user);
        }

        return result;
    }

    /**
     * @描述: 验证token
     * @参数: [request]
     * @返回值: java.util.Map<java.lang.String, java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/28
     */
    @RequestMapping(value = "/tokenVilidation")
    @ResponseBody
    public Map<String, String> vilidateToken(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();
        String cookie = request.getHeader("Cookie");
        User user = null;

        //判断是否通过"记住我"方式登录
        Boolean flag;
        flag = (Boolean) request.getSession().getAttribute("flag");

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
                user = redisClient.findAndUpdate(token, "127.0.0.1", flag);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
            if (user != null) {
                result.put("SUCCESS", "身份有效");
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
     * @参数: [json, request, response]
     * @返回值: java.util.Map<java.lang.String, java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/28
     */
    @RequestMapping(value = "/loginValidation")
    @ResponseBody
    public Map<String, String> login(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> result = new HashMap<>();
        JSONObject data = new JSONObject(json);
        String userName = (String) data.get("userName");

        HttpSession session = request.getSession();

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
        if (!user.getUserPassword().equals(MD5.encrypt(in_passwd))) {
            result.put("INVALID_PASSWD", "Password Is Invalid");
            return result;
        }

        //校验验证码
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String in_code = (String) data.get("code");
        if (!code.toLowerCase().equals(in_code.toLowerCase())) {
            result.put("INVALID_CODE", "Code Is Invalid");
            return result;
        }

        String token;
        token = TokenCreation.createToken("127.0.0.1");

        user.setUserPassword(null);
        session.setAttribute(userName, user);

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

//        //判断是否通过"记住我"方式登录的标识
//        session.setAttribute("flag", false);

        return result;
    }

    @RequestMapping(value = "/initFlag")
    public void initFlag(HttpServletRequest request) {
        request.getSession().setAttribute("flag", false);
    }

    @RequestMapping(value = "/changeFlag")
    public String changeFlag(HttpServletRequest request) {
        request.getSession().setAttribute("flag", true);
        return "redirect:http://www.baidu.com";
    }

}
