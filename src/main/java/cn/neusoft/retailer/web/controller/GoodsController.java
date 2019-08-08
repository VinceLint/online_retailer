package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Goods;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.GoodsService;
import cn.neusoft.retailer.web.tools.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/Goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/insert")
    public String insert() {
        /*return "testPic";*/
        System.out.println("return");
        return "insert";
    }

    @RequestMapping("/searchGoods")
    @ResponseBody
    public List<Goods> search(HttpServletRequest request) {
        /*HttpSession session = request.getSession(true);
        String[] names = session.getValueNames();
        for(int i = 0;i < names.length;i++){
            System.out.println(names[i] + "," + session.getValue(names[i]));
        }*/
        HttpSession session = request.getSession(true);
        Integer brandUserId= ((User)session.getAttribute("user")).getUserId();
        /*System.out.println(brandUserId);*/

        return goodsService.selectByBrandId(brandUserId);
    }

    @RequestMapping("/queryInsert")
    public String queryInsert(HttpServletRequest request, String title, int brandId, double price, int amount, int clazz,
                              String describe, double length, double width, double height, double weight,
                              @RequestParam CommonsMultipartFile file, HttpSession session) throws IOException {

        //处理中文乱码问题
        request.setCharacterEncoding("UTF-8");

        Integer id = null;

        //显示从前端表单获取到的数据
        System.out.println(title + " " + id + " " + price + " " + amount + " " + clazz +
                " " + describe + " " + length + " " + width + " " + height + " " + weight);

        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename()); //文件名
        System.out.println(file.getSize());
        System.out.println(file.getName());  //文件对应的属性名


        //获取文件名后缀
        int index = file.getOriginalFilename().lastIndexOf(".");
        String substring = file.getOriginalFilename().substring(index);

        //确定上传路径
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/goods_images");
        //变成程序中的路径
        File uploadPath = new File(realPath);

        String fileName = null;

        if (file.getOriginalFilename() == "") {
            System.out.println("There is not any pics");
        } else {
            //确认最终的路径  /文件夹/文件名
            fileName = UUID.randomUUID().toString() + substring;
            uploadPath = new File(uploadPath + "/" + fileName);
            System.out.println(uploadPath);
            System.out.println(fileName);

            //开始上传
            file.transferTo(uploadPath);
            FtpUtils ftpUtils = new FtpUtils();
            ftpUtils.uploadFileForNginx("/usr/local/nginx/html/online_retailer/goods_images",fileName,uploadPath);
            uploadPath.delete();
        }

        //将数据添加到goods表
        goodsService.save(title, id, price, amount, clazz, describe, length, width, height, weight, brandId, fileName);
        System.out.println("save!!!");
        return "insert";
    }

    @RequestMapping("/queryUpdate")
    public String queryUpdate(HttpServletRequest request, int goodsId, String title, int brandId, double price, int amount, int clazz,
                              String describe, double length, double width, double height, double weight,
                              @RequestParam CommonsMultipartFile file, HttpSession session) throws IOException {
        //处理中文乱码问题
        request.setCharacterEncoding("UTF-8");

        //显示从前端表单获取到的数据
        System.out.println(title + " " + goodsId + " " + price + " " + amount + " " + clazz +
                " " + describe + " " + length + " " + width + " " + height + " " + weight);

        //获取文件名后缀
        int index = file.getOriginalFilename().lastIndexOf(".");
        String substring = file.getOriginalFilename().substring(index);

        //确定上传路径
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/goods_images");
        //变成程序中的路径
        File uploadPath = new File(realPath);

        String fileName = null;

        if (file.getOriginalFilename() == "") {
            System.out.println("There is not any pics");
        } else {
            //确认最终的路径  /文件夹/文件名
            fileName = UUID.randomUUID().toString() + substring;
            uploadPath = new File(uploadPath + "/" + fileName);
            System.out.println(uploadPath);
            System.out.println(fileName);

            //开始上传
            file.transferTo(uploadPath);
            FtpUtils ftpUtils = new FtpUtils();
            Goods goods = goodsService.selectByPrimaryKey(goodsId);
            if(!goods.getGoodsPic().equals("")){
                ftpUtils.deleteFile("/usr/local/nginx/html/online_retailer/goods_images",goods.getGoodsPic());
            }
            ftpUtils.uploadFileForNginx("/usr/local/nginx/html/online_retailer/goods_images",fileName,uploadPath);
            uploadPath.delete();
        }
        goodsService.updateByGoodsId(goodsId, title, price, amount, clazz, describe, length, width,
                height, weight, brandId, fileName);
        return "insert";
    }

    @RequestMapping("/queryDelete/{goodsId}")
    public String queryDelete(@PathVariable int goodsId) {
        System.out.println(goodsId);
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        if(goods.getGoodsPic().equals("")){
            //donoting
        }else{
            FtpUtils ftpUtils = new FtpUtils();
            ftpUtils.deleteFile("/usr/local/nginx/html/online_retailer/goods_images",goods.getGoodsPic());
        }
        goodsService.deleteByGoodsId(goodsId);

        return "insert";
    }

    @RequestMapping("/putin/{goodsId}")
    public String putin(@PathVariable int goodsId) {
        System.out.println(goodsId);
        goodsService.updateStatus0ByPrimaryKey(goodsId);
        return "insert";
    }

    @RequestMapping("/onshelves/{goodsId}")
    public String onshelves(@PathVariable int goodsId) {
        System.out.println(goodsId);
        goodsService.updateStatus1ByPrimaryKey(goodsId);
        return "insert";
    }

    @RequestMapping("/offshelves/{goodsId}")
    public String offshelves(@PathVariable int goodsId) {
        System.out.println(goodsId);
        goodsService.updateStatus2ByPrimaryKey(goodsId);
        return "insert";
    }

}
