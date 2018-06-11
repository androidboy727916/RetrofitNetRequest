package com.cn.perfect.utils;

import android.util.Log;

import com.cn.perfect.BuildConfig;


/**
 * log  工具类
 */
public class LogUtil {
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "langooo";

    public static void i(String info) {
        if (DEBUG) Log.i(TAG, info);
    }

    public static void d(String info) {
        if (DEBUG) Log.d(TAG, info);
    }

    public static void e(String info) {
        if (DEBUG) Log.e(TAG, info);
    }

    public static void showLog(Object log) {
        Log.e(TAG, String.valueOf(log));
    }

    public static void showLog(String tag, Object log) {
        Log.e(tag, String.valueOf(log));
    }

    public static void showAll(String info) {
        int LOG_MAXLENGTH = 5000;
        if (DEBUG) {
            int strLength = info.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    i(info.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    i(info.substring(start, strLength));
                    break;
                }
            }
        }
    }
}
