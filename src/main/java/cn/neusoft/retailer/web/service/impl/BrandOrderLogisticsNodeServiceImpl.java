package cn.neusoft.retailer.web.service.impl;
/*
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/29 21:23
 */
import cn.neusoft.retailer.web.mapper.LogisticsNodeMapper;
import cn.neusoft.retailer.web.pojo.LogisticsNode;
import cn.neusoft.retailer.web.service.BrandOrderLogisticsNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandOrderLogisticsNodeServiceImpl implements BrandOrderLogisticsNodeService {
    @Autowired
    private LogisticsNodeMapper logisticsNodeMapper;

    @Override
    public boolean deleteByPrimaryKey(String logNodeId) {
        return logisticsNodeMapper.deleteByPrimaryKey(logNodeId)>0;
    }

    @Override
    public boolean insert(LogisticsNode record) {
        return logisticsNodeMapper.insert(record)>0;
    }

    @Override
    public LogisticsNode selectByPrimaryKey(String logNodeId) {
        return logisticsNodeMapper.selectByPrimaryKey(logNodeId);
    }

    @Override
    public List<LogisticsNode> selectAll() {
        return logisticsNodeMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(LogisticsNode record) {
        return logisticsNodeMapper.updateByPrimaryKey(record)>0;
    }

    @Override
    public List<LogisticsNode> selectByLogId(String log_id) {
        return logisticsNodeMapper.selectByLogId(log_id);
    }
}
