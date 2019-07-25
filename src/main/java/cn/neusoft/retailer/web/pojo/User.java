package cn.neusoft.retailer.web.pojo;


public class User {
    private Integer userId;

    private String userPassword;

    private String userName;

    private String userMail;

    private String userPhone;

    private Integer userWalId;

    private Integer userPrivilege;

    private Integer mvoType;

    private String mvoUrl;

    private String mvoEngName;

    private String mvoIntroduction;

    public Integer getUserId() {
        return userId;
    }

    public User() {

    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Integer getUserWalId() {
        return userWalId;
    }

    public void setUserWalId(Integer userWalId) {
        this.userWalId = userWalId;
    }

    public Integer getUserPrivilege() {
        return userPrivilege;
    }

    public void setUserPrivilege(Integer userPrivilege) {
        this.userPrivilege = userPrivilege;
    }

    public Integer getMvoType() {
        return mvoType;
    }

    public void setMvoType(Integer mvoType) {
        this.mvoType = mvoType;
    }

    public String getMvoUrl() {
        return mvoUrl;
    }

    public void setMvoUrl(String mvoUrl) {
        this.mvoUrl = mvoUrl == null ? null : mvoUrl.trim();
    }

    public String getMvoEngName() {
        return mvoEngName;
    }

    public void setMvoEngName(String mvoEngName) {
        this.mvoEngName = mvoEngName == null ? null : mvoEngName.trim();
    }

    public String getMvoIntroduction() {
        return mvoIntroduction;
    }

    public void setMvoIntroduction(String mvoIntroduction) {
        this.mvoIntroduction = mvoIntroduction == null ? null : mvoIntroduction.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userWalId=" + userWalId +
                ", userPrivilege=" + userPrivilege +
                ", MvoType=" + mvoType +
                ", mvoUrl='" + mvoUrl + '\'' +
                ", mvoEngName='" + mvoEngName + '\'' +
                ", mvoIntroduction='" + mvoIntroduction + '\'' +
                '}';
    }
}