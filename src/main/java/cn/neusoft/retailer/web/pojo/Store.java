package cn.neusoft.retailer.web.pojo;

public class Store {
    private Integer storeId;

    private String storeName;

    private Integer storeType;

    private Integer storeOwn;

    private String marketplaceId;

    private String nwsAuthToken;

    public Store() {
    }

    public Store(Integer storeId, String storeName, Integer storeType, Integer storeOwn, String marketplaceId, String nwsAuthToken) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeType = storeType;
        this.storeOwn = storeOwn;
        this.marketplaceId = marketplaceId;
        this.nwsAuthToken = nwsAuthToken;
    }

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

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId == null ? null : marketplaceId.trim();
    }

    public String getNwsAuthToken() {
        return nwsAuthToken;
    }

    public void setNwsAuthToken(String nwsAuthToken) {
        this.nwsAuthToken = nwsAuthToken == null ? null : nwsAuthToken.trim();
    }
}