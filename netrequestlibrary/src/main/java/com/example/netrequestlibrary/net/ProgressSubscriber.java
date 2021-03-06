package com.example.netrequestlibrary.net;



import com.example.netrequestlibrary.bean.ResultStatusBean;

import rx.Subscriber;

/**
 * Created by admin on 2017/3/1.
 */

public class ProgressSubscriber<T> extends Subscriber<T> {
    private RtResultCallbackListener<T> mListener;
    private int mHttpFlag;

    public ProgressSubscriber(RtResultCallbackListener<T> listener, int httpFlag) {
        this.mListener = listener;
        this.mHttpFlag = httpFlag;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (mListener != null) {
            mListener.onErr( e.getMessage(),mHttpFlag);
        }
//        Log.e("onError", "onError==" + e.getMessage());
//        if (e instanceof SocketTimeoutException) {
//            Toast.makeText(MyApplication.getAppContext(), "Network outage, please check your network status", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof ConnectException) {
//            Toast.makeText(MyApplication.getAppContext(), "Network outage, please check your network status", Toast.LENGTH_SHORT).show();
//        } else {
//            //  Toast.makeText(MyApplication.getAppContext(), "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onNext(T t) {

        if (mListener != null) {
            ResultStatusBean resultStatusBean = (ResultStatusBean) t;
            if(resultStatusBean.getCode() == 1){
                mListener.onCompleted(t, mHttpFlag);
            }else{
                mListener.onErr( resultStatusBean.getErrMsg(),mHttpFlag);
            }


        }

    }
}
