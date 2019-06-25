package com.xandone.dog.wcapp.api;

import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.uitils.ProgressDialogUtil;

/**
 * author: xandone
 * created on: 2019/3/25 14:39
 */
public class DialogSubscriber<T> extends CommonSubscriber<T> {
    public DialogSubscriber(BaseView baseView) {
        super(baseView);
    }

    protected DialogSubscriber(BaseView view, String errorMsg) {
        super(view, errorMsg);
    }

    protected DialogSubscriber(BaseView view, boolean isShowErrorState) {
        super(view, isShowErrorState);
    }

    protected DialogSubscriber(BaseView view, String errorMsg, boolean isShowErrorState) {
        super(view, errorMsg, isShowErrorState);
    }


    @Override
    public void onNext(T t) {
        dismissLoadingDialog();
        super.onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        dismissLoadingDialog();
        super.onError(t);
    }

    protected void showLoadingDialog(boolean cancelable) {
        showLoadingDialog("正在请求...", cancelable);
    }

    protected void showLoadingDialog(String msg, boolean cancelable) {
        ProgressDialogUtil.showProgress(App.sContext, msg, cancelable);
    }

    protected void dismissLoadingDialog() {
        ProgressDialogUtil.dismiss();
    }
}
