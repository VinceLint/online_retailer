package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    Order selectByPrimaryKey(Integer orderId);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);


    List<Order> selectByProperty(@Param("propertyName") String propertyName, @Param("value")Object value);

    List<Map<String,Object>> selectByBrandUserId(int brandUserId);

    List<Map<String, Object>> selectByBvoUserId(int bvoUserId);
}