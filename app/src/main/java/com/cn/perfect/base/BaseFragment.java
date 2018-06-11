package com.cn.perfect.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * base  郭  2017年9月18日14:00:02
 *
 * @param <T>
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private Unbinder mUnBinder;
    protected ProgressDialog progressDialog;
    public Activity mActivity;
    private String name = this.getClass().getSimpleName();
    public BaseAPI restAPI;

    protected View mRootView;
    public Context mContext;
    protected boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;

    //判断Fragment是否加载网络数据（即执行initView方法）
    private boolean isLoad = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            isLoad = false;
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnBinder = ButterKnife.bind(this, view);
        setStatusBar();
        restAPI = RestAdapterUtils.getRestAPI(BaseAPI.class);

        if (mRootView != null && !isLoad) {
            isLoad = true;
            initView(savedInstanceState, view);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    /**
     * 设置状态栏默认颜色
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(mActivity, getResources().getColor(R.color.default_color), 0);
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
        super.onDestroy();
    }

    public Toolbar getAppToolbar() {
        return (Toolbar) this.getView().findViewById(R.id.app_toolbar);
    }

    /**
     * 设置title
     */
    public void setTitle(String title) {
        TextView textView = (TextView) this.getView().findViewById(R.id.toolbar_title);
        if (textView != null && !TextUtils.isEmpty(title)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(title);
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 设置右侧text
     */
    public void setRightTitle(String title) {
        TextView textView = (TextView) this.getView().findViewById(R.id.toolbar_right);
        if (textView != null && !TextUtils.isEmpty(title)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(title);
            textView.setOnClickListener(this);
        }
    }

    /**
     * 返回右侧按钮
     *
     * @return
     */
    public TextView getRightTitle() {
        return (TextView) this.getView().findViewById(R.id.toolbar_right);
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
        ImageView rightIcon = (ImageView) this.getView().findViewById(R.id.toolbar_right_icon);
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
        ImageView rightIcon = (ImageView) this.getView().findViewById(R.id.toolbar_left);
        if (rightIcon != null) {
            rightIcon.setVisibility(View.VISIBLE);
            rightIcon.setImageResource(resid);
        }
    }

    public boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            return ContextCompat.checkSelfPermission(MyApplication.getInstance(), permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    /**
     * 弹出进度框，点击返回键不会消失
     *
     * @param text
     * @param isCancelable
     */
    public void showProgressDialog(String text, boolean isCancelable) {
        dismissProgressDialog();
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(isCancelable);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(text);
        progressDialog.show();
    }

    /**
     * 隐藏进度框
     */
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView(@Nullable Bundle savedInstanceState, View view);

    public void requestPermission(String[] permissions, int requestCode) {
        //申请权限
        ActivityCompat.requestPermissions(mActivity, permissions, requestCode);
    }

    /**
     * 封装Intent跳转
     */
    public void skip(Class<?> clazz, boolean isCloseSelf) {
        Intent intent = new Intent(mActivity, clazz);
        mActivity.startActivity(intent);
        if (isCloseSelf) mActivity.finish();
        leftOutRightIn(mActivity);
    }

    public void skip(Class<?> clazz, Bundle data, boolean isCloseSelf) {
        Intent intent = new Intent(mActivity, clazz);
        if (data != null) intent.putExtra(PubConst.DATA, data);
        mActivity.startActivity(intent);
        if (isCloseSelf) mActivity.finish();
        leftOutRightIn(mActivity);
    }

    public void skip(Class<?> clazz, Serializable serializableObj, boolean isCloseSelf) {
        Intent intent = new Intent(mActivity, clazz);
        if (serializableObj != null) intent.putExtra(PubConst.DATA, serializableObj);
        mActivity.startActivity(intent);
        if (isCloseSelf) mActivity.finish();
        leftOutRightIn(mActivity);
    }

    protected void skipForResult(Class<?> clazz, Bundle data, int requestCode) {
        Intent intent = new Intent(mActivity, clazz);
        if (data != null) intent.putExtra(PubConst.DATA, data);
        startActivityForResult(intent, requestCode);
        leftOutRightIn(mActivity);
    }

    /**
     * 做出右进的效果
     *
     * @param context
     */
    public static void leftOutRightIn(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /////////////////////////////////////网络请求//////////////////////
    public IHttpCallBack baseCallBack = new IHttpCallBack() {
        public void onSuccess(Object o, String tag) {
            LogUtil.showLog("outputtag==" + tag + "成功=====" + new Gson().toJson(o));
//            onDestroy();
            onDestroy(Integer.valueOf(tag));
            onSuccessCallBack(o, tag);
        }

        public void onFailure(String failureMsg, String tag) {
            LogUtil.showLog("outputtag==" + tag + "失败=====" + failureMsg);
//            onDestroy();
            onDestroy(Integer.valueOf(tag));
            onFailureCallBack(failureMsg, tag);
        }
    };

    public <T> void onSuccessCallBack(T t, String tag) {
    }

    public void onFailureCallBack(String failureMsg, String tag) {
        if (failureMsg == null)
            Toast.makeText(mActivity, getString(R.string.reterror_error), Toast.LENGTH_LONG).show();
        else if (failureMsg.indexOf("java") >= 0 || failureMsg.endsWith("Error") || failureMsg.indexOf("Exception") >= 0 || failureMsg.contains("refused")) {
            Toast.makeText(mActivity, getString(R.string.reterror_network_java_net_error), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mActivity, failureMsg, Toast.LENGTH_LONG).show();
        }
    }
}
