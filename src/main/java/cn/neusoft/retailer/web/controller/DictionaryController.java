package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/insertDictionary", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String insert(@RequestBody String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        //这个对象就是你前端提交的对象
        Map<String, Object> m = mapper.readValue(json, Map.class);
        //这个是演示所以就不返回了
        return null;
    }

}
