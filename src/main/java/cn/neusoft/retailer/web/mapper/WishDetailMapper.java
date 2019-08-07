package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.WishDetail;
import java.util.List;
import java.util.Map;

public interface WishDetailMapper {
    int deleteByPrimaryKey(Integer wishDetailId);

    int insert(WishDetail record);

    WishDetail selectByPrimaryKey(Integer wishDetailId);

    List<WishDetail> selectByWishListId(Integer wishListId);

    //  List<WishDetail> selectByWishListId(Integer wishListId, Page page);

    List<WishDetail> selectAll();

    int updateByPrimaryKey(WishDetail record);

    int total(Integer wishListId);

    WishDetail selectByWishListIdAndGoodsId(Map<String,Object> map);
}