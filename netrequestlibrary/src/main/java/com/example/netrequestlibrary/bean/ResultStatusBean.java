package com.example.netrequestlibrary.bean;

public class ResultStatusBean<T,T1,T2> {
    private int code;
    private String errMsg;
    private T user;
    private T1 hu;

    public T1 getHu() {
        return hu;
    }

    public void setHu(T1 hu) {
        this.hu = hu;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResultStatusBean{" +
                "code=" + code +
                ", errMsg='" + errMsg + '\'' +
                ", user=" + user +
                ", hu="  +
                '}';
    }
}
