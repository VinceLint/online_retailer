package cn.neusoft.retailer.web.pojo;

import java.io.Serializable;

public class Parameter implements Serializable {
    private static final long serialVersionUID = -3691686307057614455L;
    private String parId;

    private Integer parValue;

    private String parDescribe;

    public Parameter(String parId, Integer parValue, String parDescribe) {
        this.parId = parId;
        this.parValue = parValue;
        this.parDescribe = parDescribe;
    }

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