package cn.neusoft.retailer.web.pojo;

public class Brand {
    private Integer brandId;

    private String brandName;

    private Integer brandComId;

    private Integer brandMerId;

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

    public Integer getBrandComId() {
        return brandComId;
    }

    public void setBrandComId(Integer brandComId) {
        this.brandComId = brandComId;
    }

    public Integer getBrandMerId() {
        return brandMerId;
    }

    public void setBrandMerId(Integer brandMerId) {
        this.brandMerId = brandMerId;
    }
}