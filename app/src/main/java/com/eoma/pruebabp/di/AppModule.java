package com.eoma.pruebabp.di;


import android.app.Application;

import com.eoma.pruebabp.R;
import com.eoma.pruebabp.repository.MySharedPref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton
    @Provides
    MySharedPref initSharedPreferences(Application application){
        return new MySharedPref(application);
    }

    @Singleton
    @Provides
    RetrofitInterface initRetrofit(Application application){

        return new Retrofit.Builder()
                .baseUrl(application.getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitInterface.class);
    }


}
