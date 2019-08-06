package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.UserWishlistMapper;
import cn.neusoft.retailer.web.pojo.UserWishlist;
import cn.neusoft.retailer.web.service.UserWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 庄梓源
 * @version 1.0
 * @date 2019-07-23 21:55
 */
@Service("UserWishlistService")
public class UserWishlistServiceImpl implements UserWishlistService {
    @Autowired
    UserWishlistMapper userWishlistMapper;

    @Override
    public UserWishlist selectByPrimaryKey(Integer wishlistId){
        return userWishlistMapper.selectByPrimaryKey(wishlistId);
    }

    @Override
    public UserWishlist selectByBvoId(Integer bvoId){
        return userWishlistMapper.selectByBvoId(bvoId);
    }

    @Override
    public List<UserWishlist> selectAll(){
        return userWishlistMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(UserWishlist record){
        return userWishlistMapper.updateByPrimaryKey(record) !=0;
    }

    @Override
    public boolean deleteByPrimaryKey(Integer wishlistId){
       return userWishlistMapper.deleteByPrimaryKey(wishlistId) !=0;
    }

    @Override
    public boolean insert(UserWishlist record){
        return userWishlistMapper.insert(record) !=0;
    }

    @Override
    public boolean existByWishlistId(Integer wishlistId) {
        if(userWishlistMapper.selectByPrimaryKey(wishlistId)==null)
        return false;
        else return true;
    }

    @Override
    public boolean existByBvoId(Integer bvoId) {
        if(userWishlistMapper.selectByBvoId(bvoId)==null)
        return false;
        else return true;
    }
}
