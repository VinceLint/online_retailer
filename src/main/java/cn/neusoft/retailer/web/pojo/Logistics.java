package cn.neusoft.retailer.web.pojo;

public class Logistics {
    private String logId;

    private String logComName;

    private String logCouName;

    private String logCouPhone;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getLogComName() {
        return logComName;
    }

    public void setLogComName(String logComName) {
        this.logComName = logComName == null ? null : logComName.trim();
    }

    public String getLogCouName() {
        return logCouName;
    }

    public void setLogCouName(String logCouName) {
        this.logCouName = logCouName == null ? null : logCouName.trim();
    }

    public String getLogCouPhone() {
        return logCouPhone;
    }

    public void setLogCouPhone(String logCouPhone) {
        this.logCouPhone = logCouPhone == null ? null : logCouPhone.trim();
    }
}