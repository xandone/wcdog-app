package com.xandone.dog.wcapp.uitils.imgload;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * author: xandone
 * created on: 2018/3/7 10:38
 */

public interface ImageLoadInterface<T extends View> extends Serializable {
    void displayImage(Context context, Object path, T imageView);

    T createImage(Context context);
}
