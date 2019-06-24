package com.xandone.dog.wcapp.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author: xandone
 * created on: 2019/3/5 15:43
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T view;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        unSubscrible();
    }

    //移除并取消订阅
    public void dispose(Disposable disposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.remove(disposable);
        }
    }

    //取消所有的订阅
    public void unSubscrible() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public void addSubscrible(Disposable disposable) {
        if (disposable == null) return;
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
