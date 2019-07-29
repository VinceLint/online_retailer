package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Cust_Cookie;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.MD5;
import cn.neusoft.retailer.web.tools.MyString;
import cn.neusoft.retailer.web.tools.TokenCreation;
import cn.neusoft.retailer.web.tools.UniqueID;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 罗圣荣
 * @version 1.0
 * @date 2019/7/23 15:52
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml", "classpath*:springmvc.xml"})

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @描述: 选中"记住我"功能后在浏览器保存相应用户信息(Cookies)
     * @参数: [userName, userPassword, request, response]
     * @返回值: void
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/25
     */
    private void saveCookie(String userName, String userPassword, HttpServletRequest request, HttpServletResponse response) {

        if (true) {
            //创建Cookie
            Cookie userNameCookie = new Cookie("userName", userName);
            //可以先加密
            Cookie userPasswordCookie = new Cookie("userPassword", userPassword);
            //设置Cookie父路径
            userNameCookie.setPath(request.getContextPath() + "/");
            userPasswordCookie.setPath(request.getContextPath() + "/");
            //是否保存Cookie(根据复选框Remember-me的值)
            String remember = request.getParameter("remember-me");
            if (remember == null || remember.equals(false)) {
                //不保存Cookie
                userNameCookie.setMaxAge(0);
                userPasswordCookie.setMaxAge(0);
            } else {
                //保存Cookie的时间长度
                userNameCookie.setMaxAge(7 * 24 * 60 * 60);
                userPasswordCookie.setMaxAge(7 * 24 * 60 * 60);
            }
            //加入Cookie响应头
            response.addCookie(userNameCookie);
            response.addCookie(userPasswordCookie);
        }
    }

    /**
     * @描述: 登录页面加载时遍历浏览器记录Cookies信息；若有对应用户信息，直接跳转主界面
     * @参数: [request]
     * @返回值: cn.neusoft.retailer.web.pojo.Cust_Cookie
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/25
     */
    @RequestMapping(value = "/getCookie")
    @ResponseBody
    public Cust_Cookie getCookie(HttpServletRequest request) {

        String userName = "";
        String userPassword = "";
        //获取客户端保存的所有Cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            //遍历
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if ("userName".equals(cookie.getName())) {
                    userName = cookie.getValue();
                }
                if ("userPassword".equals(cookie.getName())) {
                    userPassword = cookie.getValue();
                }
            }
        }
        Cust_Cookie cookie = new Cust_Cookie();
        cookie.setUserName(userName);
        cookie.setUserPassword(userPassword);
        return cookie;
    }

    /**
     * @描述: 用户注册
     * @参数: [user, request]
     * @返回值: java.util.List<java.lang.Boolean>
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/25
     */
    @RequestMapping(value = "/register")
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
        if (mvoUrl == "")
            mvoUrl = null;
        if (!MyString.isURL(mvoUrl)) {
            result.add(false);
            token = false;
        } else {
            result.add(true);
        }

//        System.out.println(result);
        System.out.println(token);

        if(token){
            user.setUserId(UniqueID.getGuid());
            System.out.println(user.toString());

            String ciphertext = MD5.encrypt(userPassword);
            user.setUserPassword(ciphertext);
            userService.insertByUserInfo(user);
        }

        return result;
    }

    /**
     * @描述: 登陆校验
     * @参数: [request, response]
     * @返回值: java.lang.Boolean
     * @创建人: 罗圣荣
     * @创建时间: 2019/7/25
     */
    @RequestMapping(value = "/loginValidate")
    @ResponseBody
    public Map<String, String> login(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> result = new HashMap<>();
        User user = userService.selectByName(request.getParameter("userName"));
        String token;

        if (user == null || !user.getUserPassword().equals(request.getParameter("userPassword"))) {
            result.put("ERROR", "NO USER");
            return result;
        }

        token = TokenCreation.createToken(user.getUserName());
        result.put("SUCCESS", token);

        return result;
    }
}
