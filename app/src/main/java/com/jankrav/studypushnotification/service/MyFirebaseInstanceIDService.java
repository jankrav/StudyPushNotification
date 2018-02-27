package com.jankrav.studypushnotification.service;

import android.util.Log;

import android.provider.Settings.Secure;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.jankrav.studypushnotification.utils.DeviceToken;


public class MyFirebaseInstanceIDService extends
        FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Registration of token
        String refreshedToken =
                FirebaseInstanceId.getInstance().getToken();

        //display token on logcat
        Log.d("TOKEN: ", refreshedToken);
        System.out.println("TOKEN: " + refreshedToken);
        saveTokenToDatabase(refreshedToken);
    }

    private void saveTokenToDatabase(String refreshedToken) {
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mTokenDatabaseReference = mFirebaseDatabase.getReference().child("tokens");
        String androidId = Secure.getString(getContentResolver(), Secure.ANDROID_ID);

        DeviceToken token = new DeviceToken(androidId, refreshedToken);

        mTokenDatabaseReference.push().setValue(token);
    }

}


