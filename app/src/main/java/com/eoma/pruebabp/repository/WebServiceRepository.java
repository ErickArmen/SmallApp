package com.eoma.pruebabp.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.eoma.pruebabp.RespuestaPOJO;
import com.eoma.pruebabp.di.Respo;
import com.eoma.pruebabp.di.RespoList;
import com.eoma.pruebabp.di.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceRepository {

    private RetrofitInterface retrofitInterface;
    private MySharedPref mySharedPref;

    @Inject
    public WebServiceRepository(RetrofitInterface retrofitInterface, MySharedPref mySharedPref) {
        this.retrofitInterface = retrofitInterface;
        this.mySharedPref = mySharedPref;
    }

    public LiveData<Integer> tryLogin(final String user, String pass){

        final MutableLiveData<Integer> mutable = new MutableLiveData<>();

        retrofitInterface.tryLogin(user, pass).enqueue(new Callback<Respo>() {

            @Override
            public void onResponse(Call<Respo> call, Response<Respo> response) {

                Respo respo = response.body();
                if (respo != null) {
                    mutable.setValue(respo.getSuccess());

                    if(respo.getSuccess() == 0) mySharedPref.saveUserName(user);
                }


            }

            @Override
            public void onFailure(Call<Respo> call, Throwable t) {
                mutable.setValue(4);
            }
        });
        return mutable;
    }

    public LiveData<List<String>> giveMeUser(){

        final MutableLiveData<List<String>> mutable = new MutableLiveData<>();

        retrofitInterface.getUser().enqueue(new Callback<RespoList>() {

            @Override
            public void onResponse(Call<RespoList> call, Response<RespoList> response) {
                RespoList respoList = response.body();
                List<String> list = new ArrayList<>();

                if (respoList != null){
                    String name = respoList.getName();
                    String apellido1 = respoList.getApellidoPaterno();
                    String apellido2 = respoList.getApellidoMaterno();
                    int edad = respoList.getEdad();

                    list.add(name);
                    list.add(apellido1);
                    list.add(apellido2);
                    list.add(String.valueOf(edad));
                    list.addAll(respoList.getHobbies());

                    mutable.setValue(list);
                }
            }

            @Override
            public void onFailure(Call<RespoList> call, Throwable t) {
                mutable.setValue(null);
            }
        });

        return mutable;
    }

    public LiveData<RespuestaPOJO> sendEmail(String receiver){

        final MutableLiveData<RespuestaPOJO> mutable = new MutableLiveData<>();

        retrofitInterface.sendEmail(receiver).enqueue(new Callback<Respo>() {

            @Override
            public void onResponse(Call<Respo> call, Response<Respo> response) {

                Respo respo = response.body();
                if (respo != null){
                    mutable.setValue(new RespuestaPOJO(respo.getSuccess(), respo.getMessage()));
                }
            }

            @Override
            public void onFailure(Call<Respo> call, Throwable t) {
                mutable.setValue(new RespuestaPOJO(2, t.getMessage()));
            }
        });
        return mutable;
    }

}
