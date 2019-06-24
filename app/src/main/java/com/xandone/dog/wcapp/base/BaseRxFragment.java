package com.xandone.dog.wcapp.base;

import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.di.component.DaggerFragmentComponent;
import com.xandone.dog.wcapp.di.component.FragmentComponent;
import com.xandone.dog.wcapp.di.module.FragmentModule;

import javax.inject.Inject;


/**
 * author: xandone
 * created on: 2019/3/5 15:31
 */

public abstract class BaseRxFragment<T extends RxPresenter> extends BaseFragment {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }


    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void initData() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract void initInject();

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }


    /**
     * 懒加载重写
     */
    @Override
    protected void lazyLoadData() {

    }
}
