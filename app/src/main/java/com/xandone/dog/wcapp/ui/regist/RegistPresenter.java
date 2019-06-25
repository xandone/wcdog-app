package com.xandone.dog.wcapp.ui.regist;


import android.text.TextUtils;
import android.util.Log;

import com.xandone.dog.wcapp.api.CommonSubscriber;
import com.xandone.dog.wcapp.base.RxPresenter;
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.model.DataManager;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.uitils.GsonUtil;
import com.xandone.dog.wcapp.uitils.SPUtils;
import com.xandone.dog.wcapp.uitils.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/3/6 14:43
 */

public class RegistPresenter extends RxPresenter<RegistContact.View> implements RegistContact.Presenter {
    private DataManager mDataManager;

    @Inject
    RegistPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void regist(final String name, final String psw, String nick) {
        Flowable<BaseResponse<List<UserBean>>> result = mDataManager.regist(name, psw, nick);

        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<UserBean>>>(view) {
                    @Override
                    public void onNext(BaseResponse<List<UserBean>> baseResponse) {
                        if (baseResponse != null) {
                            if (baseResponse.getCode() == 200 && baseResponse.getData() != null
                                    && !baseResponse.getData().isEmpty()) {
                                UserBean loginBean = baseResponse.getData().get(0);
                                loginBean.setName(name);
                                loginBean.setPassword(psw);
                                UserInfoCache.setLogin(true);
                                UserInfoCache.setUserBean(loginBean);

                                SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
                                String infoJson = GsonUtil.objToJson(loginBean);
                                spUtils.put(Constants.USER_INFO_KEY, infoJson);
                                view.showContent(baseResponse);
                            } else if (!TextUtils.isEmpty(baseResponse.getMsg())) {
                                ToastUtils.showShort(baseResponse.getMsg());
                            } else {
                                ToastUtils.showShort("服务器异常,请稍后再试");
                            }

                        } else {
                            ToastUtils.showShort("服务器异常,请稍后再试");
                        }
                    }
                })
        );
    }
}
