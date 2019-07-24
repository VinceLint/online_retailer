package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.UniqueID;
import cn.neusoft.retailer.web.tools.string;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/register.do")
    @ResponseBody
    public User register(@RequestBody User user) {

        System.out.println(user.toString());

        String userName = user.getUserName();
        String userMail = user.getUserMail();
        String userPhone = user.getUserPhone();
        String userPassword = user.getUserPassword();
        Integer userPrivilege = user.getUserPrivilege();
        Integer mvoType = user.getMvoType();
        String mvoUrl = user.getMvoUrl();

        //判断用户名是否重复
        if (userName == null || userService.selectByName(userName) != null) {
            return null;
        }

        //判断是否符合email格式
        if (userMail == null || !string.isEmail(userMail)) {
            return null;
        }

        //判断是否符合电话号码格式
        if (userPhone == null || userPhone.length() > 11 || !string.ifNumber(userPhone)) {
            return null;
        }

        //判断是否符合密码格式
        if (userPassword == null || !string.isPassword(userPassword)) {
            return null;
        }

        //判断是否符合url格式
        if (!string.isURL(mvoUrl)) {
            return null;
        }

        user.setUserId(UniqueID.getGuid());

        userService.insertByUserInfo(user);
        return null;
    }

    @ResponseBody
    public User login(@RequestBody User user){

        return null;
    }
}
