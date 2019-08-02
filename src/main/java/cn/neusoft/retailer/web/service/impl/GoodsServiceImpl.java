package cn.neusoft.retailer.web.service.impl;

import cn.neusoft.retailer.web.mapper.GoodsMapper;
import cn.neusoft.retailer.web.pojo.Goods;
import cn.neusoft.retailer.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void save(String title, int id, double price, int amount, int clazz, String describe, double length,
                     double width, double height, double weight) {
        System.out.println("save service in");

        Goods goods = new Goods();
        goods.setGoodsId(id);
        goods.setGoodsTitle(title);
        goods.setGoodsPrice((float)price);
        goods.setGoodsAmount(amount);
        goods.setGoodsClass(clazz);
        goods.setBrandId(1);
        goods.setGoodsDescribe(describe);
        goods.setGoodsStatus(0);
        goods.setGoodsLength((float)length);
        goods.setGoodsWidth((float)width);
        goods.setGoodsHeight((float)height);
        goods.setGoodsWeight((float)weight);

        goodsMapper.insert(goods);

    }

    @Override
    public List<Goods> selectAll() {
        return goodsMapper.selectAll();
    }

    @Override
    public void deleteByGoodsId(int goodsId) {
        goodsMapper.deleteByPrimaryKey(goodsId);
    }

    @Override
    public void updateByGoodsId(int goodsId, String title, double price, int amount, int clazz, String describe, double length,
                                double width, double height, double weight) {
        System.out.println("service in");

        /*System.out.println(title + " " + goodsId + " " + price + " " + amount + " " + clazz);*/

        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setGoodsTitle(title);
        goods.setGoodsPrice((float)price);
        goods.setGoodsAmount(amount);
        goods.setGoodsClass(clazz);
        goods.setBrandId(1);
        goods.setGoodsStatus(1);
        goods.setGoodsDescribe(describe);
        goods.setGoodsLength((float)length);
        goods.setGoodsWidth((float)width);
        goods.setGoodsHeight((float)height);
        goods.setGoodsWeight((float)weight);

        goodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public void updateStatus0ByPrimaryKey(int goodsId) {
        goodsMapper.updateStatus0ByPrimaryKey(goodsId);
    }

    @Override
    public void updateStatus1ByPrimaryKey(int goodsId) {
        goodsMapper.updateStatus1ByPrimaryKey(goodsId);
    }

    @Override
    public void updateStatus2ByPrimaryKey(int goodsId) {
        goodsMapper.updateStatus2ByPrimaryKey(goodsId);
    }

    @Override
    public List<Goods> selectByBrandId(int brandId) {
        return goodsMapper.selectByBrandId(brandId);
    }

    @Override
    public void savePic(int goodsId, String goodsPic) {
        goodsMapper.updatePicByPrimaryKey(goodsId, goodsPic);
    }

    @Override
    public Goods selectByPrimaryKey(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public boolean updateByPrimaryKey(Goods record){
        return goodsMapper.updateByPrimaryKey(record)>0;
    }
}
