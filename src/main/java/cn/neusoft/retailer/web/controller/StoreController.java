package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Store;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.StoreService;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/8/3 13:10
 */
@Controller
@RequestMapping("BvoStore")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping(value = "/loadMyStore", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray loadStore(HttpSession session) throws Exception{
        User user = (User)session.getAttribute("user");
        List<Store> stores = storeService.selectAllById(user.getUserId());
        JSONArray jsonArray = JSONArray.fromObject(stores);
        System.out.println(jsonArray);
        return jsonArray;
    }

    @PostMapping(value = "/addStore", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> loadStore(@RequestBody String json,HttpSession session) throws Exception{
        User user = (User)session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject(json);
        if(storeService.insert(jsonObject,user.getUserId())){
            map.put("result","SUCCESS");
        }else map.put("result","ERROR");
        return map;
    }
}
