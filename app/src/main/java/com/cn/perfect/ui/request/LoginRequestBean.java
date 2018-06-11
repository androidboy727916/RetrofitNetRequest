package com.cn.perfect.ui.request;


import com.cn.perfect.wapi.BaseRequest;

/**
 * Description：
 * Created on 2018/4/21
 * Author : 郭
 */

public class LoginRequestBean extends BaseRequest {
    private String mobile;
    private String password;

    public LoginRequestBean() {
        super();
    }

    public LoginRequestBean(int actId, String mobile, String password) {
        setMobile(mobile);
        setPassword(password);
        setActId(actId);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequestBean{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
