package com.cn.perfect.wapi;

import android.app.Activity;

/**
 * 展示dialog接口
 * @author renhui
 *
 */
public interface ILoadinDialog {
	/**
	 * 展示dialog
	 * @param activity 要展示的activity
	 * @param textStr	要展示的文字
	 */
	public void showDialog(Activity activity, String textStr);
	/**
	 * 展示dialog
	 * @param activity 要展示的activity
	 */
	public void showDialog(Activity activity);
	/**
	 * 判断是否显示了dialog
	 * @return
	 */
	public boolean isShow();
	/**
	 * 关闭dialog
	 * @param activity
	 */
	public void dismissDialog(Activity activity);
}
