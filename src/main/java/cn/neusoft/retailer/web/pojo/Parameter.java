package cn.neusoft.retailer.web.pojo;

public class Parameter {
    private String parId;

    private Integer parValue;

    private String parDescribe;

    public String getParId() {
        return parId;
    }

    public void setParId(String parId) {
        this.parId = parId == null ? null : parId.trim();
    }

    public Integer getParValue() {
        return parValue;
    }

    public void setParValue(Integer parValue) {
        this.parValue = parValue;
    }

    public String getParDescribe() {
        return parDescribe;
    }

    public void setParDescribe(String parDescribe) {
        this.parDescribe = parDescribe == null ? null : parDescribe.trim();
    }
}