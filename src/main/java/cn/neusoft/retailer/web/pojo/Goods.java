package cn.neusoft.retailer.web.pojo;

public class Goods {
    private Integer goodsId;

    private String goodsTitle;

    private String goodsPic;

    private Float goodsPrice;

    private Integer brandId;

    private Integer goodsAmount;

    private Integer goodsClass;

    private String goodsDescribe;

    private Integer goodsStatus;

    private Float goodsLength;

    private Float goodsWidth;

    private Float goodsHeight;

    private Float goodsWeight;

    public Float getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(Float goodsLength) {
        this.goodsLength = goodsLength;
    }

    public Float getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(Float goodsWidth) {
        this.goodsWidth = goodsWidth;
    }

    public Float getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(Float goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    public Float getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Float goodsWeight) {
        this.goodsWeight = goodsWeight;
    }


    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }


    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle == null ? null : goodsTitle.trim();
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic == null ? null : goodsPic.trim();
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Integer getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(Integer goodsClass) {
        this.goodsClass = goodsClass;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe == null ? null : goodsDescribe.trim();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsPic='" + goodsPic + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", brandId=" + brandId +
                ", goodsAmount=" + goodsAmount +
                ", goodsClass=" + goodsClass +
                ", goodsDescribe='" + goodsDescribe + '\'' +
                ", goodsStatus=" + goodsStatus +
                ", goodsLength=" + goodsLength +
                ", goodsWidth=" + goodsWidth +
                ", goodsHeight=" + goodsHeight +
                ", goodsWeight=" + goodsWeight +
                '}';
    }
}