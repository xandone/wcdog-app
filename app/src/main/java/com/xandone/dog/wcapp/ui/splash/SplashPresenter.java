package com.xandone.dog.wcapp.ui.splash;


import android.util.Log;

import com.xandone.dog.wcapp.base.RxPresenter;
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.model.DataManager;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.uitils.GsonUtil;
import com.xandone.dog.wcapp.uitils.SPUtils;
import com.xandone.dog.wcapp.uitils.SimpleUtils;
import com.xandone.dog.wcapp.uitils.ToastUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2019/6/12 9:16
 */

public class SplashPresenter extends RxPresenter<SplashContact.View> implements SplashContact.Presenter {
    private DataManager mDataManager;

    @Inject
    SplashPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getContent(final String name, final String psw) {
        Flowable<BaseResponse<List<UserBean>>> result = mDataManager.login(name, psw);

        addSubscrible(result.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BaseResponse<List<UserBean>>>() {
                            @Override
                            public void accept(@NonNull BaseResponse<List<UserBean>> baseResponse) throws Exception {

                                if (!SimpleUtils.isNetworkConnected()) {
                                    ToastUtils.showShort("没有网络");
                                    startAct();
                                    return;
                                }

                                if (baseResponse != null) {
//                                     密码正确
                                    if (baseResponse.getCode() == 200 && baseResponse.getData() != null
                                            && !baseResponse.getData().isEmpty()) {
                                        UserBean result = baseResponse.getData().get(0);
                                        result.setName(name);
                                        result.setPassword(psw);
                                        UserInfoCache.setUserBean(result);
                                        UserInfoCache.setLogin(true);

                                        String userResult = GsonUtil.objToJson(result);
                                        SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
                                        spUtils.put(Constants.USER_INFO_KEY, userResult);
                                    }

                                }
                                view.showContent(baseResponse);
                                startAct();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                startAct();
                            }
                        })
        );
    }

    void startAct() {
        addSubscrible(Flowable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        view.jumpAct();
                    }
                })
        );
    }
}
