package cn.neusoft.retailer.web.controller;

import cn.neusoft.retailer.web.pojo.Brand;
import cn.neusoft.retailer.web.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-27 13:16
 */
@Controller
@RequestMapping("Brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //品牌搜索
    @RequestMapping("brandSearchResult/{search_name}")
    @ResponseBody
    public HashMap<String, Object> SearchResult(@PathVariable(value = "search_name", required = false) String search_name) throws UnsupportedEncodingException {
        System.out.println("进入搜索" + search_name);
        List<Brand> brandList = brandService.selectByBrandSearch(search_name);
        for (int i = 0; i < brandList.size(); i++) {
            brandList.get(i).setBrandUrl("http://localhost:8080/online_retailer" + brandList.get(i).getBrandUrl());
            if (brandList.get(i).getBrandName() != null)
                brandList.get(i).setBrandName(new String(brandList.get(i).getBrandName().getBytes("ISO-8859-1"), "UTF-8"));
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("brandList", brandList);
        System.out.println(hashMap);
        return hashMap;
    }

    //品牌跳转
    @RequestMapping("brandSearch")
    public String SearchJump() {
        System.out.println("开始跳转");
        return "search";
    }
}
