package com.eoma.pruebabp.main;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.eoma.pruebabp.RespuestaPOJO;
import com.eoma.pruebabp.repository.MySharedPref;
import com.eoma.pruebabp.repository.WebServiceRepository;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private MySharedPref mySharedPref;
    private WebServiceRepository webServiceRepository;
    private UseCaseCalculator useCaseCalculator;

    @Inject
    MainViewModel(WebServiceRepository webServiceRepository, MySharedPref mySharedPref,
                  UseCaseCalculator useCaseCalculator){
        this.webServiceRepository = webServiceRepository;
        this.mySharedPref = mySharedPref;
        this.useCaseCalculator = useCaseCalculator;
    }

    LiveData<String> checkLogin() {
        return mySharedPref.checkLogin();
    }

    LiveData<List<String>> giveMeUser(){
        return webServiceRepository.giveMeUser();
    }

    LiveData<RespuestaPOJO> sendEmail(String receiver){ return webServiceRepository.sendEmail(receiver); }

    LiveData<Integer> goCompute(String string){ return useCaseCalculator.goCompute(string); }

    void logOut(){ mySharedPref.logOut(); }


}
