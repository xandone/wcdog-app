package com.xandone.dog.wcapp.ui.personal;

import android.content.Intent;
import android.os.Bundle;
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
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.eventbus.PersonalEvent;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.ui.jokedetails.JokeDetailsActivity;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;
import com.xandone.dog.wcapp.widget.LoadingLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: xandone
 * created on: 2019/6/24 22:19
 */
public class SelfJokeFragment extends BaseRxFragment<SelfJokePresenter> implements SelfJokeContact.MyView,
        BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.jokeList)
    RecyclerView jokeList;

    private BaseQuickAdapter mAdapter;
    private List<JokeBean> jokes;
    private int mPage = 1;
    private int mCount = 10;
    private RequestManager requestManager;
    private int mType;

    public static final int TYPE_SELF = 1;
    public static final int TYPE_THUMB = 2;
    public static final String TYPE_KEY = "type_key";

    public static SelfJokeFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE_KEY, type);
        SelfJokeFragment fragment = new SelfJokeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

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

        mType = getArguments().getInt(TYPE_KEY);
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
        mAdapter.setOnItemClickListener(this);

        if (!UserInfoCache.isLogin()) {
            return;
        }
        if (mType == TYPE_SELF) {
            mPresenter.getSelfJokes(1, 10, UserInfoCache.getUserBean().getUserId(), SelfJokeContact.MODE_ONE);
        } else if (mType == TYPE_THUMB) {
            mPresenter.getSelfThumbs(1, 10, UserInfoCache.getUserBean().getUserId(), SelfJokeContact.MODE_ONE);
        }

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
        if (mType == TYPE_SELF) {
            EventBus.getDefault().post(new PersonalEvent(TYPE_SELF, total));
        } else if (mType == TYPE_THUMB) {
            EventBus.getDefault().post(new PersonalEvent(TYPE_THUMB, total));
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mActivity, JokeDetailsActivity.class);
        intent.putExtra(Constants.KEY_JOKEBEAN, jokes.get(position));
        intent.putExtra(Constants.KEY_JOKEBEAN_POSITION, position);
        startActivity(intent);
    }
}
