package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Order;
import cn.neusoft.retailer.web.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 郑宗伟
 * @version 1.0
 * @date 2019/8/6 10:05
 */
public interface BvoPaymentService {
    boolean deleteByPrimaryKey(Integer orderId);

    boolean insert(Order record);

    Order selectByPrimaryKey(Integer orderId);

    List<Order> selectAll();

    boolean updateByPrimaryKey(Order record);

    List<Map<String,Object>> selectByBvoUserId(int bvoUserId);

    boolean validatePaymentPassword(User user, String paymentPassword);

    boolean payOrder(int orderId, User bvoUser,Map<String,Object> param);
}
