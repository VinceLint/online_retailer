package cn.neusoft.retailer.web.pojo;

public class Brand {
    private Integer brandId;

    private String brandName;

    private Integer brandMerId;

    private String brandUrl;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Integer getBrandMerId() {
        return brandMerId;
    }

    public void setBrandMerId(Integer brandMerId) {
        this.brandMerId = brandMerId;
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl == null ? null : brandUrl.trim();
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", brandMerId=" + brandMerId +
                ", brandUrl='" + brandUrl + '\'' +
                '}';
    }
}