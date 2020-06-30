package com.example.orderfood;


import android.app.Application;

import com.facebook.stetho.Stetho;

public class ste extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}