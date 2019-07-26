package cn.neusoft.retailer.web.service.impl;
/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/24 20:03
 */
import cn.neusoft.retailer.web.mapper.OrderMapper;
import cn.neusoft.retailer.web.pojo.Order;
import cn.neusoft.retailer.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer orderId) {
        return orderMapper.deleteByPrimaryKey(orderId)>0;
    }

    @Override
    public boolean insert(Order record) {
        return orderMapper.insert(record)>0;
    }

    @Override
    public Order selectByPrimaryKey(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<Order> selectAll() {
        return orderMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record)>0;
    }

    @Override
    public List<Order> selectByProperty(String propertyName, Object value) {
        return orderMapper.selectByProperty(propertyName,value);
    }

    @Override
    public List<Map<String, Object>> selectByBrandUserId(int brandUserId) {
        return orderMapper.selectByBrandUserId(brandUserId);
    }
}
