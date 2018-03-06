package com.eoma.pruebabp.di;

import com.eoma.pruebabp.AppPruebaBP;


public class AppInjector {

    public static void init(AppPruebaBP app){
        DaggerAppComponent.builder().application(app).build().inject(app);
    }
}
