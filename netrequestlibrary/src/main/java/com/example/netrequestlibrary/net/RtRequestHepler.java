package com.example.netrequestlibrary.net;


import com.example.netrequestlibrary.Constants;
import com.example.netrequestlibrary.bean.RequestBean;
import com.example.netrequestlibrary.bean.ResultStatusBean;
import com.example.netrequestlibrary.bean.UserBean;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by admin on 2017/3/1.
 */

public class RtRequestHepler {
    private static RtRequestHepler mRequest;
    private RequestApi requestApi;


    private RtRequestHepler() {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        Retrofit retrofit = instance.getRetrofit(Constants.Base_url);
        requestApi = retrofit.create(RequestApi.class);
    }

    public synchronized static RtRequestHepler getRequestHepler() {

        if (mRequest == null) {
            synchronized (RtRequestHepler.class) {
                if (mRequest == null) {
                    mRequest = new RtRequestHepler();
                }
            }
        }
        return mRequest;
    }

    /**
     * 登录
     */
    public synchronized void setUserLoginHepler(RequestBean requestBean, RtResultCallbackListener callbackListener) {
        getRxAndroidConfig(requestApi.getUstLoginState(requestBean), new ProgressSubscriber<ResultStatusBean<UserBean>>(callbackListener, requestBean.getHttpFlag()));
    }


    private RequestBody setmRequest(String jsonStar) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonStar);
        return body;
    }

    public <T> void getRxAndroidConfig(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
