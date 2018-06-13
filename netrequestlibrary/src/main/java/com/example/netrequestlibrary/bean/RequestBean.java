package com.example.netrequestlibrary.bean;

public class RequestBean extends BaseRequestBean{

    private String mobile;
    private String password;

    public RequestBean(int actId, String mobile, String password) {
      setActId(actId);
      setMobile(mobile);
      setPassword(password);
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
}
