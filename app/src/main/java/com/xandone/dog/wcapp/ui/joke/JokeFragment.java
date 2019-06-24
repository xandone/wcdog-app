package com.xandone.dog.wcapp.ui.joke;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
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
import com.xandone.dog.wcapp.base.BaseActivity;
import com.xandone.dog.wcapp.base.BaseRxFragment;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.model.bean.HeadArticleBean;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.model.bean.JokeListBean;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;
import com.xandone.dog.wcapp.widget.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: xandone
 * created on: 2019/3/6 13:34
 */

public class JokeFragment extends BaseRxFragment<JokePresenter> implements JokeContact.View {
    @BindView(R.id.frag_joke_list)
    RecyclerView fragJokeList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private BaseQuickAdapter mAdapter;
    private List<JokeBean> jokes;
    private List<HeadArticleBean.RowsBean> heads;
    private int mPage = 1;
    private int mCount = 10;
    private RequestManager requestManager;

    private LoadingLayout.OnReloadListener onReloadListener;

    public static final int RQS_CODE_JOKEBEAN = 1;
    public static final String KEY_RQS_IS_THUMB = "key_rqs_is_thumb";

    @Override
    public int setLayout() {
        return R.layout.frag_joke_layout;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();

        setToolBar(toolBar, "笑话");

        requestManager = Glide.with(mActivity);
        jokes = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<JokeBean, BaseViewHolder>(R.layout.item_joke_list, jokes) {
            @Override
            protected void convert(BaseViewHolder helper, JokeBean jokeBean) {
                helper.setText(R.id.item_joke_user_name, jokeBean.getJokeUserNick());
                helper.setText(R.id.item_joke_list_title, jokeBean.getTitle());
                helper.setText(R.id.item_joke_list_content, jokeBean.getContent());
                helper.setText(R.id.item_joke_list_like, String.valueOf(jokeBean.getArticleLikeCount()));
                helper.setText(R.id.item_joke_list_comment_count, String.valueOf(jokeBean.getArticleCommentCount()));
                helper.setText(R.id.item_joke_list_date, jokeBean.getPostTime());

                XGlide.loadImage(requestManager, helper.<ImageView>getView(R.id.item_joke_user_icon), jokeBean.getJokeUserIcon(), R.mipmap.df_icon);
                ImageView coverImg = helper.getView(R.id.item_joke_coverImg);
                if (TextUtils.isEmpty(jokeBean.getCoverImg())) {
                    coverImg.setVisibility(View.GONE);
                } else {
                    coverImg.setVisibility(View.VISIBLE);
                    XGlide.loadImage(requestManager, coverImg, jokeBean.getCoverImg(), R.mipmap.ic_error);
                }

            }
        };
        fragJokeList.setAdapter(mAdapter);
        fragJokeList.setLayoutManager(new LinearLayoutManager(App.sContext));

        loadingLayout.setLoadingTips(LoadingLayout.loading);

        onReloadListener = new LoadingLayout.OnReloadListener() {
            @Override
            public void reLoad() {
                mPage = 1;
                mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_ONE);
                loadingLayout.setLoadingTips(LoadingLayout.loading);
            }
        };
        loadingLayout.setOnReloadListener(onReloadListener);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_ONE);
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_MORE);
            }

        });

        mPresenter.getJokeList(mPage, mCount, JokeContact.MODE_ONE);

    }

    @Override
    public void showContent(List<JokeBean> jokeList, int total) {
        mRefreshLayout.finishRefresh();
        if (jokeList == null || jokeList.isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        jokes.clear();
        jokes.addAll(jokeList);
        mAdapter.notifyDataSetChanged();

        if (total <= mCount) {
            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void showContentMore(List<JokeBean> jokeList, int total) {
        mRefreshLayout.finishLoadMore();
        if (jokeList == null || jokeList.isEmpty()) {
            return;
        }
        jokes.addAll(jokeList);
        mAdapter.notifyDataSetChanged();

        if (total <= mCount * mPage) {
            mRefreshLayout.setNoMoreData(true);
        }

    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
        loadingLayout.setLoadingTips(loadStatus);
    }

    private void updataDatas(boolean isThumb, int position) {
        if (isThumb) {
            jokes.get(position).setArticleLikeCount(jokes.get(position).getArticleLikeCount() + 1);
            mAdapter.notifyItemChanged(position);
        }
    }


    @OnClick({R.id.toolbar_add})
    public void click(View view) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != BaseActivity.RESULT_OK) {
            return;
        }

        if (data == null) {
            return;
        }
        if (requestCode == 1) {
            boolean isThumb = data.getBooleanExtra(KEY_RQS_IS_THUMB, false);
            int position = data.getIntExtra(Constants.KEY_JOKEBEAN_POSITION, 0);
            updataDatas(isThumb, position);
        }
    }
}
