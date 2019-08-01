package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.LogisticsNode;
import java.util.List;

public interface LogisticsNodeMapper {
    int deleteByPrimaryKey(String logNodeId);

    int insert(LogisticsNode record);

    LogisticsNode selectByPrimaryKey(String logNodeId);

    List<LogisticsNode> selectAll();

    int updateByPrimaryKey(LogisticsNode record);

    List<LogisticsNode> selectByLogId(String logId);
}