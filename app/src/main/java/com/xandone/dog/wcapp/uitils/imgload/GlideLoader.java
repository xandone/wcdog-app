package com.xandone.dog.wcapp.uitils.imgload;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xandone.dog.wcapp.R;


/**
 * author: xandone
 * created on: 2018/3/7 10:44
 */

public class GlideLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .placeholder(R.mipmap.df_icon)
                .into(imageView);
    }
}
