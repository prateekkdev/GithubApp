package com.prateek.github.githubapp.ui.home;

import android.util.LruCache;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

@Module
public class HomeModule {

    private static final int COMMENTS_CACHE_SIZE = 10;

    private HomeActivity homeActivity;

    public HomeModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @HomeScope
    HomeAdapter adapter() {
        return new HomeAdapter(homeActivity);
    }

    @Provides
    @HomeScope
    IHomeContract.IHomePresenter presenter() {
        return new HomePresenter(homeActivity);
    }


}