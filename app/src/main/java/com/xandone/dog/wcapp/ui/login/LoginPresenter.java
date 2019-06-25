package com.xandone.dog.wcapp.ui.login;

import android.text.TextUtils;

import com.xandone.dog.wcapp.api.CommonSubscriber;
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

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2019/3/7 14:09
 */

public class LoginPresenter extends RxPresenter<LoginContact.View> implements LoginContact.Presenter {
    private DataManager mDataManager;

    @Inject
    LoginPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void login(final String email, final String psw) {
        Flowable<BaseResponse<List<UserBean>>> result = mDataManager.login(email, psw);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<UserBean>>>(view) {
                    @Override
                    public void onNext(BaseResponse<List<UserBean>> baseResponse) {

                        if (!SimpleUtils.isNetworkConnected()) {
                            ToastUtils.showShort("没有网络");
                            return;
                        }

                        if (baseResponse == null) {
                            ToastUtils.showShort("服务器异常,请稍后再试");
                            return;
                        }

                        if (baseResponse.getCode() == 200 && baseResponse.getData() != null
                                && !baseResponse.getData().isEmpty()) {
                            UserBean userBean = baseResponse.getData().get(0);
                            userBean.setName(email);
                            userBean.setPassword(psw);
                            UserInfoCache.setLogin(true);
                            UserInfoCache.setUserBean(userBean);

                            SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
                            String infoJson = GsonUtil.objToJson(userBean);
                            spUtils.put(Constants.USER_INFO_KEY, infoJson);
                            view.showContent(baseResponse);
                        } else if (!TextUtils.isEmpty(baseResponse.getMsg())) {
                            ToastUtils.showShort(baseResponse.getMsg());
                        } else {
                            ToastUtils.showShort("服务器异常,请稍后再试");
                        }


                    }

                })
        );
    }
}
