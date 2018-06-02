package com.ashwingkrish.chattest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class NotificationUtils extends ContextWrapper {

    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID";
    public static final String IOS_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.IOS";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    public static final String IOS_CHANNEL_NAME = "IOS CHANNEL";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationUtils(Context base) {
        super(base);
        createChannels();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels() {

        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(androidChannel);

        // create ios channel
        NotificationChannel iosChannel = new NotificationChannel(IOS_CHANNEL_ID,
                IOS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        iosChannel.enableLights(true);
        iosChannel.enableVibration(true);
        iosChannel.setLightColor(Color.GRAY);
        iosChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(iosChannel);
    }

    NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getAndroidChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true);
    }
}