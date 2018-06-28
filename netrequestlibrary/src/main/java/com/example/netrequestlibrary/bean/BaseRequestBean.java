package com.example.netrequestlibrary.bean;

public class BaseRequestBean {
    private int actId;
    private int httpFlag;

    public int getActId() {
        return actId;
    }

    public int getHttpFlag() {
        return httpFlag;
    }

    public void setHttpFlag(int httpFlag) {
        this.httpFlag = httpFlag;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }
}
