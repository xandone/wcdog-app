package com.xandone.dog.wcapp.ui.splash;

import android.content.Intent;
import android.widget.ImageView;

import com.xandone.dog.wcapp.MainActivity;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxActivity;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.uitils.GsonUtil;
import com.xandone.dog.wcapp.uitils.SPUtils;
import com.xandone.dog.wcapp.uitils.imgload.GlideLoader;
import com.xandone.dog.wcapp.uitils.imgload.ImageLoadInterface;

import java.util.List;

import butterknife.BindView;

/**
 * author: xandone
 * created on: 2019/6/25 10:49
 * description:
 */
public class SplashAvtivity extends BaseRxActivity<SplashPresenter> implements SplashContact.View {
    @BindView(R.id.act_splash_bg)
    ImageView act_splash_bg;

    private ImageLoadInterface<ImageView> mLoader;

    @Override
    public int setLayout() {
        return R.layout.act_splash_layout;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
        String sp_user = spUtils.getString(Constants.USER_INFO_KEY);
        UserBean userBean = GsonUtil.deserialize(sp_user, UserBean.class);
        if (userBean != null) {
            String userName = userBean.getName();
            String userPsw = userBean.getPassword();
            mPresenter.getContent(userName, userPsw);
        } else {
            mPresenter.startAct();
        }

        if (mLoader == null) {
            mLoader = new GlideLoader();
        }
    }

    @Override
    public void showContent(BaseResponse<List<UserBean>> baseResponse) {


    }

    @Override
    public void jumpAct() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
