package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Menu;
import cn.neusoft.retailer.web.service.MenuService;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 邹文深
 * @version 1.1
 * @date 2019/8/1 23:26
 */

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/initMenu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray init() throws Exception{
        List<Menu> menuList=menuService.selectAll();
//        System.out.println("已执行");
        JSONArray jsonarray = JSONArray.fromObject(menuList);
        return jsonarray;
    }


    @RequestMapping(value = "/insertMenu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> insert(@RequestBody String json) throws Exception{
        Map<String,Object> resultMap = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject(json); // 首先把字符串转成 JSONObject  对象

//        判断是否插入成功
        if(menuService.insert(jsonObject)){
            resultMap.put("result","SUCCESS");
        }else{
            resultMap.put("result","Error");
        }
        return resultMap;
    }

    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> update(@RequestBody String json) throws Exception{
        Map<String,Object> resultMap = new HashMap<String, Object>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json);// 首先把字符串转成 JSONArray  对象
        System.out.println(jsonArray);
        if(menuService.update(jsonArray)){
            resultMap.put("result","SUCCESS");
        }else{
            resultMap.put("result","Error");
        }
        return resultMap;
    }

    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody String json) throws Exception{
        Map<String,Object> resultMap = new HashMap<String, Object>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json);
        if(menuService.deleteByDetail(jsonArray)){
            resultMap.put("result","SUCCESS");
        }else{
            resultMap.put("result","Error");
        }
        return resultMap;
    }

    @RequestMapping("/toMenu")
    public String  toDic(){
        return "html/adminMenu-Manager.html";
    }
}
