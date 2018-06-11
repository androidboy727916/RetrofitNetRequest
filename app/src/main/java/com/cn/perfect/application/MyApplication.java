package com.cn.perfect.application;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * Description：
 * Created on 2017/9/14
 * Author : 郭
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private Set<Activity> allActivities;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 保存activity实例
     *
     * @param act
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除activity实例
     *
     * @param act
     */
    public void removeActivity(Activity act) {
        if (allActivities != null && allActivities.contains(act)) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出所有页面
     */
    public void finishAllActivity() {
        if (allActivities != null) {
            for (Activity act : allActivities) {
                act.finish();
            }
            allActivities.clear();
        }
    }

    /**
     * 得到当前的act
     */
    public Set getAllActivity() {
        if (allActivities != null) {

            return allActivities;
        }
        return null;
    }

    /**
     * 退出登录
     */
    public void logout(Activity activity) {
        if (allActivities != null) {
            for (Activity act : allActivities) {
                if (activity != act) {
                    act.finish();
                }
            }
        }
    }
}
