package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.UserWishlist;

import java.util.List;

public interface UserWishlistService {
    UserWishlist selectByPrimaryKey(Integer wishlistId);

    UserWishlist selectByBvoId(Integer bvoId);

    List<UserWishlist> selectAll();

    boolean updateByPrimaryKey(UserWishlist record);

    boolean deleteByPrimaryKey(Integer wishlisztId);

    boolean insert(UserWishlist record);

    boolean existByWishlistId(Integer wishlistId);

    boolean existByBvoId(Integer bvoId);
}
