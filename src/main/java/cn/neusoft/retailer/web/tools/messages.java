package cn.neusoft.retailer.web.tools;

/**
 * @author 林扬凯
 * @version 1.0
 * @date 2019-07-23 23:14
 */
//前端消息包：1-ok，0-remove
public class messages {
    private int status;
    private String describe;

    public messages(int status, String describe, String icon) {
        this.status = status;
        this.describe = describe;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String icon;

    public messages(int status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
