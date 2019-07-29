package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Brand;

import java.util.List;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-24 22:44
 */
public interface BrandService {
    boolean deleteByPrimaryKey(Integer brandId);

    boolean insert(Brand brand);

    Brand selectByPrimaryKey(Integer brandId);

    List<Brand> selectAll();

    boolean updateByPrimaryKey(Brand record);

    List<Brand> selectByMerId(Integer brandId);

    int selectCountBrand(Integer brandId);

    List<Brand> selectByPage(Integer brandMerId, Integer pageStart, Integer pageSize);

    List<Brand> selectByBrandSearch(String brandName);
}
