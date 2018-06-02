package com.ashwingkrish.chattest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String TAG = "MyFirebaseMessagingService";
    static int notificationId = 5;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload and check if the chat activity is not open
        if (remoteMessage.getData().size() > 0 && !ChatActivity.active) {
            Map<String, String> data = remoteMessage.getData();
            Log.d(TAG, "onMessageReceived: message arrived: "+data);
            NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Intent intent = new Intent(this, ChatActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            nManager.cancel(notificationId);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                NotificationUtils utils = new NotificationUtils(this);
                Notification.Builder nb = utils.getAndroidChannelNotification(data.get("title"),
                        data.get("body"));
                Notification n = nb.setContentIntent(pendingIntent).build();

                nManager.notify(notificationId, n);

            } else {
                Notification notification = new Notification.Builder(this)
                        .setContentTitle(data.get("title")).setSmallIcon(R.mipmap.ic_launcher_round)
                        .setWhen(System.currentTimeMillis()).setContentIntent(pendingIntent)
                        .setContentText(data.get("body")).build();

                nManager.notify(notificationId, notification);
            }

        }

    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
