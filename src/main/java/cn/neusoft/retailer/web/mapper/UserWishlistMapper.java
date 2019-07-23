package cn.neusoft.retailer.web.mapper;

import cn.neusoft.retailer.web.pojo.UserWishlist;
import java.util.List;

public interface UserWishlistMapper {
    int deleteByPrimaryKey(Integer wishlistId);

    int insert(UserWishlist record);

    UserWishlist selectByPrimaryKey(Integer wishlistId);

    List<UserWishlist> selectAll();

    int updateByPrimaryKey(UserWishlist record);
}