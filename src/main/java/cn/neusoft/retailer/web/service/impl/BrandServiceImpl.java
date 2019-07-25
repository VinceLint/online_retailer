package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.BrandMapper;
import cn.neusoft.retailer.web.pojo.Brand;
import cn.neusoft.retailer.web.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-24 22:47
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public boolean deleteByPrimaryKey(Integer brandId) {
        return brandMapper.deleteByPrimaryKey(brandId) != 0;
    }

    @Override
    public boolean insert(Brand brand) {
        return brandMapper.insert(brand) != 0;
    }

    @Override
    public Brand selectByPrimaryKey(Integer brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }

    @Override
    public List<Brand> selectAll() {
        return brandMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(Brand record) {
        return brandMapper.updateByPrimaryKey(record) != 0;
    }

    @Override
    public List<Brand> selectByMerId(Integer brandMerId) {
        return brandMapper.selectBymerId(brandMerId);
    }

    @Override
    public int selectCountBrand(Integer brandId) {
        return null;
    }
}
