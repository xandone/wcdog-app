package com.xandone.dog.wcapp.ui.personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.flyco.tablayout.SlidingTabLayout;
import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxFragment;
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.eventbus.SimpleEvent;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.ui.login.LoginActivity;
import com.xandone.dog.wcapp.uitils.SPUtils;
import com.xandone.dog.wcapp.uitils.XString;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: xandone
 * created on: 2018/3/6 13:35
 */

public class PersonalFragment extends BaseRxFragment<PersonalPresenter> implements PersonalContact.MyView {
    @BindView(R.id.info_login_ll)
    LinearLayout infoLoginLl;
    @BindView(R.id.info_icon_cl)
    ConstraintLayout infoIconCl;
    @BindView(R.id.info_nickname)
    TextView infoNickname;
    @BindView(R.id.info_head_iv)
    ImageView infoHeadIv;
    @BindView(R.id.comment_state)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.info_talk)
    TextView infoTalk;
    @BindView(R.id.info_address)
    TextView infoAddress;

    private RequestManager requestManager;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter mAdapter;

    private static final String[] mTitles = {"我的", "点赞"};

    @Override
    public int setLayout() {
        return R.layout.frag_personal_layout;
    }

    @Override
    public void initData() {
        super.initData();
        requestManager = Glide.with(App.sContext);
        if (UserInfoCache.isLogin()) {
            showUserInfo(true);
        } else {
            showUserInfo(false);
        }

    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @OnClick({R.id.frag_info_login, R.id.info_exit, R.id.info_head_iv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.frag_info_login:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.info_exit:
                showDialog("退出登录后好，会清空用户本地的个人信息数据。\n是否退出登录?", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                }, "取消", null);
                break;
            case R.id.info_head_iv:
                break;
            default:
                break;

        }
    }

    @Override
    public void onMessageReceived(SimpleEvent event) {
        super.onMessageReceived(event);
        if (event.getKey() == SimpleEvent.KEY_INIT_USER) {
            if (UserInfoCache.isLogin()) {
                showUserInfo(true);
            }
        }
    }

    /**
     * 显示个人信息
     */
    public void showUserInfo(boolean isShowInfo) {
        if (!isShowInfo) {
            infoLoginLl.setVisibility(View.VISIBLE);
            infoIconCl.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            infoNickname.setText("");
            return;
        }

        fragments = new ArrayList<>();
        fragments.add(new SelfJokeFragment());
        fragments.add(new SelfJokeFragment());
        mAdapter = new MyPagerAdapter(getFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);

        infoLoginLl.setVisibility(View.GONE);
        infoIconCl.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
        infoNickname.setText(UserInfoCache.getUserBean().getNickname());
        if (XString.isEmpty(UserInfoCache.getUserBean().getTalk())) {
            infoTalk.setVisibility(View.GONE);
        } else {
            infoTalk.setVisibility(View.VISIBLE);
            infoTalk.setText(UserInfoCache.getUserBean().getTalk());
        }
        if (XString.isEmpty(UserInfoCache.getUserBean().getAddress())) {
            infoAddress.setVisibility(View.GONE);
        } else {
            infoAddress.setVisibility(View.VISIBLE);
            infoAddress.setText(UserInfoCache.getUserBean().getAddress());
        }
        XGlide.loadImage(requestManager, infoHeadIv, UserInfoCache.getUserBean().getUserIcon(), R.mipmap.df_icon);
    }

    /**
     * 退出登录清除用户信息
     */
    public void logout() {
        showUserInfo(false);

        SPUtils.getInstance(Constants.USER_INFO_NAME).remove(Constants.USER_INFO_KEY);
        UserInfoCache.setLogin(false);
        UserInfoCache.setUserBean(null);

//        Intent intent = new Intent(mActivity, MainActivity.class);
//        intent.putExtra(MainActivity.X_USER_RELOAD, MainActivity.USER_LOGOUT);
//        startActivity(intent);
    }

    @Override
    public void showContent(BaseResponse<List<UserBean>> baseResponse) {
        XGlide.loadImage(requestManager, infoHeadIv, baseResponse.getData().get(0).getUserIcon(), R.mipmap.df_icon);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}
