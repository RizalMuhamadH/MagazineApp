package com.lumiere.user.magazineapp.Firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by user on 04/19/2017.
 */

public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        FirebaseMessaging.getInstance().subscribeToTopic("global");

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("onTokenRefresh: ",refreshedToken);
        sendRegistrationToServer(refreshedToken);

        Intent intent = new Intent("registrationComplete");
        intent.putExtra("token",refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    private void sendRegistrationToServer(String token) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("firebase",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("regID", token);
        editor.commit();
    }
}
