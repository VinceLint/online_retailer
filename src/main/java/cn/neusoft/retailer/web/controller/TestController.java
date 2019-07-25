package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "test/{userName}")
    @ResponseBody
    public String test(@PathVariable("userName") String userName) throws Exception {
        System.out.println("123");
        User user = userService.selectByPrimaryKey(Integer.parseInt(userName));
        System.out.println("user : " + user.toString());
        HashMap<Integer, User> hashMap =  new HashMap<Integer, User>();
        hashMap.put(1, user);
        hashMap.put(2, user);
        System.out.println("hashMap : " + hashMap);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(user);
        return jsonString;
    }
}
