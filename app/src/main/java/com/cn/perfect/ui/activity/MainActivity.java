package com.cn.perfect.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.perfect.R;
import com.cn.perfect.base.BaseActivity;
import com.cn.perfect.bean.UserBean;
import com.cn.perfect.ui.request.LoginRequestBean;
import com.cn.perfect.ui.response.LoginResponseBean;
import com.cn.perfect.utils.LogUtil;
import com.cn.perfect.utils.ToastUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button_net)
    Button button_net;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_psw)
    EditText et_psw;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
//        setLeftTitle();
//        setTitle("title");
        button_net.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_net:
                requestLogin();
                break;
            default:
                break;
        }
    }
    /**
     * 请求登录
     */
    private void requestLogin() {
        LoginRequestBean requestBean = new LoginRequestBean(202, et_name.getText().toString().trim(), et_psw.getText().toString().trim());
        restAPI.requestPostBase(requestBean, baseCallBack.getCallBack(202, LoginResponseBean.class, true, this));
    }

    @Override
    public <T> void onSuccessCallBack(T t, String tag) {
        super.onSuccessCallBack(t, tag);
        if (tag.endsWith(String.valueOf(202))) {
            LoginResponseBean responseBean = (LoginResponseBean) t;
            UserBean user = responseBean.getUser();
            LogUtil.showLog("展示===="+ user.toString());
            tv_content.setText(user.toString());
        }
    }

    @Override
    public void onFailureCallBack(String failureMsg, String tag) {
        super.onFailureCallBack(failureMsg, tag);
        if (tag.endsWith(String.valueOf(202))) {

        }
    }
}
