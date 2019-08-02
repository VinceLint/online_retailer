package cn.neusoft.retailer.web.tools;
/**
 * @author 胡献涛
 * @date 2019/8/1 23:30
 * @version 1.0
 */

import cn.neusoft.retailer.web.pojo.Dropship;
import cn.neusoft.retailer.web.pojo.Logistics;
import cn.neusoft.retailer.web.pojo.LogisticsNode;
import cn.neusoft.retailer.web.pojo.Order;
import cn.neusoft.retailer.web.service.BrandOrderDropshipService;
import cn.neusoft.retailer.web.service.BrandOrderLogisticsNodeService;
import cn.neusoft.retailer.web.service.BrandOrderLogisticsService;
import cn.neusoft.retailer.web.service.BrandOrderService;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class OrderAndLogCreator extends Application {
    private static ApplicationContext context;
    @Autowired
    private BrandOrderDropshipService brandOrderDropshipService;
    @Autowired
    private BrandOrderService brandOrderService;
    @Autowired
    private BrandOrderLogisticsService brandOrderLogisticsService;
    @Autowired
    private BrandOrderLogisticsNodeService brandOrderLogisticsNodeService;

    public static void main(String[] args) {
        OrderAndLogCreator.context=new ClassPathXmlApplicationContext("applicationContext.xml");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vbox=new VBox();

        Label orderLabel=new Label("借卖关系ID：");
        TextField orderTextField=new TextField();
        orderTextField.setMaxWidth(200);
        Button orderButton=new Button("创建订单信息");
        orderButton.setOnAction(e->createOrder(Integer.parseInt(orderTextField.getText())));

        Label logLabel=new Label("订单ID：");
        TextField logisticsTextField=new TextField();
        logisticsTextField.setMaxWidth(200);
        Button logisticsButton=new Button("创建物流信息");
        logisticsButton.setOnAction(e->createLogistics(Integer.parseInt(logisticsTextField.getText())));

        vbox.getChildren().addAll(orderLabel,orderTextField,orderButton,logLabel,logisticsTextField,logisticsButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Scene scene=new Scene(vbox,400,300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("自动创建订单和生成物流信息");
        primaryStage.show();
    }

    /**
     * 创建订单信息
     * @param dropshipId 借卖关系ID
     */
    private void createOrder(Integer dropshipId){
        Dropship dropship=brandOrderDropshipService.selectByPrimaryKey(dropshipId);
        if(dropship==null) return;
        Integer goodsId=dropship.getGoodsId();
        Integer orderAmount=(int)(Math.random()*100);
        Integer bsId=dropship.getBvoId();

        Order order=new Order();
        order.setGoodsId(goodsId);
        order.setOrderAmount(orderAmount);
        order.setOrderStatus(OrderStatus.待支付.ordinal());
        order.setOrderCreTime(new Date());
        order.setBsId(bsId);
        brandOrderService.insert(order);
    }

    /**
     * 创建物流信息
     * @param orderId 订单编号
     */
    private void createLogistics(int orderId){
        long time=new Date().getTime();
        int timeOffset=0;

        Order order=brandOrderService.selectByPrimaryKey(orderId);
        String logId=order.getOrderLogId();
        String[] comNames={"顺丰快递","韵达快递","中通快递","圆通快递","申通快递","百世快递","德邦快递"};
        Logistics log=brandOrderLogisticsService.selectByPrimaryKey(logId);
        if(log==null) return;
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
    }
}
