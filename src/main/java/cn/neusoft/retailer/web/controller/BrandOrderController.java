package cn.neusoft.retailer.web.controller;

/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/23 13:30
 */

import cn.neusoft.retailer.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
public class BrandOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    @ResponseBody
    public List<Map<String,Object>> getOrders(){
        return orderService.selectByBrandUserId(1);
    }
}
