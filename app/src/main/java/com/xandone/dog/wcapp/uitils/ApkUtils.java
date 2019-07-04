package com.xandone.dog.wcapp.uitils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * author: xandone
 * created on: 2019/7/4 09:44
 * description:
 */
public class ApkUtils {
    /**
     * 获取versionCode
     *
     * @param context cox
     * @return versionCode
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取versionName
     *
     * @param context cox
     * @return versionName
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
