package com.framgia.capstone.utils;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ASUS on 15/05/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    private String refreshedToken = "";
    @Override
    public void onTokenRefresh() {
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        final Intent intent = new Intent("tokenReceiver");
        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        intent.putExtra("token",refreshedToken);
        broadcastManager.sendBroadcast(intent);
    }

    public String token(){
        return FirebaseInstanceId.getInstance().getToken();
    }
}
