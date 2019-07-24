package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @RequestMapping("/register.do")
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("前端传递过来的名字是" + request.getParameter("name"));
        try {
            response.getWriter().write("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
