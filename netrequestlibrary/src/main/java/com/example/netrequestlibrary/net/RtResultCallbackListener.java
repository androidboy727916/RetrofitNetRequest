package com.example.netrequestlibrary.net;

/**
 * Created by admin on 2017/3/1.
 */

public interface RtResultCallbackListener<T> {
    public void onCompleted(T been, int httpFlag);

    public void onErr(int httpFlag);


}
