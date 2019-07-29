package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Dropship;

import java.util.List;

public interface DropshipMapper {
    int deleteByPrimaryKey(Integer dropshipId);

    int insert(Dropship record);

    Dropship selectByPrimaryKey(Integer dropshipId);

    List<Dropship> selectAll();

    int updateByPrimaryKey(Dropship record);
}