package com.cn.perfect.wapi;

/**
 * 返回体基类
 */
public class BaseRespone {
   private String errMsg;
   private  int code;

    public BaseRespone() {
        super();
    }

    public BaseRespone(String errMsg, int code) {
        this.errMsg = errMsg;
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseRespone{" +
                "errMsg='" + errMsg + '\'' +
                ", code=" + code +
                '}';
    }
}
