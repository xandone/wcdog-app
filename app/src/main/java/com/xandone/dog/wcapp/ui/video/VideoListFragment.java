package com.xandone.dog.wcapp.ui.video;

import android.content.Intent;
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
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxFragment;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.model.video.VideoInfo;
import com.xandone.dog.wcapp.ui.joke.JokeContact;
import com.xandone.dog.wcapp.ui.videodetails.VideoDetailsActivity;
import com.xandone.dog.wcapp.uitils.SimpleUtils;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;
import com.xandone.dog.wcapp.widget.LoadingLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * author: xandone
 * created on: 2019/4/19 16:55
 */
public class VideoListFragment extends BaseRxFragment<VideoListPresenter> implements VideoContact.MyView {
    @BindView(R.id.video_list_recycler)
    RecyclerView video_list_recycler;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.loadingLayout)
    LoadingLayout loadingLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private BaseQuickAdapter mAdapter;
    private List<VideoInfo.ItemListBean> datas;
    private LoadingLayout.OnReloadListener onReloadListener;
    private RequestManager requestManager;

    public static final String KEY_VIDEOINFO = "key_videoinfo";

    @Override
    public int setLayout() {
        return R.layout.frag_video_layout;
    }

    @Override
    public void initData() {
        super.initData();
        init();
        loadVideoList();
    }

    private void init() {
        setToolBar(toolbar, "视频");

        datas = new ArrayList<>();
        requestManager = Glide.with(mActivity);
        mAdapter = new BaseQuickAdapter<VideoInfo.ItemListBean, BaseViewHolder>(R.layout.item_video_list_layout, datas) {
            @Override
            protected void convert(BaseViewHolder helper, VideoInfo.ItemListBean item) {
                helper.setText(R.id.item_video_title, item.getData().getTitle());
                helper.setText(R.id.item_video_time, SimpleUtils.int2STime(item.getData().getDuration()));

                ImageView imageView = helper.getView(R.id.item_video_img);
                requestManager.load(item.getData().getCover().getDetail()).into(imageView);

            }
        };
        video_list_recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        video_list_recycler.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, VideoDetailsActivity.class);
                intent.putExtra(KEY_VIDEOINFO, datas.get(position));
                startActivity(intent);
            }
        });

        loadingLayout.setLoadingTips(LoadingLayout.loading);

        onReloadListener = new LoadingLayout.OnReloadListener() {
            @Override
            public void reLoad() {
                loadVideoList();
                loadingLayout.setLoadingTips(LoadingLayout.loading);
            }
        };
        loadingLayout.setOnReloadListener(onReloadListener);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadVideoList();
            }
        });
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void lazyLoadData() {

    }

    public void loadVideoList() {
        Map<String, String> map = new HashMap<>();
        map.put("num", "10");
        map.put("udid", "26868b32e808498db32fd51fb422d00175e179df");
        map.put("vc", "83");
        mPresenter.getVideoList(map);
    }

    @Override
    public void showContent(VideoInfo videoInfo) {
        mRefreshLayout.finishRefresh();
        if (videoInfo == null
                || videoInfo.getItemList() == null
                || videoInfo.getItemList().isEmpty()) {
            showMsg("无数据", LoadingLayout.empty);
            return;
        }
        showMsg("加载完毕", LoadingLayout.finish);
        datas.clear();
        datas.addAll(videoInfo.getItemList());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        loadingLayout.setLoadingTips(loadStatus);
    }
}
