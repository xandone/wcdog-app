package com.xandone.dog.wcapp.ui.personal;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxFragment;
import com.xandone.dog.wcapp.model.bean.HeadArticleBean;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;
import com.xandone.dog.wcapp.widget.LoadingLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: xandone
 * created on: 2019/6/24 22:19
 */
public class SelfJokeFragment extends BaseRxFragment<SelfJokePresenter> implements SelfJokeContact.MyView {
    @BindView(R.id.jokeList)
    RecyclerView jokeList;

    private BaseQuickAdapter mAdapter;
    private List<JokeBean> jokes;
    private int mPage = 1;
    private int mCount = 10;
    private RequestManager requestManager;

    @Override
    public int setLayout() {
        return R.layout.frag_self_joke;
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void initData() {
        super.initData();

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
        jokeList.setAdapter(mAdapter);
        jokeList.setLayoutManager(new LinearLayoutManager(App.sContext));


        mPresenter.getJokeList(1, 10, "-1", SelfJokeContact.MODE_ONE);
    }

    @Override
    public void showContent(List<JokeBean> jokeList, int total) {
//        mRefreshLayout.finishRefresh();
        if (jokeList == null || jokeList.isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        jokes.clear();
        jokes.addAll(jokeList);
        mAdapter.notifyDataSetChanged();

        if (total <= mCount) {
//            mRefreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void showContentMore(List<JokeBean> jokeList, int total) {
//        mRefreshLayout.finishLoadMore();
        if (jokeList == null || jokeList.isEmpty()) {
            return;
        }
        jokes.addAll(jokeList);
        mAdapter.notifyDataSetChanged();

        if (total <= mCount * mPage) {
//            mRefreshLayout.setNoMoreData(true);
        }
    }
}
