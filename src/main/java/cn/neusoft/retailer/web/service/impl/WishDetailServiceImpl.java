package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.WishDetailMapper;
import cn.neusoft.retailer.web.pojo.WishDetail;
import cn.neusoft.retailer.web.service.WishDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 庄梓源
 * @version 1.0
 * @date 2019-07-23 22:35
 */
@Service("WishDetailService")
public class WishDetailServiceImpl implements WishDetailService {
    @Autowired
    WishDetailMapper wishDetailMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer wishDetailId) {
        return wishDetailMapper.deleteByPrimaryKey(wishDetailId) !=0;
    }

    @Override
    public boolean insert(WishDetail record) {
        return wishDetailMapper.insert(record) !=0;
    }

    @Override
    public WishDetail selectByPrimaryKey(Integer wishDetailId) {
        return wishDetailMapper.selectByPrimaryKey(wishDetailId) ;
    }

    @Override
    public List<WishDetail> selectByWishListId(Integer wishListId) {
        return wishDetailMapper.selectByWishListId(wishListId);
    }
/*
    @Override
    public List<WishDetail> selectByWishListId(Integer wishListId, Page page) {
        return wishDetailMapper.selectByWishListId(wishListId,page);
    }
*/
    @Override
    public List<WishDetail> selectAll() {
        return wishDetailMapper.selectAll();
    }

   @Override
    public boolean updateByPrimaryKey(WishDetail record) {
        return wishDetailMapper.updateByPrimaryKey(record) !=0;
    }

    @Override
    public int total(Integer wishListId) {
        return wishDetailMapper.total(wishListId);
    }

    @Override
    public WishDetail selectByWishListIdAndGoodsId(Map<String, Object> map) {
        return wishDetailMapper.selectByWishListIdAndGoodsId(map);
    }


}
