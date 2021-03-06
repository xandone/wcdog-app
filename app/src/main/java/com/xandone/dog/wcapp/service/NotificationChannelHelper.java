package com.xandone.dog.wcapp.service;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * author: xandone
 * created on: 2019/7/4 15:02
 * description:
 */
public class NotificationChannelHelper {
    public static final String CHANNEL_ID_FOR_DOWNLOAD = "download";
    public static final String CHANNEL_ID_FOR_UPDATE = "update";

    public static void initChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = CHANNEL_ID_FOR_DOWNLOAD;
            String channelName = "下载";
            int importance = NotificationManager.IMPORTANCE_LOW;
            createNotificationChannel(context, channelId, channelName, importance);
            channelId = CHANNEL_ID_FOR_UPDATE;
            channelName = "应用升级";
            importance = NotificationManager.IMPORTANCE_LOW;
            createNotificationChannel(context, channelId, channelName, importance);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private static void createNotificationChannel(Context context, String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                NOTIFICATION_SERVICE);
        if (notificationManager == null) {
            return;
        }
        notificationManager.createNotificationChannel(channel);
    }
}
