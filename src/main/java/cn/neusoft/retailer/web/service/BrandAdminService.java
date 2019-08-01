package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Brand;

import java.util.List;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-23 09:09
 */
public interface BrandAdminService {
    int deleteByBrandId(Integer BrandId);
    Brand selectByBrandName(String Name);
    Brand selectByBrandId(Integer BrandId);
    List<Brand> selectAll();
}
