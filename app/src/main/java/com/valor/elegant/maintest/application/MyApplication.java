package com.valor.elegant.maintest.application;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by elegant.wang on 2016/12/26.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
