package com.example.netrequestlibrary.net;


import com.example.netrequestlibrary.Constants;
import com.example.netrequestlibrary.bean.RequestBean;
import com.example.netrequestlibrary.bean.ResultStatusBean;
import com.example.netrequestlibrary.bean.UserBean;

import retrofit2.Retrofit;
import rx.Observable;
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
        requestApi.getUstLoginState(requestBean).compose(new SchedulerTransformer<ResultStatusBean>()).subscribe(new ProgressSubscriber<ResultStatusBean>(callbackListener, requestBean.getHttpFlag()));
    }

    public class SchedulerTransformer<T> implements Observable.Transformer<T, T> {

        @Override
        public Observable<T> call(Observable<T> tObservable) {
            return tObservable.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
