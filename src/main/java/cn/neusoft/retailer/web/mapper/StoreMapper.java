package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer storeId);

    int insert(Store record);

    Store selectByPrimaryKey(Integer storeId);

    List<Store> selectAll();

    int updateByPrimaryKey(Store record);

    List<Store> selectAllById(@Param("userId") Integer userId);
}