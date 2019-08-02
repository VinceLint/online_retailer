package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.*;
import cn.neusoft.retailer.web.service.*;
import cn.neusoft.retailer.web.tools.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/8/2 20:19
 */

@Controller
@RequestMapping("/createOrderAndLog")
public class OrderAndLogCreatorController {
    @Autowired
    private BrandOrderDropshipService brandOrderDropshipService;
    @Autowired
    private BrandOrderService brandOrderService;
    @Autowired
    private BrandOrderLogisticsService brandOrderLogisticsService ;
    @Autowired
    private BrandOrderLogisticsNodeService brandOrderLogisticsNodeService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 创建订单信息
     * @param param 借卖关系ID
     * @return 0 成功 ，1 失败 ，2 失败且库存不足
     */
    @PostMapping("/createOrder")
    @ResponseBody
    private int createOrder(@RequestBody Map<String,Object> param){
        Integer dropshipId=(Integer) param.get("dropshipId") ;
        Dropship dropship=brandOrderDropshipService.selectByPrimaryKey(dropshipId);
        if(dropship==null) return 1;
        Integer goodsId=dropship.getGoodsId();

        Goods goods=goodsService.selectByPrimaryKey(goodsId);
        int goodsAmount=goods.getGoodsAmount();
        if(goodsAmount==0) return 2;//库存不足

        Integer orderAmount=(int)(Math.random()*goodsAmount+1);
        Integer bsId=dropship.getBvoId();

        Order order=new Order();
        order.setGoodsId(goodsId);
        order.setOrderAmount(orderAmount);
        order.setOrderStatus(OrderStatus.待支付.ordinal());
        order.setOrderCreTime(new Date());
        order.setBsId(bsId);
        boolean success=brandOrderService.insert(order);
        if(success) {
            goods.setGoodsAmount(goodsAmount-orderAmount);//减掉相应库存
            goodsService.updateByPrimaryKey(goods);
            return 0;
        }
        else return 1;
    }

    /**
     * 创建物流信息
     * @param param 订单编号
     */
    @PostMapping("/createLogistics")
    @ResponseBody
    private boolean createLogistics(@RequestBody Map<String,Object> param){
        int orderId=(int)param.get("orderId");
        long time=new Date().getTime();
        int timeOffset=0;

        Order order=brandOrderService.selectByPrimaryKey(orderId);
        String logId=order.getOrderLogId();
        String[] comNames={"顺丰快递","韵达快递","中通快递","圆通快递","申通快递","百世快递","德邦快递"};
        Logistics log=brandOrderLogisticsService.selectByPrimaryKey(logId);
        if(log==null) return false;
        log.setLogComName(comNames[(int)(Math.random()*comNames.length)]);
        log.setLogCouName("张三丰");
        log.setLogCouPhone("15815546689");
        brandOrderLogisticsService.updateByPrimaryKey(log);

        String[] locations={"北京","上海","广州","深圳","杭州","武汉","长沙","成都"};
        List<LogisticsNode> logisticsNodes=brandOrderLogisticsNodeService.selectByLogId(logId);
        if(logisticsNodes==null||logisticsNodes.size()==0){
            //起始地址
            LogisticsNode logisticsNodeBegin=new LogisticsNode();
            String logNodeId=logId+(int)(Math.random()*100);
            while(brandOrderLogisticsNodeService.selectByPrimaryKey(logNodeId)!=null){
                logNodeId=logId+(int)(Math.random()*100);
            }
            logisticsNodeBegin.setLogNodeId(logNodeId);
            logisticsNodeBegin.setLogId(logId);
            logisticsNodeBegin.setLogNodeName(order.getDeliverAddress());
            logisticsNodeBegin.setLogArrTime(new Date(time+timeOffset++));
            brandOrderLogisticsNodeService.insert(logisticsNodeBegin);

            //中转地址
            int midNum=(int)(Math.random()*locations.length);
            for(int i=0;i<midNum;i++){
                LogisticsNode logisticsNodeMid=new LogisticsNode();
                logNodeId=logId+(int)(Math.random()*100);
                while(brandOrderLogisticsNodeService.selectByPrimaryKey(logNodeId)!=null){
                    logNodeId=logId+(int)(Math.random()*100);
                }
                logisticsNodeMid.setLogNodeId(logNodeId);
                logisticsNodeMid.setLogId(logId);
                logisticsNodeMid.setLogNodeName(locations[(int)(Math.random()*locations.length)]);
                logisticsNodeMid.setLogArrTime(new Date(time+timeOffset++));
                brandOrderLogisticsNodeService.insert(logisticsNodeMid);
            }

            //结束地址
            LogisticsNode logisticsNodeEnd=new LogisticsNode();
            logNodeId=logId+(int)(Math.random()*100);
            while(brandOrderLogisticsNodeService.selectByPrimaryKey(logNodeId)!=null){
                logNodeId=logId+(int)(Math.random()*100);
            }
            logisticsNodeEnd.setLogNodeId(logNodeId);
            logisticsNodeEnd.setLogId(logId);
            logisticsNodeEnd.setLogNodeName(order.getReceiverAddress());
            logisticsNodeEnd.setLogArrTime(new Date(time+timeOffset));
            brandOrderLogisticsNodeService.insert(logisticsNodeEnd);
        }
        return true;
    }
}
