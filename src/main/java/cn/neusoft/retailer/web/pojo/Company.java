package cn.neusoft.retailer.web.pojo;

import java.util.Date;

public class Company {
    private Integer companyId;

    private String companyName;

    private String companyLocation;

    private String companyContact;

    private Date companyBirthday;

    private String companyIntroduction;


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation == null ? null : companyLocation.trim();
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact == null ? null : companyContact.trim();
    }

    public Date getCompanyBirthday() {
        return companyBirthday;
    }

    public void setCompanyBirthday(Date companyBirthday) {
        this.companyBirthday = companyBirthday;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }
}