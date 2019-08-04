package cn.neusoft.retailer.web.service.impl;
/*
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/24 20:03
 */

import cn.neusoft.retailer.web.mapper.*;
import cn.neusoft.retailer.web.pojo.*;
import cn.neusoft.retailer.web.service.BrandOrderService;
import cn.neusoft.retailer.web.tools.OrderStatus;
import cn.neusoft.retailer.web.tools.TraRecStatus;
import cn.neusoft.retailer.web.tools.TraRecType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class BrandOrderServiceImpl implements BrandOrderService {
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
    public List<Map<String, Object>> selectByBrandUserId(int brandUserId) {
        return orderMapper.selectByBrandUserId(brandUserId);
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

    /**
     * @描述 取消订单，退款,退回库存
     * @参数 session
     * @返回值 是否成功
     * @创建人 胡献涛
     * @创建时间  2019/7/31 15:30
     * @修改人和其它信息
     */
    @Override
    public boolean cancelOrder(int orderId, User brandUser) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) return false;
        order.setOrderStatus(OrderStatus.已取消.ordinal());
        order.setOrderCancelTime(new Date());
        if(orderMapper.updateByPrimaryKey(order)<=0) return false;

        //退回库存
        Goods goods=goodsMapper.selectByPrimaryKey(order.getGoodsId());
        goods.setGoodsAmount(goods.getGoodsAmount()+order.getOrderAmount());
        goodsMapper.updateByPrimaryKey(goods);
        //退款:从品牌商退款到借卖方，改两方余额、添加两方的交易流水
        Integer brandWalId=brandUser.getUserWalId();
        Integer bvoWalId=userMapper.selectByPrimaryKey(order.getBsId()).getUserWalId();
        Wallet brandWallet=walletMapper.selectByPrimaryKey(brandWalId);
        Wallet bvoWallet=walletMapper.selectByPrimaryKey(bvoWalId);
        //退款
        Float money=goodsMapper.selectByPrimaryKey(order.getGoodsId()).getGoodsPrice() * order.getOrderAmount();//订单总价
        brandWallet.setWalBalance(brandWallet.getWalBalance()-money);
        bvoWallet.setWalBalance(bvoWallet.getWalBalance()+money);
        if(walletMapper.updateByPrimaryKey(brandWallet)<=0||walletMapper.updateByPrimaryKey(bvoWallet)<=0)
            return false;
        //添加交易流水
        TransactionRecord brandTransactionRecord=new TransactionRecord();//品牌商交易记录
        brandTransactionRecord.setTraRecDate(new Date());
        brandTransactionRecord.setTraRecType(TraRecType.消费.ordinal());
        brandTransactionRecord.setTraRecSum(money);
        brandTransactionRecord.setTraRecStatus(TraRecStatus.通过.ordinal());
        brandTransactionRecord.setTraRecWalId(brandWalId);
        brandTransactionRecord.setTraRecBalance(brandWallet.getWalBalance());
        if(transactionRecordMapper.insert(brandTransactionRecord)<=0) return false;

        TransactionRecord bvoTransactionRecord=new TransactionRecord();//借卖方交易记录
        bvoTransactionRecord.setTraRecDate(new Date());
        bvoTransactionRecord.setTraRecType(TraRecType.收入.ordinal());
        bvoTransactionRecord.setTraRecSum(money);
        bvoTransactionRecord.setTraRecStatus(TraRecStatus.通过.ordinal());
        bvoTransactionRecord.setTraRecWalId(bvoWalId);
        bvoTransactionRecord.setTraRecBalance(bvoWallet.getWalBalance());
        if(transactionRecordMapper.insert(bvoTransactionRecord)<=0) return false;

        return true;
    }
}
