package com.example.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.netrequestlibrary.Constants;
import com.example.netrequestlibrary.bean.RequestBean;
import com.example.netrequestlibrary.bean.ResultStatusBean;
import com.example.netrequestlibrary.bean.UserBean;
import com.example.netrequestlibrary.net.ProgressSubscriber;
import com.example.netrequestlibrary.net.RequestApi;
import com.example.netrequestlibrary.net.RetrofitUtil;
import com.example.netrequestlibrary.net.RtRequestHepler;
import com.example.netrequestlibrary.net.RtResultCallbackListener;

public class MainActivity extends AppCompatActivity implements RtResultCallbackListener {
    private static RtRequestHepler mRequest;

    private RequestApi requestApi = RetrofitUtil.getInstance().getRetrofit(Constants.Base_url).create(RequestApi.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestBean bean=new RequestBean(202,"15522222222","qqqqqq");
//        RtRequestHepler requestHepler = RtRequestHepler.getRequestHepler();//.setUserLoginHepler(bean,this);
        RtRequestHepler.getRequestHepler().getRxAndroidConfig(MainActivity.this,true,requestApi.getUstLoginState(bean), new ProgressSubscriber<ResultStatusBean>(this, bean.getActId()));
//        requestApi.getUstLoginState(requestBean), new ProgressSubscriber<ResultStatusBean<UserBean>>(callbackListener, requestBean.getHttpFlag())

    }

    @Override
    public void onCompleted(Object been, int httpFlag) {
        onDestroyTag(Integer.valueOf(httpFlag));
       ResultStatusBean resultStatusBean = (ResultStatusBean) been;
//        Log.e("hui","==="+userBeanResultStatusBean.toString());
    }

    @Override
    public void onErr(String errorMsg, int httpFlag) {

    }


}
