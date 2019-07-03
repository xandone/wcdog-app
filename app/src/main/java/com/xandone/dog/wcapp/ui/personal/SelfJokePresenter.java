package com.xandone.dog.wcapp.ui.personal;

import com.xandone.dog.wcapp.api.CommonSubscriber;
import com.xandone.dog.wcapp.base.RxPresenter;
import com.xandone.dog.wcapp.model.DataManager;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.ui.joke.JokeContact;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2019/6/24 23:09
 */
public class SelfJokePresenter extends RxPresenter<SelfJokeContact.MyView> implements SelfJokeContact.Presenter {
    private DataManager dataManager;

    @Inject
    public SelfJokePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getSelfJokes(int page, int count, String tag, final int mode) {
        Flowable<BaseResponse<List<JokeBean>>> result = dataManager.getSelfJokes(page, count, tag);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<JokeBean>>>(view) {
                    @Override
                    public void onSuccess(BaseResponse<List<JokeBean>> jokeBean) {
                        if (mode == JokeContact.MODE_ONE) {
                            view.showContent(jokeBean.getData(), jokeBean.getTotal());
                        } else if (mode == JokeContact.MODE_MORE) {
                            view.showContentMore(jokeBean.getData(), jokeBean.getTotal());
                        }
                    }
                })
        );
    }

    @Override
    public void getSelfThumbs(int page, int count, String tag, final int mode) {
        Flowable<BaseResponse<List<JokeBean>>> result = dataManager.getSelfThumbs(page, count, tag);
        addSubscrible(result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<JokeBean>>>(view) {
                    @Override
                    public void onSuccess(BaseResponse<List<JokeBean>> jokeBean) {
                        if (mode == JokeContact.MODE_ONE) {
                            view.showContent(jokeBean.getData(), jokeBean.getTotal());
                        } else if (mode == JokeContact.MODE_MORE) {
                            view.showContentMore(jokeBean.getData(), jokeBean.getTotal());
                        }
                    }
                })
        );
    }
}
