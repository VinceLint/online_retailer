package cn.neusoft.retailer.web.pojo;

public class Store {
    private Integer storeId;

    private String storeName;

    private Integer storeType;

    private Integer storeOwn;

    private Float storeScore;

    private Integer storeSell;

    private String storeUrl;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Integer getStoreOwn() {
        return storeOwn;
    }

    public void setStoreOwn(Integer storeOwn) {
        this.storeOwn = storeOwn;
    }

    public Float getStoreScore() {
        return storeScore;
    }

    public void setStoreScore(Float storeScore) {
        this.storeScore = storeScore;
    }

    public Integer getStoreSell() {
        return storeSell;
    }

    public void setStoreSell(Integer storeSell) {
        this.storeSell = storeSell;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl == null ? null : storeUrl.trim();
    }
}