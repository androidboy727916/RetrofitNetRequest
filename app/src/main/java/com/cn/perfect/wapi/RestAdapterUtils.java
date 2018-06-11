package com.cn.perfect.wapi;

import android.support.annotation.NonNull;

import com.cn.perfect.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 * Description：
 * Created on 2017/4/27
 * Author : 郭
 */
public class RestAdapterUtils {


    private static final int CONNECTTION_TIMEOUTE = 30;

    private static final int READ_TIMEOUT = 10;

    /**
     * 默认API 生成  地址 {@link#Configs.URL_SERVER}
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getRestAPI(@NonNull final Class<T> service) {
        RestAdapter.LogLevel level = BuildConfig.DEBUG ? RestAdapter.LogLevel.BASIC : RestAdapter.LogLevel.NONE;
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONNECTTION_TIMEOUTE, TimeUnit.SECONDS);
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(level)
                .setEndpoint(BuildConfig.DEBUG ? WAPI.WAPI_URL_ONLINE : WAPI.WAPI_URL_ONLINE)
                .setClient(new OkClient(client))
                .build();
        return restAdapter.create(service);
    }

    /**
     * @param request
     * @param callBack
     */
    public static void sendRequest(BaseRequest request,IHttpCallBack.Callback callBack) {
        RestAdapterUtils.getRestAPI(BaseAPI.class).requestPostBase(request, callBack);
    }
}
