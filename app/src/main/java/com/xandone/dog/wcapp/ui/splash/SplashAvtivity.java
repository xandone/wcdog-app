package com.xandone.dog.wcapp.ui.splash;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.xandone.dog.wcapp.AppManager;
import com.xandone.dog.wcapp.MainActivity;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxActivity;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.config.MyPermission;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.UserBean;
import com.xandone.dog.wcapp.uitils.GsonUtil;
import com.xandone.dog.wcapp.uitils.SPUtils;
import com.xandone.dog.wcapp.uitils.imgload.GlideLoader;
import com.xandone.dog.wcapp.uitils.imgload.ImageLoadInterface;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: xandone
 * created on: 2019/6/25 10:49
 * description:
 */
public class SplashAvtivity extends BaseRxActivity<SplashPresenter> implements SplashContact.View, EasyPermissions.PermissionCallbacks {
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
        writeAndReadTask();
    }

    @Override
    public void showContent(BaseResponse<List<UserBean>> baseResponse) {

    }

    private void init() {
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
    public void jumpAct() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean hasWriteAndReadPermissions() {
        return EasyPermissions.hasPermissions(this, MyPermission.WRITE_AND_READ);
    }

    @AfterPermissionGranted(MyPermission.RC_WRITE_AND_READ_PERM)
    public void writeAndReadTask() {
        if (hasWriteAndReadPermissions()) {
            // Have permissions, do the thing!
            init();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    "需要以下权限",
                    MyPermission.RC_WRITE_AND_READ_PERM,
                    MyPermission.WRITE_AND_READ);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        init();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
        AppManager.newInstance().finishAllActivity();
    }
}
