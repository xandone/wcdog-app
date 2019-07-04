package com.xandone.dog.wcapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.orhanobut.logger.Logger;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.model.bean.ApkBean;

import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * author: xandone
 * created on: 2019/7/4 09:48
 * description:
 */
public class LoadApkService extends Service {

    private ApkBean mApkBean;

    private static final String KEY_ACTION = "key_action";
    private static final int ACTION_PAUSE = 1;
    private static final int ACTION_GO_ON = 2;
    private static final int ACTION_CANCEL = 3;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("执行....");
        mApkBean = (ApkBean) intent.getSerializableExtra("mApkBean");
        startNotification(ACTION_PAUSE, 18, "6/40Mb", 122);
        downLoadApk(mApkBean.getApkUrl(), Constants.PATH_SDCARD, "wcdog" + mApkBean.getApkVersion() + ".apk");
        return START_NOT_STICKY;
    }

    private void downLoadApk(String url, String path, String filename) {
        File appDir = new File(path);
        if (!appDir.exists()) {
            if (!appDir.mkdirs()) {
                return;
            }
        }
        File currentFile = new File(path, filename);
        DownloadTask task = new DownloadTask.Builder(url, currentFile)
                .setFilename(filename)
                // the minimal interval millisecond for callback progress
                .setMinIntervalMillisCallbackProcess(15)
                // do re-download even if the task has already been completed in the past.
                .setPassIfAlreadyCompleted(false)
                .build();
        task.enqueue(new DownloadListener() {
            @Override
            public void taskStart(@NonNull DownloadTask task) {

            }

            @Override
            public void connectTrialStart(@NonNull DownloadTask task, @NonNull Map<String, List<String>> requestHeaderFields) {

            }

            @Override
            public void connectTrialEnd(@NonNull DownloadTask task, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {

            }

            @Override
            public void downloadFromBeginning(@NonNull DownloadTask task, @NonNull BreakpointInfo info, @NonNull ResumeFailedCause cause) {

            }

            @Override
            public void downloadFromBreakpoint(@NonNull DownloadTask task, @NonNull BreakpointInfo info) {

            }

            @Override
            public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {

            }

            @Override
            public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {

            }

            @Override
            public void fetchStart(@NonNull DownloadTask task, int blockIndex, long contentLength) {

            }

            @Override
            public void fetchProgress(@NonNull DownloadTask task, int blockIndex, long increaseBytes) {

            }

            @Override
            public void fetchEnd(@NonNull DownloadTask task, int blockIndex, long contentLength) {

            }

            @Override
            public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {

            }
        });
    }

    private void startNotification(int action, int progress, String fileSize, int speed) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationChannelHelper.CHANNEL_ID_FOR_UPDATE);
        builder.setContentTitle("正在下载");
        //只响铃震动一次
        builder.setOnlyAlertOnce(true);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notify_download_layout);

        remoteViews.setTextViewText(R.id.notify_size, fileSize);
        if (action == ACTION_GO_ON) {
            remoteViews.setTextViewText(R.id.notify_pause, "继续");
        } else {
            remoteViews.setTextViewText(R.id.notify_pause, "暂停");
        }
        remoteViews.setTextViewText(R.id.notify_speed, speed + "KB/s");
        remoteViews.setTextViewText(R.id.notify_progress, progress + "%");

        remoteViews.setProgressBar(R.id.notify_pb, 100, progress, false);

        Intent pauseStartIntent = new Intent(this, LoadApkService.class);
        pauseStartIntent.putExtra(KEY_ACTION, action);
        PendingIntent pauseStartPendingIntent = PendingIntent.getService(this, action, pauseStartIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notify_pause, pauseStartPendingIntent);

        Intent cancelIntent = new Intent(this, LoadApkService.class);
        cancelIntent.putExtra(KEY_ACTION, ACTION_CANCEL);
        PendingIntent cancelPendingIntent = PendingIntent.getService(this, ACTION_CANCEL, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notify_cancel, cancelPendingIntent);

        builder.setContent(remoteViews);
        Notification notification = builder.build();
        int id = Constants.APK_DOWNLOAD_NOTIFICATION_ID;
        startForeground(id, notification);
    }
}
