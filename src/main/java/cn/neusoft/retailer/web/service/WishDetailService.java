package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.WishDetail;

import java.util.List;
import java.util.Map;

public interface WishDetailService {
    boolean deleteByPrimaryKey(Integer wishDetailId);

    boolean insert(WishDetail record);

    WishDetail selectByPrimaryKey(Integer wishDetailId);

    List<WishDetail> selectByWishListId(Integer wishListId);

   // List<WishDetail> selectByWishListId(Integer wishListId, Page page);

    List<WishDetail> selectAll();

    boolean updateByPrimaryKey(WishDetail record);

    int total(Integer wishListId);

    WishDetail selectByWishListIdAndGoodsId(Map<String, Object> map);
}
