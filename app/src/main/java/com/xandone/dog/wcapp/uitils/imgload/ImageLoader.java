package com.xandone.dog.wcapp.uitils.imgload;

import android.content.Context;
import android.widget.ImageView;

/**
 * author: xandone
 * created on: 2018/3/7 10:40
 */

public abstract class ImageLoader implements ImageLoadInterface<ImageView> {
    @Override
    public ImageView createImage(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
