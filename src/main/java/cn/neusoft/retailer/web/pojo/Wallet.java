package cn.neusoft.retailer.web.pojo;

import java.io.Serializable;

public class Wallet implements Serializable {
    private static final long serialVersionUID = -3574610027675504449L;
    private Integer walId;

    private Integer walType;

    private String walPassword;

    private Float walBalance;

    private String walPayId;

    private String walEmail;

    public Wallet(Integer walId, String walPassword, Float walBalance) {
        this.walId = walId;
        this.walPassword = walPassword;
        this.walBalance = walBalance;
    }

    public Integer getWalId() {
        return walId;
    }

    public Wallet() {
    }

    public void setWalId(Integer walId) {
        this.walId = walId;
    }

    public Integer getWalType() {
        return walType;
    }

    public void setWalType(Integer walType) {
        this.walType = walType;
    }

    public String getWalPassword() {
        return walPassword;
    }

    public void setWalPassword(String walPassword) {
        this.walPassword = walPassword == null ? null : walPassword.trim();
    }

    public Float getWalBalance() {
        return walBalance;
    }

    public void setWalBalance(Float walBalance) {
        this.walBalance = walBalance;
    }

    public String getWalPayId() {
        return walPayId;
    }

    public void setWalPayId(String walPayId) {
        this.walPayId = walPayId == null ? null : walPayId.trim();
    }

    public String getWalEmail() {
        return walEmail;
    }

    public void setWalEmail(String walEmail) {
        this.walEmail = walEmail == null ? null : walEmail.trim();
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walId=" + walId +
                ", walType=" + walType +
                ", walPassword='" + walPassword + '\'' +
                ", walBalance=" + walBalance +
                ", walPayId='" + walPayId + '\'' +
                ", walEmail='" + walEmail + '\'' +
                '}';
    }
}