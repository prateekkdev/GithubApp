package com.prateek.github.githubapp.ui.home;

import com.prateek.github.githubapp.application.PApp;
import com.prateek.github.githubapp.network.GithubService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

@Module
public class HomeModule {

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
    HomePresenter presenter() {
        return new HomePresenter(homeActivity);
    }

    @Provides
    GithubService service() {
        // This seems like an hack, should be some other way.
        return PApp.getApp().githubService();
    }
}