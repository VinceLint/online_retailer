package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Dropship;
import cn.neusoft.retailer.web.pojo.Goods;
import cn.neusoft.retailer.web.pojo.Store;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.DropshipService;
import cn.neusoft.retailer.web.service.GoodsService;
import cn.neusoft.retailer.web.service.StoreService;
import cn.neusoft.retailer.web.tools.StoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dropship")
public class DropshipController {
    @Autowired
    DropshipService dropshipService;
    @Autowired
    StoreService storeService;
    @Autowired
    GoodsService goodsService;

    @ResponseBody
    @PostMapping("/dropshipgoods")
    public HashMap<String,Object> dropshipgoods(HttpSession session) {
        HashMap<String,Object>hashMap=new HashMap<>();
       Goods goods= (Goods) session.getAttribute("goods");
       int goodsId= (int) session.getAttribute("goodsId");
       String goodsTitle= (String) session.getAttribute("goodsTitle");
        Dropship dropship = new Dropship();
        //int bvoId = (int) session.getAttribute("bvoId");
        Integer bvoId= ((User)session.getAttribute("user")).getUserId();
        System.out.println("借卖方ID："+bvoId);
        dropship.setBvoId(bvoId);
        dropship.setGoodsId(goodsId);


        //dropshipService.insert(dropship);
       /*Map<String,Object> params = new HashMap<>();
       params.put("bvoId", bvoId);
       params.put("goodsId", goodsId);
        Dropship dropship1=dropshipService.selectByBvoIdAndGoodsId(params);
        //int dropshipId=dropship1.getDropshipId();
        System.out.println(dropship1.toString());
*/

        // session.setAttribute("dropshipId",dropshipId);
//////////////////////////////////////////////////////////////////
        Map<String,Object> params = new HashMap<>();
        params.put("bvoId", bvoId);
        params.put("goodsId", goodsId);

       // if (dropshipService.existByByBvoIdAndGoodsId(params))


           // System.out.println("false");


            Map<String, Object> emap = new HashMap<>();
            emap.put("bvoId", bvoId);
            String te = "e";
            emap.put("storeType", StoreType.eBay.ordinal());
            List<Store> e = storeService.selectByBvoIdAndType(emap);
            Map<String, Object> amap = new HashMap<>();
            emap.put("bvoId", bvoId);

            emap.put("storeType", StoreType.Amazon.ordinal());
            List<Store> a = storeService.selectByBvoIdAndType(emap);
           hashMap.put("a",a);
           hashMap.put("e",e);
            hashMap.put("goodsTitle",goodsTitle);



        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/onsellgooods")
    public Boolean onsellgooods(@RequestBody Map<String,Object> param, HttpSession session) {
        System.out.println(param.get("choose")+"!!!!!!!!!!!!!!!!!!!!!!");
        try{
            List<Integer>storeId= (List<Integer>) param.get("choose");
            //System.out.println(storeId+"1");
            Integer goodsId= (Integer) session.getAttribute("goodsId");
           // System.out.println(goodsId+"2");
           /// Integer bvoId= (Integer) session.getAttribute("bvoId");
            Integer bvoId= ((User)session.getAttribute("user")).getUserId();
           // System.out.println(bvoId+"3");
            Dropship dropship = new Dropship();
            for (Integer id : storeId) {
                int storeid = id;
                dropship.setStoreId(storeid);
                dropship.setGoodsId(goodsId);

                dropship.setBvoId(bvoId);
          /*  Map<String, Object> params = new HashMap<>();
            params.put("bvoId", bvoId);
            params.put("goodsId", goodsId);
            if (dropshipService.existByByBvoIdAndGoodsId(params)) {*/
                dropshipService.insert(dropship);

          /*  } else {
                ModelAndView modelAndView = new ModelAndView("redirect:/lookGooodsagain");
                System.out.println("false");
                return modelAndView;
            }


        }
                return null;*/
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


        return true;
    }

    @ResponseBody
    @PostMapping("/getgoodsTitle")
    public boolean getgoodsTitle(@RequestBody Map<String,Object> param, HttpSession session){
        try{
            ///int bvoId=(int)session.getAttribute("bvoId");
            Integer bvoId= ((User)session.getAttribute("user")).getUserId();
            System.out.println("借卖方ID:"+bvoId);
            int goodsId= (int) session.getAttribute("goodsId");
            //System.out.println(param.get("goodsTitle")+"   getgoods");
            String goodsTitle =(String) param.get("goodsTitle");
            session.setAttribute("goodsTitle",goodsTitle);


        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        ///int bvoId=(int)session.getAttribute("bvoId");
        Integer bvoId= ((User)session.getAttribute("user")).getUserId();
        //判断有没网店
        System.out.println(bvoId);
        if (storeService.selectByBvoId(bvoId).isEmpty()){
           System.out.println("没店铺");
            return false;
        }
        //如果重复发布，则清楚该商品此前记录

        int goodsId= (int) session.getAttribute("goodsId");
       // System.out.println(goodsId+"del1");
        Map<String,Object> params = new HashMap<>();
        params.put("bvoId", bvoId);
        params.put("goodsId", goodsId);
       // System.out.println(params+"del2");
        List<Dropship> del =  dropshipService.selectByBvoIdAndGoodsId(params);
       // System.out.println(del+"del2");
        if(!del.isEmpty()){
            for(Dropship dropship:del){
                dropshipService.deleteByPrimaryKey(dropship.getDropshipId());
            }
        }
        return true;
    }

    @ResponseBody
    @PostMapping("/getbvoidgoods")
    public boolean getbvoidgoods(@RequestBody Map<String,Object> param, HttpSession session){
        try{
            //session.removeAttribute("bvoId");
            System.out.println("getbvoidgoods旧："+session.getAttribute("bvoId"));
            Integer bvoId=(Integer)param.get("bvoId");
            session.setAttribute("bvoId",bvoId);
            System.out.println("getbvoidgoods新："+session.getAttribute("bvoId"));
            session.setAttribute("WishListId",bvoId);
            Integer bvoId1= (Integer) session.getAttribute("WishListId");
            session.setAttribute("bvoId",bvoId1);
            System.out.println("getbvoidgoods新："+session.getAttribute("bvoId"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/lookGooods")
    public HashMap<String,Object> lookGoods(HttpSession session){
        /// int bvoId = (int) session.getAttribute("bvoId");
        Integer bvoId= ((User)session.getAttribute("user")).getUserId();
        System.out.println("lookGoods"+bvoId);
        ///int WishListId = (int) session.getAttribute("WishListId");
        // Integer WishListId= ((User)session.getAttribute("user")).getUserId();
        // System.out.println("lookGoodsWishListId"+WishListId);
        String goodsStatus="2";
        List<Goods> goods=goodsService.selectByGoodsStatus(goodsStatus);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("goods",goods);
        return hashMap;
    }


}
