package com.prateek.github.githubapp.application;

import dagger.Component;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

@Component(modules = PAppModule.class)
@PAppScope
public interface PAppComponent {
    void inject(PApp app);
}