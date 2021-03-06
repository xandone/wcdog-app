package com.xandone.dog.wcapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.xandone.dog.wcapp.base.BaseRxActivity;
import com.xandone.dog.wcapp.config.MyPermission;
import com.xandone.dog.wcapp.interfs.MaterialDialogOnclickListener;
import com.xandone.dog.wcapp.model.bean.ApkBean;
import com.xandone.dog.wcapp.service.LoadApkService;
import com.xandone.dog.wcapp.service.NotificationChannelHelper;
import com.xandone.dog.wcapp.ui.MainContact;
import com.xandone.dog.wcapp.ui.MainPresenter;
import com.xandone.dog.wcapp.ui.joke.JokeFragment;
import com.xandone.dog.wcapp.ui.personal.PersonalFragment;
import com.xandone.dog.wcapp.ui.video.VideoListFragment;
import com.xandone.dog.wcapp.uitils.ApkUtils;
import com.xandone.dog.wcapp.uitils.MaterialDialogUtils;
import com.xandone.dog.wcapp.uitils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: xandone
 * created on: 2019/3/5 16:30
 */
public class MainActivity extends BaseRxActivity<MainPresenter> implements MainContact.MyView {
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    private int mFragIndex;
    private Fragment mCurrentFrag;
    private List<Fragment> fragList;
    private ApkBean mApkBean;

    private boolean isState = true;

    public static final String X_USER_RELOAD = MainActivity.class.getName() + "_USER_RELOAD";
    public static final int USER_LOGIN = 1;
    public static final int USER_REGIST = 2;
    public static final int USER_LOGOUT = 3;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void initData() {
        super.initData();
        mFragIndex = 0;
        fragList = new ArrayList<Fragment>(Arrays.asList(new JokeFragment(), new VideoListFragment(), new PersonalFragment()));

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.main_footer_care_rb:
                        mFragIndex = 0;
                        break;
                    case R.id.main_footer_video_rb:
                        mFragIndex = 1;
                        break;
                    case R.id.main_footer_my_rb:
                        mFragIndex = 2;
                        break;
                    default:
                        break;
                }
                turnToFrag();
            }
        });

        NotificationChannelHelper.initChannel(this);
        mPresenter.getLastVersion();
    }

    public void turnToFrag() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment toFragment = fragList.get(mFragIndex);
        if (mCurrentFrag != null) {
            ft.hide(mCurrentFrag);
        }
        if (toFragment.isAdded()) {
            ft.show(toFragment);
        } else {
            ft.add(R.id.main_frame, toFragment);
        }
        ft.commitAllowingStateLoss();
        mCurrentFrag = toFragment;
    }

    @Override
    public void showResult(final ApkBean apkBean) {
        if (ApkUtils.getVersionCode(this) < apkBean.getApkCode()) {
            this.mApkBean = apkBean;
            MaterialDialogUtils.showSimpleDialog(this, apkBean.getContent(), "取消", "下载更新",
                    new MaterialDialogOnclickListener() {
                        @Override
                        public void onConfirm() {
                            Intent intent = new Intent(MainActivity.this, LoadApkService.class);
                            intent.putExtra("mApkBean", mApkBean);
                            startService(intent);
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        if (isState) {
            isState = false;
            ToastUtils.showShort("再按一次退出");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isState = true;
                }
            }, 2000);
        } else {
            AppManager.newInstance().finishAllActivity();
        }
    }
}
