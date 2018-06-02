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
            //TODO: Handle the FCM sent by notifying the user
        }

    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
