package com.xandone.dog.wcapp.ui.personal;


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
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * author: xandone
 * Created on: 2018/5/9 17:12
 */

public class PersonalPresenter extends RxPresenter<PersonalContact.MyView> implements PersonalContact.Presenter {
    private DataManager dataManager;

    @Inject
    public PersonalPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void changeUserIcon(Map<String, String> maps, MultipartBody.Part part) {

    }

    @Override
    public void updateUserInfo(final String talk, final String address) {
        Flowable<BaseResponse<List<UserBean>>> result = dataManager.updateUserInfo(UserInfoCache.getUserBean().getUserId(), talk, address);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<UserBean>>>(view) {
                    @Override
                    public void onSuccess(BaseResponse<List<UserBean>> baseResponse) {
                        if (baseResponse == null || SimpleUtils.isEmpty(baseResponse.getData())) {
                            ToastUtils.showShort("服务器异常,请稍后再试");
                            return;
                        }

                        if (baseResponse.getCode() == 200) {
                            UserBean userBean = baseResponse.getData().get(0);
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
