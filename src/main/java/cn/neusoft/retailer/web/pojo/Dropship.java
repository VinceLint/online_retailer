package cn.neusoft.retailer.web.pojo;

import java.io.Serializable;

public class Dropship implements Serializable {
    private static final long serialVersionUID = -3437527981429348195L;
    private Integer dropshipId;

    private Integer bvoId;

    private Integer goodsId;

    private Integer goodsNum;

    private Integer storeId;

    private String upc;

    public Integer getDropshipId() {
        return dropshipId;
    }

    public void setDropshipId(Integer dropshipId) {
        this.dropshipId = dropshipId;
    }

    public Integer getBvoId() {
        return bvoId;
    }

    public void setBvoId(Integer bvoId) {
        this.bvoId = bvoId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc == null ? null : upc.trim();
    }
}