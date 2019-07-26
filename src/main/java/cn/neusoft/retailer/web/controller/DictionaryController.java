package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Dictionary;
import cn.neusoft.retailer.web.service.DictionaryService;
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
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/24 10:40
 */
@Controller
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/initDictionary", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray init() throws Exception{
        List<Dictionary> dictionaryList = dictionaryService.selectAll();
        JSONArray jsonarray = JSONArray.fromObject(dictionaryList);
        return jsonarray;
    }

    @RequestMapping(value = "/insertDictionary", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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

    @RequestMapping(value = "/updateDictionary", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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

    @RequestMapping("/toDictionary")
    public String  toDic(){
        return "html/dictionary.html";
    }

}
