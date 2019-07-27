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
import cn.neusoft.retailer.web.tools.*;
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
    @ResponseBody
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

        //上诉过程校验完毕，用service类写入数据库，返回结果
        //这里只更新中文名，英文名，电话，邮箱，简介公司类型，品牌商认证，其他不归我管
        //补全密码用户名做测试
        if (userService.updateByPrimaryKey(user)) {
            modelAndView.addObject("msg", new Messages(1, "修改成功！", "ok"));
        } else {
            modelAndView.addObject("msg", new Messages(0, "修改失败！", "remove"));
        }

        return modelAndView;
    }

    //    显示公司信息 商品信息
    @RequestMapping(value = "/companyInfo/{pageNow}")
    @ResponseBody
    public Map<String, Object> companyInfoShow(@PathVariable("pageNow") Integer pageNow, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //登录者权限获得
        try {
            User user = (User) request.getSession().getAttribute("user");
            Map<String, Object> hashMap = new HashMap<>();
            user = userService.selectByPrimaryKey(123);
            user.setUserPrivilege(1);
            List<String> mvoType = new LinkedList<>();
            List<User> userList = new LinkedList<>();
            HashMap<String, Object> hashMap1 = null;
            //判断是否是品牌商 品牌商可以修改自己品牌
            if (user.getUserPrivilege() == 1) {
                hashMap1 = brandPage(pageNow, user.getUserId(), request);
                userList.add(user);
                for (int i = 0; i < userList.size(); i++) {
                    for (MvoType e : MvoType.values()) {
                        if (userList.get(i).getMvoType() != null && userList.get(i).getMvoType() == e.ordinal()) {
                            mvoType.add("<span class=\"label label-primary\">" + e.name() + "</span>");
                            break;
                        }
                    }
                    mvoType.add("<span class=\"label label-danger\">错误类型，无法匹配！</span>");
                }
            }
            //判断是否是管理员 管理员只能修改品牌商的东西
            if (user.getUserPrivilege() == 2) {
                //管理员的品牌商分页操作，不想写了
                hashMap1 = new HashMap<>();
                List<Brand> brandList = new LinkedList<>();
                hashMap1.put("brandList", brandList);
                userList = userService.selectAll();
                for (int i = 0; i < userList.size(); i++) {
                    String type = "<span class=\"label label-danger\">错误类型，无法匹配！</span>";

                    for (MvoType e : MvoType.values()) {
                        if (userList.get(i).getMvoType() != null && userList.get(i).getMvoType() == e.ordinal()) {
                            type = "<span class=\"label label-primary\">" + e.name() + "</span>";
                            break;
                        }
                    }
                    mvoType.add(type);
                }
            }
            hashMap.put("mvoType", mvoType);
            hashMap.put("companyList", userList);
            hashMap.put("brandList", hashMap1.get("brandList"));
            hashMap.put("pageBean", hashMap1.get("pageBean"));
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
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
        for (int i = 0; i < brandList.size(); i++) {
            //当前页面的请求使用绝对路径，使用相对路径会加上controller前缀
            brandList.get(i).setBrandUrl("http://localhost:8080/online_retailer" + brandList.get(i).getBrandUrl());
            //中文乱码
            try {
                if (brandList.get(i).getBrandName() != null)
                    brandList.get(i).setBrandName(new String(brandList.get(i).getBrandName().getBytes("ISO-8859-1"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                System.out.println("中文乱码问题！");
            }
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
    public ModelAndView update(HttpServletRequest request, @RequestParam(value = "brandName", required = false) String brandName, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, @RequestParam(value = "brandId", required = false) Integer brandId) {
        ModelAndView modelAndView = new ModelAndView();
        String basePath = request.getServletContext().getRealPath("/");
        String pictureUrl = brandId + uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        String imageName = basePath + "image/" + pictureUrl;
        System.out.println(imageName);
        try {
            Brand brand = brandService.selectByPrimaryKey(brandId);
            //删除之前的
            String OriginUrl = basePath + brand.getBrandUrl();
            File file = new File(OriginUrl);
            file.delete();
            //存入数据库
            uploadFile.transferTo(new File(imageName));
//            String OriginUrl = basePath + brand.getBrandUrl();
//            File file = new File(OriginUrl);
//            if (file != null)
//                file.delete();
            brand.setBrandName(brandName);
            brand.setBrandUrl("/image/" + pictureUrl);
            brandService.updateByPrimaryKey(brand);
            modelAndView.addObject("result", "1");
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("result", "0");
        } finally {
            modelAndView.setViewName("company_brand");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/companyAdd/{brandMerId}")
    public ModelAndView add(HttpServletRequest request, @RequestParam(value = "brandName", required = false) String brandName, @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, @PathVariable("brandMerId") Integer brandMerId) {
        System.out.println(uploadFile + " " + brandMerId + " " + brandName);
        ModelAndView modelAndView = new ModelAndView();
        String basePath = request.getServletContext().getRealPath("/");
        String pictureUrl = brandName + uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
        String imageName = basePath + "image/" + pictureUrl;
        try {
            uploadFile.transferTo(new File(imageName));
            //创建新的brand
            Brand brand = new Brand();
            brand.setBrandName(brandName);
            brand.setBrandUrl("/image/" + pictureUrl);
            brand.setBrandMerId(brandMerId);
            brand.setBrandId(Integer.valueOf(UniqueID.createID().substring(UniqueID.createID().length() - 6)));
            brandService.insert(brand);
            modelAndView.addObject("result", "1");
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.addObject("result", "0");
        } finally {
            modelAndView.setViewName("company_brand");
        }
        return modelAndView;
    }

    //    点击修改先进入这个控制器，拿到id，得到user信息然后信息带到前台
    @RequestMapping("defaultUser/{userId}")
    @ResponseBody
    public ModelAndView defaultUser(@PathVariable("userId") Integer userId) {
        Integer user_Id = userId;
        ModelAndView modelAndView = new ModelAndView("user-company");
        try {
            User user = userService.selectByPrimaryKey(userId);
            modelAndView.addObject("user", user);
            //商家类型放入,返回界面时select下拉组件需要
            Map<Integer, java.lang.String> map_id = new HashMap<Integer, java.lang.String>();
            int index = 0;
            for (MvoType e : MvoType.values()) {
                map_id.put(index++, e.toString());
            }
            modelAndView.addObject("typeList", map_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }


}