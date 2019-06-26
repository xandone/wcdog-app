package com.xandone.dog.wcapp.ui.jokedetails;

import com.xandone.dog.wcapp.api.CommonSubscriber;
import com.xandone.dog.wcapp.base.RxPresenter;
import com.xandone.dog.wcapp.model.DataManager;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.JokeBean;


import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2019/6/15 16:34
 */

public class JokeDetailsPresenter extends RxPresenter<JokeDetailsContact.View> implements JokeDetailsContact.Presenter {
    private DataManager dataManager;

    @Inject
    public JokeDetailsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getThumbsJoke(String jokeId, String jokeUserId) {
        Flowable<BaseResponse> result = dataManager.getThumbsJoke(jokeId, jokeUserId);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse>(view) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        super.onNext(baseResponse);
                        view.showContent(baseResponse);
                    }
                })
        );
    }

    @Override
    public void thumbsJoke(String jokeId, String jokeUserId) {
        Flowable<BaseResponse> result = dataManager.thumbsJoke(jokeId, jokeUserId);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse>(view) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        super.onNext(baseResponse);
                        view.thumbsJokeResult(baseResponse);
                    }
                })
        );
    }

    @Override
    public void addToCollection(JokeBean jokeBean) {

    }
}
