package cn.neusoft.retailer.web.service;
/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/24 20:01
 */
import cn.neusoft.retailer.web.pojo.Order;

import java.util.List;
import java.util.Map;


public interface OrderService {
    boolean deleteByPrimaryKey(Integer orderId);

    boolean insert(Order record);

    Order selectByPrimaryKey(Integer orderId);

    List<Order> selectAll();

    boolean updateByPrimaryKey(Order record);

    List<Order> selectByProperty(String propertyName,Object value);

    List<Map<String,Object>> selectByBrandUserId(int brandUserId);
}
