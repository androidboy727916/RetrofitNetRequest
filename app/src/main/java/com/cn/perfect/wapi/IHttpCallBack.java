package com.cn.perfect.wapi;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;

import com.cn.perfect.utils.LogUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;


/**
 * Description：
 * Created on 2017/4/28
 * Author : 郭
 */
public abstract class IHttpCallBack<T> {
    public abstract void onSuccess(T t, String tag);

    public abstract void onFailure(String failureMsg, String tag);


    private SparseArray<Callback> callbacks;

//    public Callback getCallBack(int tag, Class clasz, Activity context) {
//        if (callbacks == null) callbacks = new SparseArray<>();
//        this.context = context;
//        Callback callback = callbacks.get(tag);
//        if (callback == null) {
//            callback = new Callback();
//            callback.setTag(String.valueOf(tag));
//            callback.setClasz(clasz);
//            callbacks.put(tag, callback);
//        }
//        return callback;
//    }

    public Callback getCallBack(int tag, Class clasz, boolean showDialog, Context context) {
        if (callbacks == null) callbacks = new SparseArray<>();
        Callback callback = callbacks.get(tag);
        if (callback == null) {
            callback = new Callback();
            callback.setTag(String.valueOf(tag));
            callback.setClasz(clasz);
            callback.setShowDialog(showDialog,context);
            callback.setActivity(context);
            callbacks.put(tag, callback);
        }
        return callback;
    }

    public void onDestroy() {
        if (callbacks != null) {
            LogUtil.showLog("IHttpCallBack", "onDestroy.41.[] onDestroy clear callbacks");
            callbacks.clear();
            callbacks = null;
        }
    }

    public void onDestroy(int tag) {
        if (callbacks != null) {
            if (callbacks.get(tag) != null) {
                callbacks.remove(tag);
            }
        }
    }

    public class Callback implements retrofit.Callback<Response> {
        Class clasz;
        String tag;
        boolean showDialog;
        WeakReference<Activity> ref;

        public void setActivity(Context context) {
            if (context != null && context instanceof Activity) {
                ref = new WeakReference<Activity>((Activity) context);
            }
        }

        public void setShowDialog(boolean showDialog, Context context) {
            this.showDialog = showDialog;
            showDialog(showDialog,context);
        }

        private void setTag(String tag) {
            this.tag = tag;
        }

        private void setClasz(Class clasz) {
            this.clasz = clasz;
        }

        @Override
        public void success(Response t, Response response2) {
            if (callbacks == null || callbacks.size() == 0) {
                LogUtil.showLog("Callback", "success.63.[t, response2] callbacks is empty return ");
                return;
            }
            try {
                if (showDialog && ref != null) {
                    ConnectDialog.getInstanceDialog().dismissDialog(ref.get());
                }
            } catch (Exception e) {
            }
            try {
                String jsonString = new String(((TypedByteArray) t.getBody()).getBytes());
                if (clasz != null) {
                    //处理 Date类型的
//                    GsonConverter converter = new GsonConverter( new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create());
                    GsonConverter converter = new GsonConverter(new Gson());
                    BaseRespone res = (BaseRespone) converter.fromBody(t.getBody(), BaseRespone.class);

                    if (res.getCode()==1) {
                        T tt = (T) converter.fromBody(t.getBody(), clasz);
                        LogUtil.showLog("res===="+tt.toString());
                        if (tt != null) {
                            onSuccess(tt, tag);
                        } else onFailure("no data", tag);
                    } else {
                        onFailure(res.getErrMsg(), tag);
                    }
                } else {
                    JSONObject obj = new JSONObject(jsonString);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //暂时不处理异常
//                onFailure(e.getMessage(), tag);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            if (callbacks == null || callbacks.size() == 0) {
                LogUtil.showLog("Callback", "failure.91.[error] callbacks is empty return ");
                return;
            }
            try {
                if (showDialog && ref != null) {
                    ConnectDialog.getInstanceDialog().dismissDialog(ref.get());
                }
            } catch (Exception e) {
            }
            onFailure(error.getMessage(), tag);
        }

        public Callback showDialog(boolean showDialog, Context mcontext) {
            if (showDialog) {
                ConnectDialog.getInstanceDialog().showDialog((Activity) mcontext);
            }
            return null;
        }
    }

}
