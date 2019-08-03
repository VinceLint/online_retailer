package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.StoreMapper;
import cn.neusoft.retailer.web.pojo.Store;
import cn.neusoft.retailer.web.service.StoreService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林跃涛
 * @version 1.0
 * @date 2019/8/3 13:11
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    /**
     *@描述  查找该用户所有商铺
     *@参数  用户id
     *@返回值  商铺信息列表
     *@创建人  林跃涛
     *@创建时间  2019/8/3 13:14
     *@修改人和其它信息
     */
    @Override
    public List<Store> selectAllById(Integer userId) {
        return storeMapper.selectAllById(userId);
    }

    /**
     *@描述  插入一条商铺记录并指定他的用户
     *@参数  JSONObject对象,整型对象
     *@返回值  Boolean对象
     *@创建人  林跃涛
     *@创建时间  2019/8/3 15:03
     *@修改人和其它信息
     */
    @Override
    public boolean insert(JSONObject jsonObject, Integer userId) {
        Store store = new Store(null,jsonObject.getString("storeName"),
                jsonObject.getInt("type"),userId,jsonObject.getString("marketplace"),
                jsonObject.getString("nws"));
        if (storeMapper.insert(store)==1) return true;
        return false;
    }
}
