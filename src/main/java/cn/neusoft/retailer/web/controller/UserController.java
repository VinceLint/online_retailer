package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.MyString;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    public List<Boolean> register(@RequestBody User user) {

        System.out.println(user.toString());
        ArrayList<Boolean> result = new ArrayList<>();
        result.add(false);

        String userName = user.getUserName();
        String userMail = user.getUserMail();
        String userPhone = user.getUserPhone();
        String userPassword = user.getUserPassword();
        Integer userPrivilege = user.getUserPrivilege();
        Integer mvoType = user.getMvoType();
        String mvoUrl = user.getMvoUrl();

//        //判断用户名是否重复
//        if (userName == null || userService.selectByName(userName) != null) {
//            result.add(false);
//        }else {
//            result.add(true);
//        }

        //判断是否符合email格式
        if (userMail == null || !MyString.isEmail(userMail)) {
            result.add(false);
        }else {
            result.add(true);
        }

        //判断是否符合电话号码格式
        if (userPhone == null || userPhone.length() > 11 || !MyString.ifNumber(userPhone)) {
            result.add(false);
        }else {
            result.add(true);
        }

        //判断是否符合密码格式
        if (userPassword == null || !MyString.isPassword(userPassword)) {
            result.add(false);
        }else {
            result.add(true);
        }

        //判断是否符合url格式
        //非品牌商
        if(mvoUrl == "")
            mvoUrl = null;
        if (!MyString.isURL(mvoUrl)) {
            result.add(false);
        }else {
            result.add(true);
        }

        for (boolean a:result
             ) {
            System.out.println(a);
        }

//        user.setUserId(UniqueID.getGuid());

//        userService.insertByUserInfo(user);

        return result;
    }

    @Test
    public void test(){
        User user = userService.selectByName("admin");
        System.out.println(user.toString());
    }

    @ResponseBody
    public User login(@RequestBody User user){

        return null;
    }
}
