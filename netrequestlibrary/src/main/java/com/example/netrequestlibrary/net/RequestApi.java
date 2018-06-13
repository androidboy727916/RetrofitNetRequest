package com.example.netrequestlibrary.net;


import com.example.netrequestlibrary.bean.BaseRequestBean;
import com.example.netrequestlibrary.bean.RequestBean;
import com.example.netrequestlibrary.bean.ResultStatusBean;
import com.example.netrequestlibrary.bean.UserBean;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by admin on 2017/3/1.
 */
public interface RequestApi {

    // 登录
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/thumbman/tm")
    Observable<ResultStatusBean> getUstLoginState(@Body RequestBean body);



}
