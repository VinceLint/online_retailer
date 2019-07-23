package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.WishDetail;
import java.util.List;

public interface WishDetailMapper {
    int deleteByPrimaryKey(Integer wishDetailId);

    int insert(WishDetail record);

    WishDetail selectByPrimaryKey(Integer wishDetailId);

    List<WishDetail> selectAll();

    int updateByPrimaryKey(WishDetail record);
}