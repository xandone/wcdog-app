package com.xandone.dog.wcapp.uitils;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.R;


/**
 * author: xandone
 * created on: 2017/11/27 9:49
 */

public class ToastUtils {

    private static Toast toast_1;
    private static Toast toast_2;

    private static Toast createToast(CharSequence msg, int duration) {
        if (toast_1 == null) {
            toast_1 = Toast.makeText(App.sContext, msg, duration);
        } else {
            toast_1.setText(msg);
            toast_1.setDuration(duration);
        }
        return toast_1;
    }

    public static void showShort(CharSequence msg) {
        createToast(msg, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(int strId) {
        createToast(App.sContext.getText(strId), Toast.LENGTH_SHORT).show();
    }

    public static void showLong(CharSequence msg) {
        createToast(msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int strId) {
        createToast(App.sContext.getText(strId), Toast.LENGTH_SHORT).show();
    }

    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
        if (toast_2 == null) {
            toast_2 = new Toast(App.sContext);
        }
        View view = LayoutInflater.from(App.sContext).inflate(R.layout.toast_custom, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        toast_2.setView(view);
        toast_2.setGravity(Gravity.CENTER, 0, 0);
        toast_2.show();
        return toast_2;

    }
}