package cn.neusoft.retailer.web.service;
/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/29 21:22
 */
import cn.neusoft.retailer.web.pojo.LogisticsNode;

import java.util.List;


public interface BrandOrderLogisticsNodeService {
    boolean deleteByPrimaryKey(String logNodeId);

    boolean insert(LogisticsNode record);

    LogisticsNode selectByPrimaryKey(String logNodeId);

    List<LogisticsNode> selectAll();

    boolean updateByPrimaryKey(LogisticsNode record);

    List<LogisticsNode> selectByLogId(String log_id);
}
