package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.*;
import cn.neusoft.retailer.web.pojo.Order;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.pojo.Wallet;
import cn.neusoft.retailer.web.service.BvoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 郑宗伟
 * @version 1.0
 * @date 2019/8/4 19:20
 */

@Service
@Transactional
public class BvoOrderServiceImpl implements BvoOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private TransactionRecordMapper transactionRecordMapper;

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
    public List<Map<String, Object>> selectByBvoUserId(int bvoUserId) {
        return orderMapper.selectByBvoUserId(bvoUserId);
    }

    /**
     * @描述 验证支付密码
     * @参数 session
     * @返回值 密码是否正确
     * @创建人 胡献涛
     * @创建时间  2019/7/31 15:30
     * @修改人和其它信息
     */
    @Override
    public boolean validatePaymentPassword(User user, String paymentPassword) {
        int walId=user.getUserWalId();
        Wallet wallet=walletMapper.selectByPrimaryKey(walId);
        String pw=wallet.getWalPassword();
        return pw.equals(paymentPassword);
    }

}
