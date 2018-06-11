package com.cn.perfect.wapi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cn.perfect.R;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class ConnectDialog implements ILoadinDialog {

    private static volatile ConnectDialog dialog;

    // private Activity mActivity;
    private Reference<Activity> mActivityRef;


    private ConnectDialog() {

    }

    public static ConnectDialog getInstanceDialog() {

        if (dialog == null) synchronized (ConnectDialog.class) {
            if (dialog == null) dialog = new ConnectDialog();
        }
        return dialog;
    }

    @Override
    public void dismissDialog(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = activity.findViewById(R.id.dialog_view);
        if (view == null) {
            return;
        }
        view.setVisibility(View.GONE);
    }

    @Override
    public boolean isShow() {
//        if (mActivityRef == null || mActivityRef.get() == null) {
//            return false;
//        }
//        View view = mActivityRef.get().findViewById(R.id.loading_rlayout);
//        if (view != null && view.getVisibility() == View.VISIBLE) {
//            return true;
//        }
        return false;
    }

    @Override
    public void showDialog(Activity activity) {
        if (activity == null) {
            return;
        }
        showDialog(activity, null);
    }

    @Override
    public void showDialog(Activity activity, String textStr) {
        if (activity == null) {
            return;
        }
        mActivityRef = new WeakReference<Activity>(activity);
        View viewLoading = activity.findViewById(R.id.dialog_view);
        if (viewLoading == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            viewLoading = inflater.inflate(R.layout.loading_dialog, null);
//            SpinKitView spin_kit = (SpinKitView) viewLoading.findViewById(R.id.spin_kit);
//            Sprite fadingCircle = new Circle();
//            spin_kit.setIndeterminateDrawable(fadingCircle);
            if (textStr != null) {
                TextView tv = (TextView) viewLoading.findViewById(R.id.tipTextView);
                tv.setText(textStr);
            }
            viewLoading.setOnTouchListener(new ViewGroup.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            viewLoading.setVisibility(View.VISIBLE);
            ViewGroup rootFrameLayout = (ViewGroup) activity.getWindow().peekDecorView();
            rootFrameLayout.addView(viewLoading);
            rootFrameLayout.invalidate();
        } else {
            viewLoading.setVisibility(View.VISIBLE);
        }
    }

}
