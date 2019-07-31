package cn.neusoft.retailer.web.pojo;

import java.util.Date;

public class TransactionRecord {
    private Integer traRecId;

    private Date traRecDate;

    private String traRecDateStr;

    public String getTraRecDateStr() {
        return traRecDateStr;
    }

    public void setTraRecDateStr(String traRecDateStr) {
        this.traRecDateStr = traRecDateStr;
    }

    private Integer traRecType;


    private Float traRecSum;

    private Integer traRecStatus;


    private Integer traRecWalId;

    private Float traRecBalance;

    private String traRecExchangememo;

    public Integer getTraRecId() {
        return traRecId;
    }


    @Override
    public String toString() {
        return "TransactionRecord{" +
                "traRecId=" + traRecId +
                ", traRecDate=" + traRecDate +
                ", traRecType=" + traRecType +
                ", traRecSum=" + traRecSum +
                ", traRecStatus=" + traRecStatus +
                ", traRecWalId=" + traRecWalId +
                ", traRecBalance=" + traRecBalance +
                ", traRecExchangememo='" + traRecExchangememo + '\'' +
                '}';
    }

    public void setTraRecId(Integer traRecId) {
        this.traRecId = traRecId;
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