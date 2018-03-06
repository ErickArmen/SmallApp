package com.eoma.pruebabp.di;


import com.eoma.pruebabp.login.Login;
import com.eoma.pruebabp.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector
    public abstract Login contributeLogin();

    @ContributesAndroidInjector
    public abstract MainActivity contributeMain();
}
