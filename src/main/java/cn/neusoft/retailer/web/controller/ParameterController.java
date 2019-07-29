package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.service.ParameterService;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/7/29 15:15
 */
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "/initParameter", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray init(@RequestBody String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json); // 首先把字符串转成 JSONObject  对象
        Integer pageNum = jsonObject.getInt("pageNum");
        JSONArray jsonarray = JSONArray.fromObject(parameterService.selectByPage(pageNum));
        return jsonarray;
    }

    @RequestMapping(value = "/loadParameter", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray loadData(@RequestBody String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json); // 首先把字符串转成 JSONObject  对象
        Integer pageNum = jsonObject.getInt("pageNum");
        JSONArray jsonarray = JSONArray.fromObject(parameterService.selectByPage(pageNum));
        return jsonarray;
    }
}
