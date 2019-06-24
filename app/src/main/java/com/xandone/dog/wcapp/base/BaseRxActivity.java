package com.xandone.dog.wcapp.base;

import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.di.component.ActivityComponent;
import com.xandone.dog.wcapp.di.component.DaggerActivityComponent;
import com.xandone.dog.wcapp.di.module.ActivityModule;

import javax.inject.Inject;


/**
 * author: xandone
 * created on: 2019/3/5 15:30
 */

public abstract class BaseRxActivity<T extends RxPresenter> extends BaseActivity {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();

    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public abstract void initInject();

    @Override
    public void initData() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

}
