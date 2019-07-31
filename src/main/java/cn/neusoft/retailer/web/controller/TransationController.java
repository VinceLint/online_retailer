package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.TransactionRecord;
import cn.neusoft.retailer.web.service.TransationService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/7/30 22:37
 */
@Controller
public class TransationController {
    @Autowired
    private TransationService transationService;

    @RequestMapping("/toTransation")
    public String toTra(){
        return "html/bvo-transation.html";
    }

    @RequestMapping(value = "/toTransationList", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONArray init( ) throws Exception{
        JSONArray jsonArray=null;

            List<TransactionRecord> transactionlist = transationService.getAll(1);

            jsonArray = JSONArray.fromObject(transactionlist);
        System.out.println(jsonArray);


        return jsonArray;
    }

    @RequestMapping(value = "changeTransationList/{page}",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray changeList(@PathVariable("page") String page){

        System.out.println("page"+page);
        int p=Integer.valueOf(page);
        p-=1;

        JSONArray jsonArray= transationService.getAll(p);

        return jsonArray;
    }

    @RequestMapping("/BrandTransation")
    public String toBrand(){return "html/brand-transation.html";}
}
