package cn.neusoft.retailer.web.pojo;

import java.io.Serializable;
import java.util.Date;

public class LogisticsNode implements Serializable {
    private static final long serialVersionUID = -7345569712780863413L;
    private String logNodeId;

    private String logId;

    private String logNodeName;

    private Date logArrTime;

    public String getLogNodeId() {
        return logNodeId;
    }

    public void setLogNodeId(String logNodeId) {
        this.logNodeId = logNodeId == null ? null : logNodeId.trim();
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getLogNodeName() {
        return logNodeName;
    }

    public void setLogNodeName(String logNodeName) {
        this.logNodeName = logNodeName == null ? null : logNodeName.trim();
    }

    public Date getLogArrTime() {
        return logArrTime;
    }

    public void setLogArrTime(Date logArrTime) {
        this.logArrTime = logArrTime;
    }
}