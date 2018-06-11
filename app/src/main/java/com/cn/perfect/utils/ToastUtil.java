package com.cn.perfect.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.cn.perfect.application.MyApplication;


/**
 * Description：吐司工具类
 * Created on 2017/9/18
 * Author : 郭
 */

public class ToastUtil {
    public static void showToast(String str, int duration) {
        Toast toast = Toast.makeText(MyApplication.getInstance(), str, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    public static void showToast(String str) {
        showToast(str, Toast.LENGTH_SHORT);

    }
}
