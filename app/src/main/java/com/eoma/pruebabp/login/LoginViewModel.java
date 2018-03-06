package com.eoma.pruebabp.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.eoma.pruebabp.repository.WebServiceRepository;

import javax.inject.Inject;


public class LoginViewModel extends ViewModel {

    private WebServiceRepository webServiceRepository;

    @Inject
    LoginViewModel(WebServiceRepository webServiceRepository) {
        this.webServiceRepository = webServiceRepository;
    }

    LiveData<Integer> tryLogin(String user, String pass){
        return webServiceRepository.tryLogin(user, pass);
    }
}
