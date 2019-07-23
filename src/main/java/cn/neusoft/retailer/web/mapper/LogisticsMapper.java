package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Logistics;
import java.util.List;

public interface LogisticsMapper {
    int deleteByPrimaryKey(String logId);

    int insert(Logistics record);

    Logistics selectByPrimaryKey(String logId);

    List<Logistics> selectAll();

    int updateByPrimaryKey(Logistics record);
}