package cn.neusoft.retailer.web.controller;

/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/23 13:30
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BrandOrderController {

    @GetMapping("/brandOrderList")
    public String brandOrderList(){

        return "brandOrderList";
    }
}
