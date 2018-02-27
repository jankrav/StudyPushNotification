package com.jankrav.studypushnotification.service;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jankrav.studypushnotification.MainActivity;
import com.jankrav.studypushnotification.R;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String notificationTitle;
        String notificationMessage;
        RemoteMessage.Notification notification = remoteMessage.getNotification();

        if (notification != null) {
            notificationTitle = notification.getTitle();
            notificationMessage = notification.getBody();
            if (notificationTitle == null) {
                notificationTitle = "SendPushNotification";
            }

            Log.d("NOTIFICATION MESSAGE: ", notificationMessage);
            System.out.println("From: " + remoteMessage.getFrom() +
                    "\tTime Pause: (milisec) " + (System.currentTimeMillis() - remoteMessage.getSentTime()));
            sendMyNotification(notificationTitle, notificationMessage);
        } else {
            Log.d("Notification - null!", remoteMessage.toString());
        }
    }

    private void sendMyNotification(String title, String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent,
                        PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "BASE CHANNEL ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(0, notificationBuilder.build());
        } else {
            System.err.println("Notification manager is null!");
        }
    }
}
