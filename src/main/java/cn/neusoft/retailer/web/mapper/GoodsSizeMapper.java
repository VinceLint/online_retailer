package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.GoodsSize;
import java.util.List;

public interface GoodsSizeMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(GoodsSize record);

    GoodsSize selectByPrimaryKey(Integer goodsId);

    List<GoodsSize> selectAll();

    int updateByPrimaryKey(GoodsSize record);
}