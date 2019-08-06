package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Store;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/8/3 13:11
 */
public interface StoreService {
    List<Store> selectAllById(Integer userId);

    boolean insert(JSONObject jsonObject, Integer userId);

    //zhuang
    List<Store> selectByBvoIdAndType(Map<String,Object> map);

    List<Store> selectByBvoId(Integer bvoId);
}
