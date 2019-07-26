package cn.neusoft.retailer.web.controller;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-23 13:27
 */


import cn.neusoft.retailer.web.pojo.Brand;
import cn.neusoft.retailer.web.pojo.User;
import cn.neusoft.retailer.web.service.BrandService;
import cn.neusoft.retailer.web.service.UserService;
import cn.neusoft.retailer.web.tools.Messages;
import cn.neusoft.retailer.web.tools.MvoType;
import cn.neusoft.retailer.web.tools.MyString;
import cn.neusoft.retailer.web.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyInfoController {
    @Autowired
    private UserService userService;

    @Autowired
    private BrandService brandService;


    //    登录之后进入的第一个控制器，判断当前用户是商家函数用户，商家直接显示公司信息
//    用户不关我事
    @RequestMapping(value = "login_show")
    public ModelAndView login_show(HttpServletRequest request, HttpServletResponse response) {
        //获得登录者信息
        User user = (User) request.getSession().getAttribute("user");
        //根据类型跳转
        if (user.getUserPrivilege() == 0) {

        } else if (user.getUserPrivilege() == 1) {

        } else if (user.getUserPrivilege() == 2) {

        }
        return null;
    }

    //   在user-company点击提交信息时进入的控制器
//    负责信息更新与维护
    @RequestMapping(value = "company")
    public ModelAndView update(User user) throws UnsupportedEncodingException {


        //中文乱码
        user.setUserName(new java.lang.String(user.getUserName().getBytes("ISO-8859-1"), "UTF-8"));


        //确定跳转页面
        ModelAndView modelAndView = new ModelAndView("user-company");


        //已通过前台校验，后台校验开始
        //判断是否汉字
        if (user.getUserName() == null || !MyString.ifChinese(user.getUserName())) {
            modelAndView.addObject("errorInfoChineseName", new java.lang.String("不是中文名或名字为空"));
            modelAndView.setViewName("user-company");
            return modelAndView;
        }
        //判断是否英文
        if (user.getMvoEngName() == null || !MyString.ifEnglish(user.getMvoEngName())) {
            modelAndView.addObject("errorInfoEnglishName", new java.lang.String("不是英文名或名字为空"));
            modelAndView.setViewName("user-company");
            return modelAndView;
        }
        //判断电话号码
        if (user.getUserPhone() == null || user.getUserPhone().length() > 11 || !MyString.ifNumber(user.getUserPhone())) {
            modelAndView.addObject("errorInfoTelephone", new java.lang.String("电话号码不全为数字或超过11位"));
            modelAndView.setViewName("user-company");
            return modelAndView;
        }

        //商家类型放入,返回界面时select下拉组件需要
        Map<Integer, java.lang.String> map_id = new HashMap<Integer, java.lang.String>();
        int index = 0;
        for (MvoType e : MvoType.values()) {
            map_id.put(index++, e.toString());
        }
        modelAndView.addObject("typeList", map_id);
//        测试案例
        System.out.println(user.toString());


        //上诉过程校验完毕，用service类写入数据库，返回结果
        //这里只更新中文名，英文名，电话，邮箱，简介公司类型，品牌商认证，其他不归我管
        //补全密码用户名做测试
        user.setUserPassword("bjwdehwjehjw");
        user.setUserId(123);
        if (userService.updateByPrimaryKey(user)) {
            modelAndView.addObject("msg", new Messages(1, "修改成功！", "ok"));
        } else {
            modelAndView.addObject("msg", new Messages(0, "修改失败！", "remove"));
        }

        return modelAndView;
    }

    //    显示公司信息
    @RequestMapping(value = "companyInfo/{pageNow}")
    @ResponseBody
    public Map<String, Object> companyInfoShow(@PathVariable("pageNow") Integer pageNow, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> hashMap = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        user = userService.selectByPrimaryKey(123);
        user.setUserPrivilege(1);
        //判断是否是品牌商 品牌商可以修改自己品牌
        if (user.getUserPrivilege() == 1) {
            List<User> userList = new LinkedList<>();
            List<String> mvoType = new LinkedList<>();
            HashMap<String, Object> hashMap1 = brandPage(pageNow, user.getUserId(), request);
            userList.add(user);
            for (int i = 0; i < userList.size(); i++) {
                for (MvoType e : MvoType.values()) {
                    if (userList.get(i).getMvoType() == e.ordinal()) {
                        mvoType.add("<span class=\"label label-primary\">" + e.name() + "</span>");
                    } else {
                        mvoType.add("<span class=\"label label-danger\">错误类型，无法匹配！</span>");
                    }
                }
            }

            hashMap.put("mvoType", mvoType);
            hashMap.put("companyList", userList);
            hashMap.put("brandList", hashMap1.get("brandList"));
            hashMap.put("pageBean", hashMap1.get("pageBean"));
            return hashMap;
        }
        //判断是否是管理员 管理员只能修改品牌商的东西
        if (user.getUserPrivilege() == 2) {

        }
        return null;
    }

    public HashMap<String, Object> brandPage(Integer pageNow, Integer userId, HttpServletRequest request) {
        PageBean pageBean = new PageBean(pageNow, PageBean.page_Size);
        pageBean.setTotallSize(brandService.selectCountBrand(userId));
        if (pageBean.getTotallSize() % pageBean.getPageSize() == 0) {
            pageBean.setTotallPage(pageBean.getTotallSize() / pageBean.getPageSize());
        } else {
            pageBean.setTotallPage(pageBean.getTotallSize() / pageBean.getPageSize() + 1);

        }
        List<Brand> brandList = brandService.selectByPage(userId, pageBean.getStart(), pageBean.getPageSize());
        for (int i = 0; i < brandList.size(); i++){
            brandList.get(i).setBrandUrl("." + brandList.get(i).getBrandUrl());
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("brandList", brandList);
        hashMap.put("pageBean", pageBean);
        return hashMap;
    }

    @RequestMapping(value = "companyDelete/{brandId}")
    @ResponseBody
    public HashMap<String, String> deleteBrand(@PathVariable("brandId") Integer brandId) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (brandService.deleteByPrimaryKey(brandId)) {
            hashMap.put("result", "true");
        } else {
            hashMap.put("result", "false");
        }
        return hashMap;
    }

    @RequestMapping(value = "companyUpdate")
    public ModelAndView update(HttpServletRequest request, @RequestParam("brandName") String brandName, @RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam(value = "brandId", required = false) Integer brandId) {
        ModelAndView modelAndView = new ModelAndView();
        String basePath = request.getServletContext().getRealPath("/");
        String pictureUrl = brandName + uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        String imageName = basePath + "/image/" + pictureUrl;
        try {
            uploadFile.transferTo(new File(imageName));
            //存入数据库
            Brand brand = brandService.selectByPrimaryKey(brandId);
            brand.setBrandName(brandName);
            brand.setBrandUrl("/image/" + pictureUrl);
            brandService.updateByPrimaryKey(brand);
            modelAndView.addObject("result", "1");
            System.out.println("已经修改！");
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("result", "0");
        }finally {
            modelAndView.setViewName("company_brand");
        }
        return modelAndView;
    }

}