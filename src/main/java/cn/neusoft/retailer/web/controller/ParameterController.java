package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Parameter;
import cn.neusoft.retailer.web.service.ParameterService;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/29 15:15
 */
@Controller
@RequestMapping("/parameter")
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @PostMapping(value = "/initParameter", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> init() throws Exception{
        Map<String,Object> map = new HashMap<>();
        int pageSum = parameterService.selectAll().size();
        map.put("pageSum",pageSum);
        return map;
    }

    @PostMapping(value = "/loadParameter", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray loadData(@RequestBody String json) throws Exception{
        JSONObject jsonObject = new JSONObject(json); // 首先把字符串转成 JSONObject  对象
        int pageNum = jsonObject.getInt("pa");
        List<Parameter> parameters = parameterService.selectByPage(pageNum);
        JSONArray jsonarray = JSONArray.fromObject(parameters);
        return jsonarray;
    }

    @PostMapping(value = "/updateParameter", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> updateData(@RequestBody String json) throws Exception{
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject(json); // 首先把字符串转成 JSONObject对象
        if(parameterService.updateByPrimaryKey(jsonObject)){
            map.put("result","SUCCESS");
        }else map.put("result","ERROR");
        return map;
    }

    @PostMapping(value = "/insertParameter", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> insertData(@RequestBody String json) throws Exception{
        System.out.println(json);
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject(json); // 首先把字符串转成 JSONObject对象
        System.out.println(jsonObject);
        if(parameterService.insert(jsonObject)){
            map.put("result","SUCCESS");
        }else map.put("result","ERROR");
        return map;
    }

    @PostMapping(value = "/deleteParameter", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> deleteData(@RequestBody String json) throws Exception{
        System.out.println(json);
        Map<String, Object> map = new HashMap<>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json); // 首先把字符串转成 JSONObject对象
        System.out.println(jsonArray);
        if(parameterService.delete(jsonArray)){
            map.put("result","SUCCESS");
        }else map.put("result","ERROR");
        return map;
    }
}
