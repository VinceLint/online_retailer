package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    Goods selectByPrimaryKey(Integer goodsId);

    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);

    int updateStatus0ByPrimaryKey(Integer goodsId);

    int updateStatus1ByPrimaryKey(Integer goodsId);

    int updateStatus2ByPrimaryKey(Integer goodsId);

    List<Goods> selectByBrandId(Integer brandId);

    int updatePicByPrimaryKey(@Param("goodsId") Integer goodsId, @Param("goodsPic") String goodsPic);
}