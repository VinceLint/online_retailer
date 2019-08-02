package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.DropshipMapper;
import cn.neusoft.retailer.web.pojo.Dropship;
import cn.neusoft.retailer.web.service.BrandOrderDropshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/8/2 10:31
 */
@Service
@Transactional
public class BrandOrderDropshipServiceImpl implements BrandOrderDropshipService {
    @Autowired
    private DropshipMapper dropshipMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer dropshipId) {
        return dropshipMapper.deleteByPrimaryKey(dropshipId)>0;
    }

    @Override
    public boolean insert(Dropship record) {
        return dropshipMapper.insert(record)>0;
    }

    @Override
    public Dropship selectByPrimaryKey(Integer dropshipId) {
        return dropshipMapper.selectByPrimaryKey(dropshipId);
    }

    @Override
    public List<Dropship> selectAll() {
        return dropshipMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(Dropship record) {
        return dropshipMapper.updateByPrimaryKey(record)>0;
    }
}
