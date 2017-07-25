package com.prateek.github.githubapp.application;

import com.prateek.github.githubapp.ui.home.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

@Singleton
@Component(modules = PAppModule.class)
public interface PAppComponent {
    void inject(HomePresenter presenter);
}