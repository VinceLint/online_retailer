package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Brand;
import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer brandId);

    int insert(Brand record);

    Brand selectByPrimaryKey(Integer brandId);

    List<Brand> selectByCompany(Integer brandComid);

    Brand selectByName(String brand_name);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);

    List<Brand> selectBymerId(Integer brandMerId);

    int selectCountBrand(Integer brandMerId);
}