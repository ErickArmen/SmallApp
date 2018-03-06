package com.eoma.pruebabp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;


public class MySharedPref {

    private static final String         sPrefs = "MisPreferencias";
    private SharedPreferences           preferences;
    private SharedPreferences.Editor    editor;

    public MySharedPref(Context mContext){
        preferences = mContext.getSharedPreferences(sPrefs, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public String getUserName(){
        return preferences.getString("nameUser", null);
    }

    public void saveUserName(@Nullable String userName){ editor.putString("nameUser", userName).commit(); }

    public void logOut(){ saveUserName(null); }

    public LiveData<String> checkLogin(){
        MutableLiveData<String> mutable = new MutableLiveData<>();
        mutable.setValue(getUserName());
        return mutable;
    }








}
