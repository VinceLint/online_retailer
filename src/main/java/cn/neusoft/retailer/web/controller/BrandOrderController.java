package cn.neusoft.retailer.web.controller;

/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/23 13:30
 */

import cn.neusoft.retailer.web.pojo.Logistics;
import cn.neusoft.retailer.web.pojo.LogisticsNode;
import cn.neusoft.retailer.web.pojo.Order;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.BrandOrderLogisticsNodeService;
import cn.neusoft.retailer.web.service.BrandOrderLogisticsService;
import cn.neusoft.retailer.web.service.BrandOrderService;
import cn.neusoft.retailer.web.tools.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/brandOrder")
public class BrandOrderController {
    @Autowired
    private BrandOrderService brandOrderService;
    @Autowired
    private BrandOrderLogisticsService brandOrderLogisticsService;
    @Autowired
    private BrandOrderLogisticsNodeService brandOrderLogisticsNodeService;


    /**
     *@描述  获取品牌商订单信息
     *@参数
     *@返回值  订单信息
     *@创建人  胡献涛
     *@创建时间  2019/7/24 13:30
     *@修改人和其它信息
     */
    @GetMapping("/getOrders")
    @ResponseBody
    public List<Map<String,Object>> getOrders(HttpSession session){
        Integer brandUserId= ((User)session.getAttribute("user")).getUserId();
        return brandOrderService.selectByBrandUserId(brandUserId);
    }


    /**
     * @描述 发货，更新订单发货方物流信息
     * @参数 logInfo 物流信息
     * @返回值 物流信息修改是否成功
     * @创建人 胡献涛
     * @创建时间  2019/7/29 14:30
     * @修改人和其它信息
     */
    @PostMapping("/transfer")
    @ResponseBody
    public boolean transfer(@RequestBody Map<String,Object> logInfo){
        if(brandOrderLogisticsService.selectByPrimaryKey((String) logInfo.get("log_id"))==null) {
            Logistics logistics = new Logistics();
            logistics.setLogId((String) logInfo.get("log_id"));
            if (!brandOrderLogisticsService.insert(logistics))
                return false;
        }

        Order order = brandOrderService.selectByPrimaryKey((int) logInfo.get("order_id"));
        if (order == null)
            return false;
        order.setOrderStatus(OrderStatus.已发货.ordinal());
        order.setOrderSendTime(new Date());
        order.setOrderLogId((String) logInfo.get("log_id"));
        order.setDeliverName((String) logInfo.get("deliver_name"));
        order.setDeliverAddress((String) logInfo.get("deliver_address"));
        order.setDeliverPhone((String) logInfo.get("deliver_phone"));
        return brandOrderService.updateByPrimaryKey(order);
    }


    /**
     * @描述 保存orderId到Session，用于确定发货的订单ID
     * @参数 param 请求参数，session session对象
     * @返回值 请求成功
     * @创建人 胡献涛
     * @创建时间  2019/7/29 14:30
     * @修改人和其它信息
     */
    @PostMapping("/saveOrderId")
    @ResponseBody
    public boolean saveOrderId(@RequestBody Map<String,Object> param, HttpSession session){
        Integer orderId= (Integer) param.get("orderId");
        session.setAttribute("orderId",orderId);
        return true;
    }


    /**
     * @描述 获取接收方物流信息和要发货的订单ID，用于发货页面
     * @参数 session
     * @返回值 订单里的物流信息
     * @创建人 胡献涛
     * @创建时间  2019/7/29 14:30
     * @修改人和其它信息
     */
    @GetMapping("/getLogInfo")
    @ResponseBody
    public Map<String,Object> getLogInfo(HttpSession session){
        Integer orderId= (Integer) session.getAttribute("orderId");
        session.removeAttribute("orderId");
        Order order= brandOrderService.selectByPrimaryKey(orderId);
        Map<String,Object> returnMap=new HashMap<String,Object>();
        returnMap.put("orderId",order.getOrderId());
        returnMap.put("receiverName",order.getReceiverName());
        returnMap.put("receiverAddress",order.getReceiverAddress());
        returnMap.put("receiverPhone",order.getReceiverPhone());
        returnMap.put("receiverCode",order.getReceiverCode());
        returnMap.put("transWay",order.getTransWay());
        return returnMap;
    }

    @PostMapping("/completeOrder")
    @ResponseBody
    public boolean completeOrder(@RequestBody Map<String,Object> param){
        Integer orderId= (Integer) param.get("orderId");
        Order order=brandOrderService.selectByPrimaryKey(orderId);
        order.setOrderStatus(OrderStatus.已完成.ordinal());
        order.setOrderCompleteTime(new Date());
        return brandOrderService.updateByPrimaryKey(order);
    }


    /**
     * @描述 验证支付密码，取消订单
     * @参数 param 参数Map，保存orderId; session
     * @返回值 订单是否取消成功
     * @创建人 胡献涛
     * @创建时间  2019/7/29 14:30
     * @修改人和其它信息
     */
    @PostMapping("/cancelOrder")
    @ResponseBody
    public Map<String,Object> cancelOrder(@RequestBody Map<String,Object> param,HttpSession session){
        Map<String,Object> retMap=new HashMap<String,Object>();
        //验证支付密码
        User brandUser= (User) session.getAttribute("user");
        String paymentPassword= (String) param.get("paymentPassword");
        boolean success=false;
        success=brandOrderService.validatePaymentPassword(brandUser,paymentPassword);
        if(!success){//密码不正确
            retMap.put("res",false);
            retMap.put("msg","支付密码错误");
            return retMap;
        }
        //取消订单
        success=false;
        Integer orderId=(Integer)param.get("orderId");
        success=brandOrderService.cancelOrder(orderId,brandUser);
        if(success){//取消订单成功
            retMap.put("res",true);
            retMap.put("msg","取消订单成功");
        }else{//取消订单失败
            retMap.put("res",false);
            retMap.put("msg","取消订单失败，请重试");
        }
        return retMap;
    }


    /**
     * @描述 物流跟踪，保存orderLogId到Session
     * @参数 param 传入参数, session
     * @返回值 请求成功
     * @创建人 胡献涛
     * @创建时间  2019/7/30 14:30
     * @修改人和其它信息
     */
    @PostMapping("/saveOrderLogId")
    @ResponseBody
    public boolean saveOrderLogId(@RequestBody Map<String,Object> param, HttpSession session){
        String orderLogId=(String)param.get("orderLogId");
        session.setAttribute("orderLogId",orderLogId);
        return true;
    }

    /**
     * @描述 获取品牌商物流跟踪信息
     * @参数 session
     * @返回值 物流跟踪信息
     * @创建人 胡献涛
     * @创建时间  2019/7/30 15:30
     * @修改人和其它信息
     */
    @PostMapping("/brandOrderTracking")
    @ResponseBody
    public Map<String,Object> brandOrderTracking(HttpSession session){
        //获取session中的数据
        String orderLogId=(String)session.getAttribute("orderLogId");
        session.removeAttribute("orderLogId");
        if(orderLogId==null)
            return null;
        //保存物流信息到HashMap
        Map<String,Object> retMap=new HashMap<String,Object>();
        Logistics logistics= brandOrderLogisticsService.selectByPrimaryKey(orderLogId);
        retMap.put("logistics",logistics);
        List<LogisticsNode> logisticsNodes= brandOrderLogisticsNodeService.selectByLogId(orderLogId);//得到的结果已经按照时间降序排序
        retMap.put("logisticsNodes",logisticsNodes);
        return retMap;
    }
}
