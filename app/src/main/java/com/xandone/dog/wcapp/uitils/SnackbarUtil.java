package com.xandone.dog.wcapp.uitils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * author: xandone
 * created on: 2018/3/6 10:56
 */

public class SnackbarUtil {

    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}