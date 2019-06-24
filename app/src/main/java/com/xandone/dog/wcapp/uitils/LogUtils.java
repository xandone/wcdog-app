package com.xandone.dog.wcapp.uitils;

import com.orhanobut.logger.Logger;
import com.xandone.dog.wcapp.BuildConfig;


/**
 * author: xandone
 * created on: 2017/11/29 14:12
 */

public class LogUtils {
    private static boolean isDeBug = BuildConfig.DEBUG;

    public static void d(String msg) {
        if (isDeBug) {
            Logger.d(msg);
        }
    }
}
