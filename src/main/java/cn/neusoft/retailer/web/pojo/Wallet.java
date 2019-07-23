package cn.neusoft.retailer.web.pojo;

public class Wallet {
    private Integer walId;

    private Integer walType;

    private String walPassword;

    private Float walBalance;

    private String walPayId;

    private String walEmail;

    public Integer getWalId() {
        return walId;
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
}