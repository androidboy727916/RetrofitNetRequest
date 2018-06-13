package com.example.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.netrequestlibrary.bean.RequestBean;
import com.example.netrequestlibrary.bean.ResultStatusBean;
import com.example.netrequestlibrary.bean.UserBean;
import com.example.netrequestlibrary.net.RtRequestHepler;
import com.example.netrequestlibrary.net.RtResultCallbackListener;

public class MainActivity extends AppCompatActivity implements RtResultCallbackListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestBean bean=new RequestBean();
        bean.setActId(202);
        bean.setMobile("15522222222");
        bean.setPassword("qqqqqq");
        bean.setHttpFlag(10000);
        RtRequestHepler.getRequestHepler().setUserLoginHepler(bean,this);

    }

    @Override
    public void onCompleted(Object been, int httpFlag) {
        ResultStatusBean<UserBean> userBeanResultStatusBean= (ResultStatusBean<UserBean>) been;
        Log.e("hui","==="+userBeanResultStatusBean.toString());
    }

    @Override
    public void onErr(int httpFlag) {

    }
}
