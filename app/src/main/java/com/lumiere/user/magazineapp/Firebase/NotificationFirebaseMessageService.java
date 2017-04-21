package com.lumiere.user.magazineapp.Firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lumiere.user.magazineapp.Activity.LoginActivity;
import com.lumiere.user.magazineapp.R;

/**
 * Created by user on 04/19/2017.
 */

public class NotificationFirebaseMessageService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    private Intent intent;
    private PendingIntent pendingIntent;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e(TAG,remoteMessage.getFrom());
//        Log.e(TAG,remoteMessage.getNotification().getBody());
        Log.e(TAG,remoteMessage.getData().toString());
        intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pendingIntent = PendingIntent.getActivity(this,1410,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("Message")
                .setContentText(remoteMessage.getData().toString())
                .setSmallIcon(R.drawable.ic_message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1410,builder.build());
    }
}
