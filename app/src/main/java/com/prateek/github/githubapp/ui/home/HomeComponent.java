package com.prateek.github.githubapp.ui.home;

import dagger.Component;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

@Component(modules = {HomeModule.class})
@HomeScope
public interface HomeComponent {
    void inject(HomeActivity homeActivity);

    // void inject(HomeAdapter homeAdapter);
}