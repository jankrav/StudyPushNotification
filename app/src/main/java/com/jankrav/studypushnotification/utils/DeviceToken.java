package com.jankrav.studypushnotification.utils;


import java.util.Calendar;

public class DeviceToken {
    private String id;
    private String timeOfCreationInMillis;
    private String currentToken;

    public DeviceToken(String androidId, String refreshToken){
        id = androidId;
        currentToken = refreshToken;
        timeOfCreationInMillis = String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    public String getId() {
        return id;
    }

    public String getTimeOfCreationInMillis() {
        return timeOfCreationInMillis;
    }

    public String getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(String currentToken) {
        this.currentToken = currentToken;
    }

}
