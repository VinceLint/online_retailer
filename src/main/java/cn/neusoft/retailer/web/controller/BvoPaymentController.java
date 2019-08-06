package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Order;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.BvoOrderService;
import cn.neusoft.retailer.web.service.BvoPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 郑宗伟
 * @version 1.0
 * @date 2019/8/6 10:04
 */

@Controller
@RequestMapping("/bvoPayment")
public class BvoPaymentController {
    @Autowired
    private BvoPaymentService bvoPaymentServiceImpl;
    @Autowired
    private BvoOrderService bvoOrderServiceImpl;

    /**
     *@描述  获取借卖方订单信息
     *@参数
     *@返回值  订单信息
     *@创建人  郑宗伟
     *@创建时间  2019/7/24 13:30
     *@修改人和其它信息
     */
    @GetMapping("/getOrders")
    @ResponseBody
    public List<Map<String,Object>> getOrders(HttpSession session){
        Integer bvoUserId= ((User)session.getAttribute("user")).getUserId();
        return bvoOrderServiceImpl.selectByBvoUserId(bvoUserId);
    }

    /**
     * @描述 保存orderId到Session，用于确定订单ID
     * @参数 param 请求参数，session session对象
     * @返回值 请求成功
     * @创建人 郑宗伟
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
     * @描述 获取接收方物流信息和要发货的订单ID
     * @参数 session
     * @返回值 订单里的物流信息
     * @创建人 郑宗伟
     * @创建时间  2019/7/29 14:30
     * @修改人和其它信息
     */
    @GetMapping("/getLogInfo")
    @ResponseBody
    public Map<String,Object> getLogInfo(HttpSession session){
        Integer orderId= (Integer) session.getAttribute("orderId");
        session.removeAttribute("orderId");
        Order order= bvoPaymentServiceImpl.selectByPrimaryKey(orderId);
        Map<String,Object> returnMap=new HashMap<String,Object>();
        returnMap.put("orderId",order.getOrderId());
        returnMap.put("deliverName",order.getDeliverName());
        returnMap.put("deliverAddress",order.getDeliverAddress());
        returnMap.put("deliverPhone",order.getDeliverPhone());
        return returnMap;
    }

     /**
     * @描述 验证支付密码，取消订单
     * @参数 param 参数Map，保存orderId; session
     * @返回值 订单是否取消成功
     * @创建人 胡献涛
     * @创建时间  2019/7/29 14:30
     * @修改人和其它信息
     */
    @PostMapping("/payOrder")
    @ResponseBody
    public Map<String,Object> payOrder(@RequestBody Map<String,Object> param,HttpSession session){
        Map<String,Object> retMap=new HashMap<String,Object>();
        //验证支付密码
        User bvoUser= (User) session.getAttribute("user");
        String paymentPassword= (String) param.get("paymentPassword");
        boolean success=false;
        success=bvoPaymentServiceImpl.validatePaymentPassword(bvoUser,paymentPassword);
        if(!success){//密码不正确
            retMap.put("res",false);
            retMap.put("msg","支付密码错误");
            return retMap;
        }
        //支付订单
        success=false;
        Integer orderId=(Integer)param.get("order_id");
        success=bvoPaymentServiceImpl.payOrder(orderId,bvoUser,param);
        if(success){//支付订单成功
            retMap.put("res",true);
            retMap.put("msg","支付订单成功");
        }else{//取消订单失败
            retMap.put("res",false);
            retMap.put("msg","支付订单失败，请重试");
        }
        return retMap;
    }




}
