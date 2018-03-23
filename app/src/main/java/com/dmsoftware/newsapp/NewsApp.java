package com.dmsoftware.newsapp;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

/**
 * Created by juanjesuscuetoyabar on 21/03/18.
 */

public class NewsApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
