package cn.neusoft.retailer.web.pojo;


import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 6977312702365086792L;
    private Integer userId;

    private String userPassword;

    private String userMail;

    private String userPhone;

    private String userName;

    private Integer userWalId;

    private Integer userPrivilege;

    private Integer mvoType;

    private String mvoUrl;

    private String mvoEngName;

    private String mvoIntroduction;
    public User(Integer userId, String name, String email, String phone) {
        this.userId = userId;
        this.mvoEngName = name;
        this.userMail = email;
        this.userPhone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public Integer getUserId() {
        return userId;
    }

    public User() {

    }

    public User(Integer userId, String userPassword, String userMail, String userPhone, String userName, Integer userWalId, Integer userPrivilege, Integer mvoType, String mvoUrl, String mvoEngName, String mvoIntroduction) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.userPhone = userPhone;
        this.userName = userName;
        this.userWalId = userWalId;
        this.userPrivilege = userPrivilege;
        this.mvoType = mvoType;
        this.mvoUrl = mvoUrl;
        this.mvoEngName = mvoEngName;
        this.mvoIntroduction = mvoIntroduction;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userMail, user.userMail) &&
                Objects.equals(userPhone, user.userPhone) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userWalId, user.userWalId) &&
                Objects.equals(userPrivilege, user.userPrivilege) &&
                Objects.equals(mvoType, user.mvoType) &&
                Objects.equals(mvoUrl, user.mvoUrl) &&
                Objects.equals(mvoEngName, user.mvoEngName) &&
                Objects.equals(mvoIntroduction, user.mvoIntroduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPassword, userMail, userPhone, userName, userWalId, userPrivilege, mvoType, mvoUrl, mvoEngName, mvoIntroduction);
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