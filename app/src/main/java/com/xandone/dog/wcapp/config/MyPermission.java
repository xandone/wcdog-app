package com.xandone.dog.wcapp.config;

import android.Manifest;

/**
 * author: Admin
 * created on: 2019/7/5 11:51
 * description:
 */
public class MyPermission {

    public static final int RC_WRITE_AND_READ_PERM=100;

    public static final String[] WRITE_AND_READ = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
}
