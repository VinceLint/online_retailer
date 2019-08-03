package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Store;
import org.json.JSONObject;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/8/3 13:11
 */
public interface StoreService {
    List<Store> selectAllById(Integer userId);

    boolean insert(JSONObject jsonObject, Integer userId);
}
