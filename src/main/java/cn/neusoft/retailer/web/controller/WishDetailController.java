package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Goods;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.pojo.UserWishlist;
import cn.neusoft.retailer.web.pojo.WishDetail;
import cn.neusoft.retailer.web.service.GoodsService;
import cn.neusoft.retailer.web.service.UserWishlistService;
import cn.neusoft.retailer.web.service.WishDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wishdetail")
public class WishDetailController {
    @Autowired
    WishDetailService wishDetailService;
   @Autowired
   GoodsService goodsService;
   @Autowired
   UserWishlistService userWishlistService;

    @ResponseBody
    @RequestMapping("/showWishDetailjsp")
    public HashMap<String, Object> showWishDetail(HttpSession session, @RequestParam(value = "WishListId", defaultValue = "-1")Integer WishListId, @RequestParam(value = "page", defaultValue = "1")String page){
        //System.out.println(WishListId);
        if(WishListId!=-1)
            session.setAttribute("WishListId",WishListId);
       /// int WishListId1= (int) session.getAttribute("WishListId");
        Integer WishListId1= ((User)session.getAttribute("user")).getUserId();
        //判断用户是否有心愿单，如果没有跳转创建，如果有就继续
        System.out.println(WishListId1);
        if(userWishlistService.existByBvoId(WishListId1)){
            ModelAndView mav = new ModelAndView();
        }
        else{
            UserWishlist userWishlist=new UserWishlist();
            userWishlist.setWishlistId(WishListId1);
            userWishlist.setBvoId(WishListId1);
            userWishlistService.insert(userWishlist);

            session.setAttribute("WishListId",WishListId1);


        }
            List<WishDetail> wishDetailList=  wishDetailService.selectByWishListId(WishListId1);
         /*
        int total=wishDetailService.total(WishListId1);
            //System.out.println(total);

            double t=total;
            double c=7;
            double num=t/c;
            int pagecount= (int) Math.ceil(num);
            //System.out.println(pagecount);

            int lastnum=total%7;
           // System.out.println(lastnum);
            List<Integer> pages=new ArrayList<>();
            for(int i=1;i<=pagecount;i++)
                pages.add(i);
            //分页
            int pagenum=Integer.parseInt(page);
            int start=0+(pagenum-1)*7;
          //  System.out.println(start);
            List<WishDetail> wishDetails=new ArrayList<>();
            if(pagenum!=pagecount) {
                for (int i = 0; i <= 6; i++) {
                    WishDetail wishDetail = wishDetailList.get(start);

                    wishDetails.add(wishDetail);
                    start++;
                }
            }
            else if(pagenum==pagecount&&lastnum==0) {
                for (int i = 0; i <= 6; i++) {
                    WishDetail wishDetail = wishDetailList.get(start);

                    wishDetails.add(wishDetail);
                    start++;
                }
            }
            else if(pagenum==pagecount) {
                for (int i = 0; i < lastnum; i++) {
                    WishDetail wishDetail = wishDetailList.get(start);
                   // System.out.println(wishDetail.getWishDetailId());
                    wishDetails.add(wishDetail);
                    start++;
                }
            }*/
            List<Goods> goods=new ArrayList<Goods>();
            for(WishDetail wd:wishDetailList)
            {
                int goodsid=wd.getWishGoodsId();
                Goods good=goodsService.selectByPrimaryKey(goodsid);
                goods.add(good);
            }
         /*   // 放入转发参数
            mav.addObject("wishDetailList",wishDetails);
            mav.addObject("goods",goods);
            mav.addObject("count",pages);
            // 放入jsp路径
            mav.setViewName("testshowWishDetailjsp");
            return mav;*/
            HashMap<String, Object> hashMap = new HashMap<>();
        System.out.println(wishDetailList);
        System.out.println(goods);
            hashMap.put("wishDetailList",wishDetailList);
            hashMap.put("goods",goods);
            return hashMap;


    }

    @ResponseBody
    @PostMapping("/getbvoid")
    public boolean getbvoid(@RequestBody Map<String,Object> param, HttpSession session){
        try{

            Integer WishListId=(Integer)param.get("WishListId");
            session.setAttribute("WishListId",WishListId);
            session.setAttribute("bvoId",WishListId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @ResponseBody
    @PostMapping("/getwishDetailId")
    public boolean getwishDetailId(@RequestBody Map<String,Object> param, HttpSession session){
        try{
            Integer wishDetailId=(Integer)param.get("wishDetailId");

            session.setAttribute("wishDetailId",wishDetailId);
            System.out.println(wishDetailId+"0");
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @ResponseBody
    @PostMapping("/getgoodsid")
    public boolean getgoodsid(@RequestBody Map<String,Object> param, HttpSession session){
        try{
            Integer goodsId=(Integer)param.get("goodsId");
            session.setAttribute("goodsId",goodsId);
            ///int bvoId= (int) session.getAttribute("bvoId");
            Integer bvoId= ((User)session.getAttribute("user")).getUserId();
            System.out.println("getgoodsid"+bvoId);
            UserWishlist userWishlist=userWishlistService.selectByBvoId(bvoId);
            if(userWishlist==null){
                UserWishlist userWishlist1=new UserWishlist();
                userWishlist1.setBvoId(bvoId);
                userWishlist1.setWishlistId(bvoId);
                userWishlistService.insert(userWishlist1);
                System.out.println("创建心愿单");
            }
            session.setAttribute("WishlistId",bvoId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ModelAndView deleteWishDetail(HttpSession session){
        Integer wishDetailId= (Integer) session.getAttribute("wishDetailId");

        System.out.println(wishDetailId+"1");
        WishDetail wishDetail1=wishDetailService.selectByPrimaryKey(wishDetailId);
        System.out.println(wishDetailId+"2");
        int WishListId=wishDetail1.getWishListId();
        System.out.println(wishDetailId+"3");
        String wl= String.valueOf(WishListId);
        System.out.println(wishDetailId+"4");
        wishDetailService.deleteByPrimaryKey(wishDetailId);
        System.out.println(wishDetailId+"5");
        //ModelAndView mav = new ModelAndView("redirect:/showWishDetailjsp?WishListId="+wl);
        System.out.println("OK");
        ModelAndView mav=new ModelAndView("redirect:/html/wishlist.html");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/look")
    public HashMap<String, Object> look(HttpSession session){
        HashMap<String, Object> hashMap=null;
        try {
            ///Integer bvoId = (Integer) session.getAttribute("bvoId");
            Integer bvoId= ((User)session.getAttribute("user")).getUserId();
            System.out.println("look"+bvoId);
            Integer goodsId = (Integer) session.getAttribute("goodsId");
            Goods goods = goodsService.selectByPrimaryKey(goodsId);
           /// Integer WishListId = (Integer) session.getAttribute("WishListId");
            Integer WishListId= ((User)session.getAttribute("user")).getUserId();
            System.out.println("look"+WishListId);
            hashMap = new HashMap<>();
            hashMap.put("WishListId", WishListId);
            hashMap.put("goods", goods);
        }catch (Exception e){
            e.printStackTrace();
        }

        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/wishgoods")
    public ModelAndView wishgoods(HttpSession session){
        //sesssion
        Integer goodsId= (Integer) session.getAttribute("goodsId");
        ///Integer wishlistId= (Integer) session.getAttribute("bvoId");
        Integer wishlistId= ((User)session.getAttribute("user")).getUserId();
        //判断重复添加
        Map<String,Object> params = new HashMap<>();
        params.put("wishListId", wishlistId);
        params.put("wishGoodsId", goodsId);
    System.out.println(params.values());
    System.out.println(params.get("wishGoodsId"));
        System.out.println(wishDetailService.selectByWishListIdAndGoodsId(params));
        if(wishDetailService.selectByWishListIdAndGoodsId(params)==null) {
            WishDetail wishDetail = new WishDetail();
            wishDetail.setWishListId(wishlistId);
            wishDetail.setWishGoodsId(goodsId);
            System.out.println(wishDetail);
            wishDetailService.insert(wishDetail);
            ModelAndView mav = new ModelAndView("redirect:/html/wishlist.html");
            return mav;
        }
        else {
            System.out.println("重复添加");
            ModelAndView mav = new ModelAndView("redirect:/html/wishlist.html");
            return mav;
        }

    }


}
