package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private RedisClient redisClient = new RedisClient();

    /**
     * @描述: 用户注册
     * @参数: [user, request]
     * @返回值: java.util.List<java.lang.Boolean>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/25
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Boolean> register(@RequestBody User user) {

        System.out.println(user.toString());
        List<Boolean> result = new ArrayList<>();

        String userName = user.getUserName();
        String userMail = user.getUserMail();
        String userPhone = user.getUserPhone();
        String userPassword = user.getUserPassword();
        Integer userPrivilege = user.getUserPrivilege();
        Integer mvoType = user.getMvoType();
        String mvoUrl = user.getMvoUrl();

        boolean token = true;

        //判断用户名是否重复
        if (userName == null || userService.selectByName(userName) != null) {
            result.add(false);
            token = false;
        } else {
            result.add(true);
        }

        //判断是否符合email格式
        if (userMail == null || !MyString.isEmail(userMail)) {
            result.add(false);
            token = false;
        } else {
            result.add(true);
        }

        //判断是否符合电话号码格式
        if (userPhone == null || userPhone.length() > 11 || !MyString.ifNumber(userPhone)) {
            result.add(false);
            token = false;
        } else {
            result.add(true);
        }

        //判断是否符合密码格式
        if (userPassword == null || !MyString.isPassword(userPassword)) {
            result.add(false);
            token = false;
        } else {
            result.add(true);
        }

        //判断是否符合url格式
        //非品牌商
        if ("".equals(mvoUrl))
            mvoUrl = null;
        if (!MyString.isURL(mvoUrl)) {
            result.add(false);
            token = false;
        } else {
            result.add(true);
        }

//        System.out.println(result);
        System.out.println(token);

        if (token) {
            user.setUserId(UniqueID.getGuid());
            System.out.println(user.toString());

            String ciphertext = MD5.encrypt(userPassword);
            user.setUserPassword(ciphertext);
            userService.insertByUserInfo(user);
        }

        return result;
    }

    /**
     * @描述: 登录页面加载时遍历浏览器Cookies信息, 获取其中token信息并校验是否过期
     * @参数: [request]
     * @返回值: java.util.Map<java.lang.String, java.lang.String>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/27
     */
    @RequestMapping(value = "/tokenVilidation")
    @ResponseBody
    public Map<String, String> vilidateToken(HttpServletRequest request) {

        Map<String, String> data = new HashMap<>();
        String token = "";
        User user = null;

        //获取客户端保存的所有Cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            //遍历
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
                String userInfo = redisClient.findAndUpdate(token, request.getRemoteAddr());
                try {
                    user = (User) SerializeUtils.serializeToObject(userInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (user != null) {
                    data.put("SUCCESS", "成功登陆");
                    return data;
                } else {
                    data.put("ERROR", "身份过期，请重新登录");
                    return data;
                }
            }
        }
        data.put("ERROR", "请登录");
        return data;
    }


    /**
     * @描述: 登陆校验
     * @参数: [request, response]
     * @返回值: java.lang.Boolean
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/25
     */
    @RequestMapping(value = "/loginValidation")
    @ResponseBody
    public Map<String, String> login(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> result = new HashMap<>();
        JSONObject data = new JSONObject(json);
        User user = null;
        String userName = (String) data.get("userName");
        try {
            user = userService.selectByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("ERROR", e.getMessage());
            return result;
        }
        String token;

        if (user == null || !user.getUserPassword().equals(userName)) {
            result.put("ERROR", "NO USER");
            return result;
        }

        token = TokenCreation.createToken(request.getRemoteAddr());

        //user序列化
        user.setUserPassword(null);
        String userInfo = null;
        try {
            userInfo = SerializeUtils.serialize(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //根据"记住我"的值选择Token存放时间
        if (data.get("remember-me").equals(true)) {
            try {
                redisClient.set(token, userInfo, 7 * 24 * 60 * 60);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("ERROR", e.getMessage());
                return result;
            }
        } else {
            try {
                redisClient.set(token, userInfo);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("ERROR", e.getMessage());
                return result;
            }
        }
        //传给前端，保存于Cookies
        result.put("SUCCESS", token);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/online_retailer");
        cookie.setHttpOnly(true);
        try {
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
        return result;
    }

}
