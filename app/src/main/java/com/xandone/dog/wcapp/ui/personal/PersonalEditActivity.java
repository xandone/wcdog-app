package com.xandone.dog.wcapp.ui.personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xandone.dog.wcapp.MainActivity;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxActivity;
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.eventbus.SimpleEvent;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.uitils.SPUtils;
import com.xandone.dog.wcapp.uitils.SimpleUtils;
import com.xandone.dog.wcapp.uitils.ToastUtils;
import com.xandone.dog.wcapp.uitils.XString;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;


import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: xandone
 * created on: 2019/6/26 10:37
 * description:
 */
public class PersonalEditActivity extends BaseRxActivity<PersonalPresenter> implements PersonalContact.MyView {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_tv)
    TextView toolbarTv;
    @BindView(R.id.edit_head_iv)
    ImageView editHeadIv;
    @BindView(R.id.edit_nickname)
    TextView editNickname;
    @BindView(R.id.edit_talk)
    EditText editTalk;
    @BindView(R.id.edit_address)
    EditText editAddress;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_personal_edit;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolbar, "编辑");
        toolbarTv.setText("保存");
        toolbarTv.setVisibility(View.VISIBLE);

        editNickname.setText(UserInfoCache.getUserBean().getNickname());
        editTalk.setText(UserInfoCache.getUserBean().getTalk());
        editAddress.setText(UserInfoCache.getUserBean().getAddress());
        XGlide.loadImage(Glide.with(this), editHeadIv, UserInfoCache.getUserBean().getUserIcon(), R.mipmap.df_icon);
    }

    @Override
    public void showContent(BaseResponse response) {
        if (response.getCode() == 200) {
            ToastUtils.showShort("修改成功");
            EventBus.getDefault().post(new SimpleEvent(SimpleEvent.KEY_INIT_USER));
            finish();
        }
    }

    /**
     * 退出登录清除用户信息
     */
    public void logout() {
        EventBus.getDefault().post(new SimpleEvent(SimpleEvent.KEY_CLEAR_USER));

        SPUtils.getInstance(Constants.USER_INFO_NAME).remove(Constants.USER_INFO_KEY);
        UserInfoCache.setLogin(false);
        UserInfoCache.setUserBean(null);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.X_USER_RELOAD, MainActivity.USER_LOGOUT);
        startActivity(intent);
    }

    @OnClick({R.id.toolbar_tv, R.id.edit_exit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.toolbar_tv:
                String talk = editTalk.getText().toString().trim();
                String address = editAddress.getText().toString().trim();
                if (XString.isEmpty(talk)) {
                    ToastUtils.showShort("请输入签名");
                    return;
                }
                if (XString.isEmpty(address)) {
                    ToastUtils.showShort("请输入城市");
                    return;
                }
                SimpleUtils.hideSoftInput(this);
                mPresenter.updateUserInfo(talk, address);
                break;
            case R.id.edit_exit:
                showDialog("退出登录后好，会清空用户本地的个人信息数据。\n是否退出登录?", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logout();
                    }
                }, "取消", null);
                break;
            default:
                break;
        }
    }
}
