package com.xandone.dog.wcapp.ui.comment;


import com.xandone.dog.wcapp.api.CommonSubscriber;
import com.xandone.dog.wcapp.api.RxHelper;
import com.xandone.dog.wcapp.base.RxPresenter;
import com.xandone.dog.wcapp.model.DataManager;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.CommentBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: xandone
 * created on: 2018/3/16 9:08
 */

public class CommentPresenter extends RxPresenter<CommentContact.MyView> implements CommentContact.Presenter {
    private DataManager dataManager;

    @Inject
    public CommentPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getContentList(int page, int rows, String jokeId, final int mode) {
        Flowable<BaseResponse<List<CommentBean>>> result = dataManager.getJokeCommentList(page, rows, jokeId);
        addSubscrible(result.compose(RxHelper.<BaseResponse<List<CommentBean>>>handleIO())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<CommentBean>>>(view) {
                    @Override
                    public void onSuccess(BaseResponse<List<CommentBean>> baseResponse) {
                        if (mode == CommentContact.MODE_ONE) {
                            view.showContent(baseResponse.getData());
                        } else if (mode == CommentContact.MODE_MORE) {
                            view.showContentMore(baseResponse.getData());
                        }
                    }
                })
        );
    }

    @Override
    public void addComment(String jokeId, String userId, String details) {
        final Flowable<BaseResponse<List<CommentBean>>> result = dataManager.addComment(jokeId, userId, details);
        addSubscrible(result.compose(RxHelper.<BaseResponse<List<CommentBean>>>handleIO())
                .subscribeWith(new CommonSubscriber<BaseResponse<List<CommentBean>>>(view) {
                    @Override
                    public void onSuccess(BaseResponse<List<CommentBean>> baseResponse) {
                        view.showCommentResult(baseResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        view.showCommentError();
                    }
                })
        );
    }
}
