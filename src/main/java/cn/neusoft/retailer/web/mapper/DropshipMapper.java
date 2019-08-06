package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Dropship;

import java.util.List;
import java.util.Map;

public interface DropshipMapper {
    int deleteByPrimaryKey(Integer dropshipId);

    int insert(Dropship record);

    Dropship selectByPrimaryKey(Integer dropshipId);

    List<Dropship> selectByBvoIdAndGoodsId(Map<String,Object> map);

    List<Dropship> selectByBvoId(Integer bvoId);

    int updateByPrimaryKey(Dropship record);

    List<Dropship> selectAll();
}