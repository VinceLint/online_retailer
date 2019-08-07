package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Order;
import cn.neusoft.retailer.web.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author 郑宗伟
 * @version 1.0
 * @date 2019/8/4 19:16
 */
public interface BvoOrderService {
    boolean deleteByPrimaryKey(Integer orderId);

    boolean insert(Order record);

    Order selectByPrimaryKey(Integer orderId);

    List<Order> selectAll();

    boolean updateByPrimaryKey(Order record);

    List<Order> selectByProperty(String propertyName, Object value);

    List<Map<String,Object>> selectByBvoUserId(int bvoUserId);

    boolean validatePaymentPassword(User user, String paymentPassword);
}
