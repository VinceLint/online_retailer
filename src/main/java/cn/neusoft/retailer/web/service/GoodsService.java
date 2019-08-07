package cn.neusoft.retailer.web.service;

import cn.neusoft.retailer.web.pojo.Goods;

import java.util.List;

public interface GoodsService {

    public void save(String title, Integer id, double price, int amount, int clazz, String describe, double length,
                     double width, double height, double weight, int brandId, String fileName);

    public List<Goods> selectAll();

    public void deleteByGoodsId(int goodsId);

    public void updateByGoodsId(int goodsId, String title, double price, int amount, int clazz, String describe, double length,
                                double width, double height, double weight, int brandId, String fileName);

    public void updateStatus0ByPrimaryKey(int goodsId);

    public void updateStatus1ByPrimaryKey(int goodsId);

    public void updateStatus2ByPrimaryKey(int goodsId);

    public List<Goods> selectByBrandId(int brandId);

    public void savePic(int goodsId, String goodsPic);

    Goods selectByPrimaryKey(Integer goodsId);

    boolean updateByPrimaryKey(Goods record);

    //zhuang
    List<Goods> selectByGoodsStatus(String goodsStatus);

    List<Goods> selectByGoodsTitle(String goodsTitle);
}
