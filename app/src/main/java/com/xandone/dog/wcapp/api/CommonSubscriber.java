package com.xandone.dog.wcapp.api;

import android.text.TextUtils;

import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.exception.ApiException;
import com.xandone.dog.wcapp.uitils.LogUtils;
import com.xandone.dog.wcapp.uitils.SimpleUtils;
import com.xandone.dog.wcapp.uitils.ToastUtils;
import com.xandone.dog.wcapp.widget.LoadingLayout;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * author: xandone
 * created on: 2019/3/29 14:01
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState;

    //默认开启
    public CommonSubscriber(BaseView baseView) {
        this(baseView, true);
    }

    protected CommonSubscriber(BaseView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected CommonSubscriber(BaseView view, boolean isShowErrorState) {
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    protected CommonSubscriber(BaseView view, String errorMsg, boolean isShowErrorState) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onNext(T t) {
        if (!SimpleUtils.isNetworkConnected()) {
            ToastUtils.showShort("无法连接，请检查网络");
        }
        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {
        if (mView == null) {
            return;
        }
        if (isShowErrorState) {
            if (!TextUtils.isEmpty(mErrorMsg)) {
                mView.showMsg(mErrorMsg, LoadingLayout.serverError);
            } else if (t instanceof ApiException) {
                mView.showMsg(t.toString(), LoadingLayout.serverError);
            } else if (t instanceof HttpException) {
                mView.showMsg("数据加载失败", LoadingLayout.netError);
            } else {
                mView.showMsg("未知错误", LoadingLayout.serverError);
                LogUtils.d(t.toString());
            }

            mView.stateError();
        }

        ApiException ex = ApiException.handleException(t);
        if (!TextUtils.isEmpty(ex.getMessage())) {
            ToastUtils.showShort(ex.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);
}
