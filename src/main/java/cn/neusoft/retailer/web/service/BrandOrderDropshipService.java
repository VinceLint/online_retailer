package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Dropship;

import java.util.List;

/**
 * @author 胡献涛
 * @version 1.0
 * @date 2019/8/2 10:29
 */
public interface BrandOrderDropshipService {
    boolean deleteByPrimaryKey(Integer dropshipId);

    boolean insert(Dropship record);

    Dropship selectByPrimaryKey(Integer dropshipId);

    List<Dropship> selectAll();

    boolean updateByPrimaryKey(Dropship record);
}
