package cn.neusoft.retailer.web.controller;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-23 13:27
 */


import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.mvoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class companyInfoController {
    @Autowired
    private UserService userService;

    @GetMapping("/company")
    public ModelAndView update(@RequestParam(value = "file", required = false) CommonsMultipartFile Logo, User user, String type) {
        ModelAndView modelAndView = new ModelAndView("user-company");
        Map<Integer, String> map_id = new HashMap<Integer, String>();
        int index = 0;
        for (mvoType e:mvoType.values()){
            map_id.put(index++, e.toString());
        }
        modelAndView.addObject("typeList", map_id);
        return modelAndView;
    }


}