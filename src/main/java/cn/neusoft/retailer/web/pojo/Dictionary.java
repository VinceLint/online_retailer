package cn.neusoft.retailer.web.pojo;

public class Dictionary {
    private Integer dicId;

    private String dicType;

    private String dicDiscribe;

    private Integer dicCode;

    private String dicValue;

    public Dictionary() {
    }

    public Dictionary(Integer dicId, String dicType, String dicDiscribe, Integer dicCode, String dicValue) {
        this.dicId = dicId;
        this.dicType = dicType;
        this.dicDiscribe = dicDiscribe;
        this.dicCode = dicCode;
        this.dicValue = dicValue;
    }

    public Integer getDicId() {
        return dicId;
    }

    public void setDicId(Integer dicId) {
        this.dicId = dicId;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType == null ? null : dicType.trim();
    }

    public String getDicDiscribe() {
        return dicDiscribe;
    }

    public void setDicDiscribe(String dicDiscribe) {
        this.dicDiscribe = dicDiscribe == null ? null : dicDiscribe.trim();
    }

    public Integer getDicCode() {
        return dicCode;
    }

    public void setDicCode(Integer dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }
}