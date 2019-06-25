package com.xandone.dog.wcapp.ui.comment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxActivity;
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.CommentBean;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.ui.joke.JokeContact;
import com.xandone.dog.wcapp.uitils.SimpleUtils;
import com.xandone.dog.wcapp.uitils.ToastUtils;
import com.xandone.dog.wcapp.uitils.XString;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;
import com.xandone.dog.wcapp.widget.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: xandone
 * created on: 2018/3/15 22:04
 */

public class JokeCommentActivity extends BaseRxActivity<CommentPresenter> implements CommentContact.MyView {
    @BindView(R.id.act_joke_comment_list)
    RecyclerView act_joke_comment_list;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.act_joke_comment_et)
    EditText act_joke_comment_et;

    private BaseQuickAdapter mAdapter;
    private List<CommentBean> comments;
    private int mPage = 1;
    private int mCount = 10;

    private LoadingLayout.OnReloadListener onReloadListener;
    private JokeBean jokeBean;
    private RequestManager requestManager;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_joke_comment_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, getString(R.string.x_joke_comment_title));

        jokeBean = (JokeBean) getIntent().getSerializableExtra(Constants.KEY_JOKEBEAN);
        if (jokeBean == null) {
            return;
        }
        requestManager = Glide.with(this);
        comments = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<CommentBean, BaseViewHolder>(R.layout.item_comment_list, comments) {
            @Override
            protected void convert(BaseViewHolder helper, CommentBean commentBean) {
                helper.setText(R.id.item_joke_comment_name, commentBean.getCommentNick());
                helper.setText(R.id.item_joke_comment_details, commentBean.getCommentDetails());
                helper.setText(R.id.item_joke_comment_date, commentBean.getCommentDate());

                XGlide.loadImage(requestManager, helper.<ImageView>getView(R.id.item_joke_comment_icon),
                        commentBean.getCommentIcon(), R.mipmap.df_icon);

            }
        };
        act_joke_comment_list.setAdapter(mAdapter);
        act_joke_comment_list.setLayoutManager(new LinearLayoutManager(App.sContext));

        mPresenter.getContentList(mPage, mCount, jokeBean.getJokeId(), CommentContact.MODE_ONE);
        loadingLayout.setLoadingTips(LoadingLayout.loading);

        onReloadListener = new LoadingLayout.OnReloadListener() {
            @Override
            public void reLoad() {
                mPage = 1;
                mPresenter.getContentList(mPage, mCount, jokeBean.getJokeId(), JokeContact.MODE_ONE);
                loadingLayout.setLoadingTips(LoadingLayout.loading);
            }
        };
        loadingLayout.setOnReloadListener(onReloadListener);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mPresenter.getContentList(mPage, mCount, jokeBean.getJokeId(), JokeContact.MODE_ONE);
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                mPage++;
                mPresenter.getContentList(mPage, mCount, jokeBean.getJokeId(), JokeContact.MODE_MORE);
            }
        });

        act_joke_comment_et.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(act_joke_comment_et, 0);
            }
        });

    }

    @Override
    public void showContent(List<CommentBean> commentBean) {
        mRefreshLayout.finishRefresh();

        if (commentBean == null || commentBean.isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        comments.clear();
        comments.addAll(commentBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showContentMore(List<CommentBean> commentBean) {
        mRefreshLayout.finishLoadMore();
        if (commentBean == null || commentBean.isEmpty()) {
            return;
        }
        comments.addAll(commentBean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
        loadingLayout.setLoadingTips(loadStatus);
    }

    @Override
    public void showCommentResult(BaseResponse<List<CommentBean>> response) {
        dismissLoadingDialog();
        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            return;
        }
        if (response.getCode() == 200) {
            if (loadingLayout.getVisibility() == View.VISIBLE) {
                loadingLayout.setVisibility(View.GONE);
            }
            ToastUtils.showShort("评论成功");
            act_joke_comment_et.setText("");

            CommentBean commentBean = response.getData().get(0);
            comments.add(0, commentBean);
            mAdapter.notifyItemChanged(0);
        } else {
            ToastUtils.showShort("评论失败");
        }
    }

    @Override
    public void showCommentError() {
        dismissLoadingDialog();
    }

    @OnClick({R.id.act_joke_comment_commit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.act_joke_comment_commit:
                if (!UserInfoCache.isLogin()) {
                    ToastUtils.showShort("你还未登录");
                    break;
                }
                String jokeId = jokeBean.getJokeId();
                String userId = UserInfoCache.getUserBean().getUserId();
                String details = act_joke_comment_et.getText().toString();
                if (XString.isEmpty(details)) {
                    ToastUtils.showShort("请输入内容");
                    break;
                }
                SimpleUtils.hideSoftInput(this);
                showLoadingDialog(false);
                mPresenter.addComment(jokeId, userId, details);
                break;
            default:
                break;
        }
    }
}
