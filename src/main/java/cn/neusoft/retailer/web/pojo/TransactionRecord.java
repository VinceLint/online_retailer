package cn.neusoft.retailer.web.pojo;

import java.util.Date;

public class TransactionRecord {
    private String traRecId;

    private Date traRecDate;

    private Integer traRecType;

    private Float traRecSum;

    private Integer traRecStatus;

    private Integer traRecWalId;

    private Float traRecBalance;

    private String traRecExchangememo;

    public String getTraRecId() {
        return traRecId;
    }

    public void setTraRecId(String traRecId) {
        this.traRecId = traRecId == null ? null : traRecId.trim();
    }

    public Date getTraRecDate() {
        return traRecDate;
    }

    public void setTraRecDate(Date traRecDate) {
        this.traRecDate = traRecDate;
    }

    public Integer getTraRecType() {
        return traRecType;
    }

    public void setTraRecType(Integer traRecType) {
        this.traRecType = traRecType;
    }

    public Float getTraRecSum() {
        return traRecSum;
    }

    public void setTraRecSum(Float traRecSum) {
        this.traRecSum = traRecSum;
    }

    public Integer getTraRecStatus() {
        return traRecStatus;
    }

    public void setTraRecStatus(Integer traRecStatus) {
        this.traRecStatus = traRecStatus;
    }

    public Integer getTraRecWalId() {
        return traRecWalId;
    }

    public void setTraRecWalId(Integer traRecWalId) {
        this.traRecWalId = traRecWalId;
    }

    public Float getTraRecBalance() {
        return traRecBalance;
    }

    public void setTraRecBalance(Float traRecBalance) {
        this.traRecBalance = traRecBalance;
    }

    public String getTraRecExchangememo() {
        return traRecExchangememo;
    }

    public void setTraRecExchangememo(String traRecExchangememo) {
        this.traRecExchangememo = traRecExchangememo == null ? null : traRecExchangememo.trim();
    }
}