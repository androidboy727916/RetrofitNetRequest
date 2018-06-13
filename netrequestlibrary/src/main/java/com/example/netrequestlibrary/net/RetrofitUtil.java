package com.example.netrequestlibrary.net;


import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by admin on 2017/3/1.
 */

public class RetrofitUtil {
    private static RetrofitUtil mInstance;
    private OkHttpClient mHttpClient;
    private static Retrofit.Builder mRetrofitBuilder;

    private RetrofitUtil(OkHttpClient okHttpClient) {
        if (null == okHttpClient) {
            mHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new LoggerInterceptor("RetrofitHttp", true)).connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        } else {
            mHttpClient = okHttpClient;
        }
        if (null == mRetrofitBuilder) {
            mRetrofitBuilder = new Retrofit.Builder().client(mHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }
    }

    /**
     * 在application的onCreate中初始化
     *
     * @param
     * @return
     */
    public static RetrofitUtil init() {
        if (null == mInstance) {
            synchronized (RetrofitUtil.class) {
                if (null == mInstance) {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new LoggerInterceptor("RetrofitHttp", true)).connectTimeout(150000L, TimeUnit.MILLISECONDS)
                            .readTimeout(150000L, TimeUnit.MILLISECONDS)
                            .retryOnConnectionFailure(true)
                            .build();
                    //  RetrofitUtil.init(okHttpClient);
                    mInstance = new RetrofitUtil(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static RetrofitUtil getInstance() {
        return init();
    }

    public   Retrofit getRetrofit(String baseUrl) {
        return mRetrofitBuilder.baseUrl(baseUrl).build();
    }

    static SSLSocketFactory sslSocketFactory;


}
