package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Wallet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/23 21:44
 */
@Controller
public class WalController {
    @RequestMapping(value="/toAddWal",method= RequestMethod.GET)
    public String toAdd(){return "WalSingUp";}
    @RequestMapping("/toAddWal")
    public String toAddWal(Wallet wallet, Model model, HttpSession session){
        System.out.println(wallet.toString());
        model.addAttribute(wallet.toString());
        return "redirect:success";
    }
}
