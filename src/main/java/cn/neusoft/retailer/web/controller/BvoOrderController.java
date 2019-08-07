package cn.neusoft.retailer.web.controller;

/**
 * @author  郑宗伟
 * @version 1.0
 * @date 2019/7/23 13:30
 */

import cn.neusoft.retailer.web.pojo.*;
import cn.neusoft.retailer.web.service.BrandOrderLogisticsNodeService;
import cn.neusoft.retailer.web.service.BrandOrderLogisticsService;
import cn.neusoft.retailer.web.service.BvoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/bvoOrder")
public class BvoOrderController {
    @Autowired
    private BvoOrderService bvoOrderService;
    @Autowired
    private BrandOrderLogisticsService brandOrderLogisticsService;
    @Autowired
    private BrandOrderLogisticsNodeService brandOrderLogisticsNodeService;


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
        return bvoOrderService.selectByBvoUserId(bvoUserId);
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
     * @描述 物流跟踪，保存orderLogId到Session
     * @参数 param 传入参数, session
     * @返回值 请求成功
     * @创建人 郑宗伟
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
     * @描述 获取借卖方物流跟踪信息
     * @参数 session
     * @返回值 物流跟踪信息
     * @创建人 郑宗伟
     * @创建时间  2019/7/30 15:30
     * @修改人和其它信息
     */
    @PostMapping("/bvoOrderTracking")
    @ResponseBody
    public Map<String,Object> bvoOrderTracking(HttpSession session){
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

