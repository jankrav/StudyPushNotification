package com.jankrav.studypushnotification.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIDService extends
        FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Regit gistration of token
        String refreshedToken =
                FirebaseInstanceId.getInstance().getToken();

        //display token on logcat
        Log.d("TOKEN: ", refreshedToken);

    }
}
