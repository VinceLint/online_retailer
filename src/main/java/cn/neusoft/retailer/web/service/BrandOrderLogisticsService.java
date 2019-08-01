package cn.neusoft.retailer.web.service;
/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/7/29 14:42
 */
import cn.neusoft.retailer.web.pojo.Logistics;

import java.util.List;


public interface BrandOrderLogisticsService {
    boolean deleteByPrimaryKey(String logId);

    boolean insert(Logistics record);

    Logistics selectByPrimaryKey(String logId);

    List<Logistics> selectAll();

    boolean updateByPrimaryKey(Logistics record);
}
