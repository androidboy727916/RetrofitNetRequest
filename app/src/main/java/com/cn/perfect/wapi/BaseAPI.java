package com.cn.perfect.wapi;


import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Description：
 * Created on 2017/4/27
 * Author : 郭
 */
public interface BaseAPI {

    //同意请求链接
    @POST(WAPI.BASE_URL)
    void requestPostBase(@Body BaseRequest request, IHttpCallBack.Callback callback);



}
