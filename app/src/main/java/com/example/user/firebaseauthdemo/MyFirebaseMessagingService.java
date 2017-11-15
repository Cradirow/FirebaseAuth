package com.example.user.firebaseauthdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by HANSUNG on 2017-10-12.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {

        System.out.println("received message : ");
        Log.d("JM", "From : " + remoteMessage.getFrom());
        sendPushNotification(remoteMessage.getData().get("message"));
    }

    private void sendPushNotification(String message)
    {
        System.out.println("received message : " + message);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Admin")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setLights(000000255, 500, 2000)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
    //        // ...
    //
    //        // TODO(developer): Handle FCM messages here.
    //        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
    //        Log.d(TAG, "From: " + remoteMessage.getFrom());
    //
    //        // Check if message contains a data payload.
    //        if (remoteMessage.getData().size() > 0) {
    //            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
    //
    //            if (/* Check if data needs to be processed by long running job */ true) {
    //                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
    //                scheduleJob();
    //            } else {
    //                // Handle message within 10 seconds
    //                handleNow();
    //            }
    //
    //        }
    //
    //        // Check if message contains a notification payload.
    //        if (remoteMessage.getNotification() != null) {
    //            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
    //        }
    //
    //        // Also if you intend on generating your own notifications as a result of a received FCM
    //        // message, here is where that should be initiated. See sendNotification method below.

    //    private void handleNow()
    //    {
    //    }
    //
    //    private void scheduleJob()
    //    {
    //    }
}