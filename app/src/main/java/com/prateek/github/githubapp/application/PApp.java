package com.prateek.github.githubapp.application;

import android.app.Application;
import android.content.Context;

import com.prateek.github.githubapp.network.GithubService;

import javax.inject.Inject;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public class PApp extends Application {

    @Inject
    GithubService githubService;

    private PAppComponent pAppComponent;

    private static PApp appContext;

    public PApp() {
        appContext = this;

        pAppComponent = DaggerPAppComponent.builder().build();
        pAppComponent.inject(this);
    }

    public PAppComponent getAppComponent() {
        return pAppComponent;
    }

    /**
     * Should not use this outside of places where context is available.
     *
     * @return
     */
    public static PApp getApp() {
        return appContext;
    }

    public static PApp getApp(Context context) {
        return (PApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public GithubService githubService() {
        return githubService;
    }
}