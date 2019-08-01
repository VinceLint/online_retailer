package cn.neusoft.retailer.web.service.impl;
/*
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/29 14:42
 */
import cn.neusoft.retailer.web.mapper.LogisticsMapper;
import cn.neusoft.retailer.web.pojo.Logistics;
import cn.neusoft.retailer.web.service.BrandOrderLogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandOrderLogisticsServiceImpl implements BrandOrderLogisticsService {
    @Autowired
    private LogisticsMapper logisticsMapper;

    @Override
    public boolean deleteByPrimaryKey(String logId) {
        return logisticsMapper.deleteByPrimaryKey(logId)>0;
    }

    @Override
    public boolean insert(Logistics record) {
        return logisticsMapper.insert(record)>0;
    }

    @Override
    public Logistics selectByPrimaryKey(String logId) {
        return logisticsMapper.selectByPrimaryKey(logId);
    }

    @Override
    public List<Logistics> selectAll() {
        return logisticsMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(Logistics record) {
        return logisticsMapper.updateByPrimaryKey(record)>0;
    }
}
