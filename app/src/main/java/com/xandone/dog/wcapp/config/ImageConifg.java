package com.xandone.dog.wcapp.config;


import android.content.Context;

import com.xandone.dog.wcapp.uitils.SimpleUtils;

/**
 * author: xandone
 * created on: 2019/3/13 22:16
 */
public class ImageConifg {
    public static int TYPE_SMALL_IMAGE;
    public static int TYPE_MIDDLE_IMAGE;
    public static int TYPE_LARGER_IMAGE;


    public static final int TYPE_SMALL = 1;
    public static final int TYPE_MIDDLE = 2;
    public static final int TYPE_LARGER = 3;


    public static void init(Context context) {
        TYPE_SMALL_IMAGE = SimpleUtils.dp2px(context, 160);
        TYPE_MIDDLE_IMAGE = SimpleUtils.dp2px(context, 180);
        TYPE_LARGER_IMAGE = SimpleUtils.dp2px(context, 200);
    }

    public static int setImageHeight(int type) {
        int height = TYPE_MIDDLE;
        switch (type) {
            case TYPE_SMALL:
                height = TYPE_SMALL_IMAGE;
                break;
            case TYPE_MIDDLE:
                height = TYPE_MIDDLE_IMAGE;
                break;
            case TYPE_LARGER:
                height = TYPE_LARGER_IMAGE;
                break;
        }
        return height;
    }
}
