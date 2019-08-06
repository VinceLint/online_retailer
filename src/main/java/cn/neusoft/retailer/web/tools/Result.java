package cn.neusoft.retailer.web.tools;

import java.io.Serializable;

/**
 * @author 庄志宏
 * @version 1.0
 * @date 2019/8/1 20:43
 */
public class Result implements Serializable {

    //判断结果
    private boolean success;
    //返回信息
    private String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
