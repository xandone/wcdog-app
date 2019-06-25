package com.xandone.dog.wcapp.ui.regist;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;

import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.MainActivity;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxActivity;
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.eventbus.SimpleEvent;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.uitils.AnimUtils;
import com.xandone.dog.wcapp.uitils.SimpleUtils;
import com.xandone.dog.wcapp.uitils.ToastUtils;
import com.xandone.dog.wcapp.uitils.XString;
import com.xandone.dog.wcapp.widget.DrawableTextView;
import com.xandone.dog.wcapp.widget.KeyboardWatcher;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: xandone
 * created on: 2019/3/6 20:16
 */

public class RegistActivity extends BaseRxActivity<RegistPresenter> implements RegistContact.View, KeyboardWatcher.SoftKeyboardStateListener {
    @BindView(R.id.act_regist_et_name)
    EditText act_regist_et_name;
    @BindView(R.id.act_regist_et_psw)
    EditText act_regist_et_psw;
    @BindView(R.id.act_regist_et_nick)
    EditText act_regist_et_nick;
    @BindView(R.id.body)
    ConstraintLayout body;
    @BindView(R.id.act_regist_title)
    DrawableTextView act_regist_title;


    private KeyboardWatcher keyboardWatcher;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_regist_layout;
    }

    @Override
    public void initData() {
        super.initData();

        keyboardWatcher = new KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
    }

    @OnClick({R.id.act_regist_btn, R.id.close})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.act_regist_btn:
                String email = act_regist_et_name.getText().toString().trim();
                String psw = act_regist_et_psw.getText().toString().trim();
                String nick = act_regist_et_nick.getText().toString().trim();
                if (XString.isEmpty(email)) {
                    ToastUtils.showShort("请输入用户名");
                    return;
                }
                if (XString.isEmpty(psw) || !XString.isPassword(psw)) {
                    ToastUtils.showShort("请输入6-16位数字/英文密码");
                    return;
                }
                if (XString.isEmpty(nick)) {
                    ToastUtils.showShort("请输入昵称");
                    return;
                }
                mPresenter.regist(email, psw, nick);
                break;
            case R.id.close:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showContent(BaseResponse<List<UserBean>> baseResponse) {
        if (UserInfoCache.isLogin()) {
            SimpleUtils.hideSoftInput(this);
            Intent intent = new Intent(RegistActivity.this, MainActivity.class);
            intent.putExtra(MainActivity.X_USER_RELOAD, MainActivity.USER_REGIST);
            startActivity(intent);
            EventBus.getDefault().post(new SimpleEvent(SimpleEvent.KEY_INIT_USER));
        } else if (!TextUtils.isEmpty(baseResponse.getMsg())) {
            ToastUtils.showShort(baseResponse.getMsg());
        } else {
            ToastUtils.showShort("服务器异常,请稍后再试");
        }

    }

    @Override
    public void onSoftKeyboardOpened(int keyboardSize) {
        int[] location = new int[2];
        body.getLocationOnScreen(location); //获取body在屏幕中的坐标,控件左上角
        int x = location[0];
        int y = location[1];
        int bottom = App.SCREEN_HEIGHT - (y + body.getHeight());
        if (keyboardSize > bottom) {
            ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", 0.0f, -(keyboardSize - bottom));
            mAnimatorTranslateY.setDuration(300);
            mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimatorTranslateY.start();
            AnimUtils.zoomIn(act_regist_title, keyboardSize - bottom, 0.8f);

        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", body.getTranslationY(), 0);
        mAnimatorTranslateY.setDuration(300);
        mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimatorTranslateY.start();
        AnimUtils.zoomOut(act_regist_title, 0.8f);
    }
}
