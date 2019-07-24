package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-24 09:53
 */
@Controller
public class testController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "test/{userName}")
    @ResponseBody
    public HashMap<Integer, User> test(@PathVariable("userName") String userName){
        User user = userService.selectByPrimaryKey(123);
        HashMap<Integer, User> hashMap =  new HashMap<Integer, User>();
        hashMap.put(1, user);
        return hashMap;
    }
}
