package com.prateek.github.githubapp.application;

import android.app.Application;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public class PApp extends Application {

    private static PAppComponent pAppComponent;

    public static PAppComponent getAppComponent() {
        return pAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        pAppComponent = buildComponent();
    }

    private PAppComponent buildComponent() {
        return DaggerPAppComponent.builder().build();
    }
}