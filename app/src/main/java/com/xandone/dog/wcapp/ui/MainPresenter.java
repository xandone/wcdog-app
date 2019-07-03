package com.xandone.dog.wcapp.ui;


import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xandone.dog.wcapp.api.CommonSubscriber;
import com.xandone.dog.wcapp.api.RxHelper;
import com.xandone.dog.wcapp.base.RxPresenter;
import com.xandone.dog.wcapp.model.DataManager;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.ApkBean;
import com.xandone.dog.wcapp.uitils.SimpleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * author: Admin
 * created on: 2019/7/3 14:21
 * description:
 */
public class MainPresenter extends RxPresenter<MainContact.MyView> implements MainContact.Presenter {

    private DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getLastVersion() {
        Flowable<BaseResponse<List<ApkBean>>> result = dataManager.getLastVersion();
        addSubscrible(result.compose(RxHelper.<BaseResponse<List<ApkBean>>>handleIO())
                .compose(RxHelper.<List<ApkBean>>handleRespose())
                .subscribeWith(new CommonSubscriber<List<ApkBean>>(view) {
                    @Override
                    public void onSuccess(List<ApkBean> apkBeanList) {
                        Logger.d(new Gson().toJson(apkBeanList));
                        if (SimpleUtils.isNotEmpty(apkBeanList)) {
                            view.showResult(apkBeanList.get(0));
                        }
                    }
                })
        );
    }
}
