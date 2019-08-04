package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Dictionary;
import cn.neusoft.retailer.web.service.DictionaryService;
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
 * @date 2019/7/24 10:40
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping(value = "/initDictionary", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray init() throws Exception{
        List<Dictionary> dictionaryList = dictionaryService.selectAll();
        JSONArray jsonarray = JSONArray.fromObject(dictionaryList);
        return jsonarray;
    }

    @PostMapping(value = "/insertDictionary", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> insert(@RequestBody String json) throws Exception{
        Map<String,Object> resultMap = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject(json); // 首先把字符串转成 JSONObject  对象
//        判断是否插入成功
        if(dictionaryService.insert(jsonObject)){
            resultMap.put("result","SUCCESS");
        }else{
            resultMap.put("result","Error");
        }
        return resultMap;
    }

    @PostMapping(value = "/updateDictionary", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> update(@RequestBody String json) throws Exception{
        Map<String,Object> resultMap = new HashMap<String, Object>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json);// 首先把字符串转成 JSONArray  对象
        System.out.println(jsonArray);
//        判断是否插入成功
        if(dictionaryService.update(jsonArray)){
            resultMap.put("result","SUCCESS");
        }else{
            resultMap.put("result","Error");
        }
        return resultMap;
    }

    @PostMapping(value = "/deleteDictionary", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> delete(@RequestBody String json) throws Exception{
        Map<String,Object> resultMap = new HashMap<String, Object>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json);
        //返回结果
        if(dictionaryService.deleteByDetail(jsonArray)){
            resultMap.put("result","SUCCESS");
        }else{
            resultMap.put("result","Error");
        }
        return resultMap;
    }
}
