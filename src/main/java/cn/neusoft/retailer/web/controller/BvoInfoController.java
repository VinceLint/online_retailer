package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.BvoInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/8/2 10:29
 */
@Controller
@RequestMapping("BvoInfo")
public class BvoInfoController {
    @Autowired
    private BvoInfoService bvoInfoService;
    @PostMapping(value = "/initBvoInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject init(HttpSession session) throws Exception{
        User user = (User)session.getAttribute("user");
        System.out.println(user.getUserId());
        User lateUser = bvoInfoService.selectByPrimaryKey(user.getUserId());
        JSONObject jsonObject = JSONObject.fromObject(lateUser);
        System.out.println(jsonObject);
        return jsonObject;
    }

    @PostMapping(value = "/updateBvoInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> update(@RequestBody String json,HttpSession session) throws Exception{
        Map<String, Object> map = new HashMap<>();
        org.json.JSONObject jsonObject = new org.json.JSONObject(json);
        User user = (User)session.getAttribute("user");
        if (bvoInfoService.update(jsonObject,user.getUserId())){
            map.put("result","SUCCESS");
        }else map.put("result","ERROR");
        return map;
    }

}
