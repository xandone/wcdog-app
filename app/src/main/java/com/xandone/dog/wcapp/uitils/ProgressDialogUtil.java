package com.xandone.dog.wcapp.uitils;

import android.app.ProgressDialog;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * author: xandone
 * Created on: 2018/4/4 16:11
 */

public class ProgressDialogUtil {
    private ProgressDialog progress;
    private WeakReference<Context> contextReference;
    private static ProgressDialogUtil mInstance;

    public static void showProgress(Context context, String message) {
        showProgress(context, message, true);
    }


    public static void showProgress(Context context, String message, boolean cancelable) {
        showProgress(context, cancelable);
        setMessage(message);
    }

    private static ProgressDialogUtil getInstance() {
        if (null == mInstance) {
            mInstance = new ProgressDialogUtil();
        }
        return mInstance;
    }

    /**
     * 通过setMessage和showProgress，可以实现进度条的显示 在按后退键的时候消失且不会再弹对话框。
     *
     * @param message
     */
    public static void setMessage(String message) {
        if (null == mInstance || null == mInstance.progress){
            return;
        }
        mInstance.progress.setMessage(message);
    }

    public static void showProgress(Context context, boolean cancelable) {
        mInstance = getInstance();
        if (mInstance.contextReference == null || mInstance.contextReference.get() != context) {
            mInstance.contextReference = new WeakReference<>(context);
            mInstance.progress = new ProgressDialog(mInstance.contextReference.get());
        }
        mInstance.progress.setCancelable(cancelable);
        mInstance.progress.setCanceledOnTouchOutside(false);
        if (!mInstance.progress.isShowing()) {
            mInstance.progress.show();
        }
    }

    public static void dismiss() {
        if (null == mInstance) {
            return;
        }
        if (mInstance.progress != null && mInstance.progress.isShowing()) {
            mInstance.progress.dismiss();
            mInstance = null;
        }
    }

    public static boolean isShowing() {
        return mInstance.progress.isShowing();
    }
    /**
     * 得到dialog对象，分享时的dialog可以用这个
     */
    /*public static ProgressDialog getDialog(Context context,String content){
		if (contextReference==null||contextReference.get()!=context) {
			contextReference = new WeakReference<>(context);
			progress = new ProgressDialog(contextReference.get());
		}
		progress.setMessage(content);
		return progress;
	}*/
}
