package com.cn.perfect.ui.response;


import com.cn.perfect.bean.UserBean;
import com.cn.perfect.wapi.BaseRespone;

/**
 * Description：
 * Created on 2018/4/21
 * Author : 郭
 */

public class LoginResponseBean extends BaseRespone {
    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
