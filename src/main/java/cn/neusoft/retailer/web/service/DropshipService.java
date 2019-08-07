package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Dropship;

import java.util.List;
import java.util.Map;

public interface DropshipService {
    boolean deleteByPrimaryKey(Integer dropshipId);

    Boolean insert(Dropship record);

    Dropship selectByPrimaryKey(Integer dropshipId);

    List<Dropship> selectByBvoIdAndGoodsId(Map<String, Object> map);

    boolean existByByBvoIdAndGoodsId(Map<String, Object> map);



}
