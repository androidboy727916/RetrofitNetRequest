package com.cn.perfect.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.perfect.R;
import com.cn.perfect.application.MyApplication;
import com.cn.perfect.pub.PubConst;
import com.cn.perfect.utils.LogUtil;
import com.cn.perfect.wapi.BaseAPI;
import com.cn.perfect.wapi.IHttpCallBack;
import com.cn.perfect.wapi.RestAdapterUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description：base 密码t666666
 * Created on 2017/9/14
 * Author : 郭
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public Unbinder mUnBinder;
    protected BaseActivity baseContext = BaseActivity.this;
    private String name = this.getClass().getSimpleName();
    /**
     * 输入法管理器
     */
    public InputMethodManager imm;
    public BaseAPI restAPI;
    public ImageView rightIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWin();
        if(getLayoutId() == 0){
            setContentView(getLayoutView());
        }else{
            setContentView(getLayoutId());
        }

        MyApplication.getInstance().addActivity(this);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        setStatusBar();
        mUnBinder = ButterKnife.bind(this);
        restAPI = RestAdapterUtils.getRestAPI(BaseAPI.class);
        initView(savedInstanceState);
    }

    protected View getLayoutView(){
        return null;
    }

    protected void setWin() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 设置状态栏默认颜色
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.default_color), 0);
    }


    public Toolbar getAppToolbar() {
        return (Toolbar) findViewById(R.id.app_toolbar);
    }
    /**
     * 返回右侧按钮
     * @return
     */
    public TextView getRightTitle() {
        return (TextView) findViewById(R.id.toolbar_right);
    }
    /**
     * 设置title
     */
    public void setTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.toolbar_title);
        if (textView != null && !TextUtils.isEmpty(title)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(title);
        }
    }

    /**
     * 设置右侧text
     */
    public void setRightTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.toolbar_right);
        if (textView != null && !TextUtils.isEmpty(title)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(title);
            textView.setOnClickListener(this);
        }
    }

    public void setLeftTitle() {
        setLeftIcon(R.drawable.arror_left);
    }

    /**
     * 设置右侧icon
     *
     * @param resid
     * @return
     */
    public void setRightTitle(int resid) {
        rightIcon = (ImageView) findViewById(R.id.toolbar_right_icon);
        if (rightIcon != null) {
            rightIcon.setVisibility(View.VISIBLE);
            rightIcon.setImageResource(resid);
            rightIcon.setOnClickListener(this);
        }
    }

    /**
     * 设置左侧icon
     *
     * @param resid
     * @return
     */
    public void setLeftIcon(int resid) {
        ImageView arrowIcon = (ImageView) findViewById(R.id.toolbar_left);
        if (arrowIcon != null) {
            arrowIcon.setVisibility(View.VISIBLE);
            arrowIcon.setImageResource(resid);
            arrowIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        MyApplication.getInstance().removeActivity(this);
        if (mUnBinder != null)
            mUnBinder.unbind();
        super.onDestroy();
        mUnBinder = null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        showKeyboard(false);
        return super.onTouchEvent(event);
    }

    /**
     * 控制输入法是否显示
     *
     * @param isShow
     */
    protected void showKeyboard(boolean isShow) {
        if (imm == null) {
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    public boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return ContextCompat.checkSelfPermission(MyApplication.getInstance(), permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public void requestPermission(String[] permissions, int requestCode) {
        //申请权限
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    /**
     * 加載 布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    //////////////////////////////////////////// 封装Intent跳转//////////////////////////

    protected void skip(Class<?> clazz, boolean isCloseSelf) {
        Intent intent = new Intent(baseContext, clazz);
        startActivity(intent);
        if (isCloseSelf) baseContext.finish();
        leftOutRightIn(baseContext);
    }

    protected void skip(Class<?> clazz, Bundle data, boolean isCloseSelf) {
        Intent intent = new Intent(baseContext, clazz);
        if (data != null) intent.putExtra(PubConst.DATA, data);
        startActivity(intent);
        if (isCloseSelf) baseContext.finish();
        leftOutRightIn(baseContext);
    }

    protected void skipForResult(Class<?> clazz, Bundle data, int requestCode) {
        Intent intent = new Intent(baseContext, clazz);
        if (data != null) intent.putExtra(PubConst.DATA, data);
        startActivityForResult(intent, requestCode);
        leftOutRightIn(baseContext);
    }

    protected void skipForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(baseContext, clazz);
        startActivityForResult(intent, requestCode);
        leftOutRightIn(baseContext);
    }

    /**
     * 做出右进的效果
     *
     * @param context
     */
    public static void leftOutRightIn(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 右侧出的效果
     *
     * @param context
     */
    public static void rightOut(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    @Override
    public void finish() {
        super.finish();
        rightOut(this);
    }

    /////////////////////////////////////网络请求//////////////////////
    public IHttpCallBack baseCallBack = new IHttpCallBack() {
        public void onSuccess(Object o, String tag) {
            LogUtil.showLog("outputtag==" + tag + "==成功=====" + new Gson().toJson(o));
//            onDestroy();
            onDestroy(Integer.valueOf(tag));
            onSuccessCallBack(o, tag);
        }

        public void onFailure(String failureMsg, String tag) {
            LogUtil.showLog("outputtag==" + tag + "===失败=====" + failureMsg);
//            onDestroy();
            onDestroy(Integer.valueOf(tag));
            onFailureCallBack(failureMsg, tag);
        }
    };


    public <T> void onSuccessCallBack(T t, String tag) {
    }

    public void onFailureCallBack(String failureMsg, String tag) {
        if (failureMsg == null)
            Toast.makeText(this, getString(R.string.reterror_error), Toast.LENGTH_LONG).show();
        else if (failureMsg.indexOf("java") >= 0 || failureMsg.endsWith("Error") || failureMsg.indexOf("Exception") >= 0||failureMsg.contains("refused")) {
            Toast.makeText(this, getString(R.string.reterror_network_java_net_error), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, failureMsg, Toast.LENGTH_LONG).show();
        }
    }

}