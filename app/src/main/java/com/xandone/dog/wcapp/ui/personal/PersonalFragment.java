package com.xandone.dog.wcapp.ui.personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.MainActivity;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxFragment;
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.ui.login.LoginActivity;
import com.xandone.dog.wcapp.uitils.SPUtils;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;
import com.xandone.dog.wcapp.widget.BottomDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: xandone
 * created on: 2018/3/6 13:35
 */

public class PersonalFragment extends BaseRxFragment<PersonalPresenter> implements PersonalContact.MyView {
    @BindView(R.id.frag_info_login_ll)
    LinearLayout frag_info_login_ll;
    @BindView(R.id.frag_info_icon_ll)
    LinearLayout frag_info_icon_ll;
    @BindView(R.id.frag_info_nick)
    TextView frag_info_nick;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.frag_info_icon_iv)
    ImageView frag_info_icon_iv;

    private BottomDialog bottomDialog;
    private RequestManager requestManager;

    @Override
    public int setLayout() {
        return R.layout.frag_personal_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, "个人中心");

        requestManager = Glide.with(App.sContext);
        if (UserInfoCache.isLogin()) {
            showUserInfo();
        }
    }

    @Override
    public void initInject() {
        getFragmentComponent().inject(this);
    }

    @OnClick({R.id.frag_info_login, R.id.frag_info_out, R.id.frag_info_icon_iv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.frag_info_login:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.frag_info_out:

                showDialog("退出登录后好，会清空用户本地的个人信息数据。\n是否退出登录?", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();

                    }
                }, "取消", null);
                break;
            case R.id.frag_info_icon_iv:
                break;
            default:
                break;

        }
    }

    /**
     * 显示个人信息
     */
    public void showUserInfo() {
        frag_info_login_ll.setVisibility(View.GONE);
        frag_info_icon_ll.setVisibility(View.VISIBLE);
        frag_info_nick.setText(UserInfoCache.getUserBean().getNickname());
        XGlide.loadImage(requestManager, frag_info_icon_iv, UserInfoCache.getUserBean().getUserIcon(), R.mipmap.df_icon);
    }

    /**
     * 退出登录清除用户信息
     */
    public void logout() {
        frag_info_login_ll.setVisibility(View.VISIBLE);
        frag_info_icon_ll.setVisibility(View.GONE);
        frag_info_nick.setText("");

        SPUtils.getInstance(Constants.USER_INFO_NAME).remove(Constants.USER_INFO_KEY);
        UserInfoCache.setLogin(false);
        UserInfoCache.setUserBean(null);

//        Intent intent = new Intent(mActivity, MainActivity.class);
//        intent.putExtra(MainActivity.X_USER_RELOAD, MainActivity.USER_LOGOUT);
//        startActivity(intent);
    }

    @Override
    public void showContent(BaseResponse<List<UserBean>> baseResponse) {
        XGlide.loadImage(requestManager, frag_info_icon_iv, baseResponse.getData().get(0).getUserIcon(), R.mipmap.df_icon);
    }
}
