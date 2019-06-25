package com.xandone.dog.wcapp.ui.comment;

import com.xandone.dog.wcapp.base.BasePresenter;
import com.xandone.dog.wcapp.base.BaseView;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.CommentBean;

import java.util.List;

/**
 * author: xandone
 * created on: 2018/3/16 9:08
 */

public interface CommentContact {
    int MODE_ONE = 0;
    int MODE_MORE = 1;

    interface MyView extends BaseView {
        void showContent(List<CommentBean> commentBean);

        void showContentMore(List<CommentBean> commentBean);

        void showCommentResult(BaseResponse<List<CommentBean>> response);

        void showCommentError();
    }

    interface Presenter extends BasePresenter<MyView> {
        void getContentList(int page, int rows, String jokeId, int mode);

        void addComment(String jokeId, String userId, String details);
    }

}
