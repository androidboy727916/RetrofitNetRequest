package com.cn.perfect.wapi;


/**
 * 基本请求类
 */
public class BaseRequest {
    private  int actId;

    public BaseRequest() {
        super();
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "actId=" + actId +
                '}';
    }
}
