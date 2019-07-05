package com.xandone.dog.wcapp.uitils.imgload;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;


/**
 * author: xandone
 * created on: 2018/3/14 15:24
 */
public class XGlide {
    private XGlide() {
    }

    public static void loadImage(RequestManager loader, ImageView view, String url) {
        loadImage(loader, view, url, 0);
    }

    public static void loadImage(RequestManager loader, ImageView view, String url, int placeholder) {
        loadImage(loader, view, url, placeholder, placeholder);
    }

    public static void loadImage(RequestManager loader, ImageView view, String url, int placeholder, int error) {
        loader.load(url).placeholder(placeholder).error(error).into(view);
    }

}

