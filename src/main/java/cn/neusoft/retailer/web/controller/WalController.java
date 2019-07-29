package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Wallet;
import cn.neusoft.retailer.web.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/23 21:44
 */
@Controller
public class WalController {
    @Autowired
    private WalletService walletService;
    @RequestMapping(value="/toAddWal",method= RequestMethod.GET)
    public String toAdd(){return "WalSingUp";}
    @RequestMapping("/toAddWal")
    public String toAddWal(Wallet wallet, Model model, HttpSession session){
        System.out.println(wallet.toString());
        model.addAttribute(wallet.toString());
        wallet.setWalBalance((float) 0);
        walletService.insert(wallet);
        return "success";
    }

    @GetMapping("/bvoWalletList")
    public String bvoWalletList(){
        return "bvo-WalletList";
    }

    @RequestMapping("/toWalList")
    @ResponseBody
    public List<Wallet> toWalList( HttpServletResponse response){
        List<Wallet> list = walletService.selectAll();
//        JSONObject jsonObject =new JSONObject();
//        JSONArray jsonArray = JSONArray.fromObject(list);

//        OutUtil.print(jsonArray, response);
        return list;
    }

    @RequestMapping(value="/EditWal",method= RequestMethod.POST,produces="application/json; charset:UTF-8")
    @ResponseBody
    public Map<String, Object> EditWal(@RequestBody String json){
        Map<String,Object> resultmap = new HashMap<String,Object>();
        org.json.JSONArray jsonArray = new org.json.JSONArray(json);
        System.out.println(jsonArray);
        int i=walletService.invest(jsonArray);
        if(i==7){
            resultmap.put("result","success");
        }
        else if(i==0) {
            resultmap.put("result","passError");

        }else if(i==3) {
            resultmap.put("result","unenough");
        }else
        {
                resultmap.put("result","error");
            }
        System.out.println(resultmap);

        return resultmap;
    }
}

