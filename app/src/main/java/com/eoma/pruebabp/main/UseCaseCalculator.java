package com.eoma.pruebabp.main;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

class UseCaseCalculator {

    @Inject
    UseCaseCalculator() {}

    LiveData<Integer> goCompute(String string){
        MutableLiveData<Integer> mutableCompute = new MutableLiveData<>();
        mutableCompute.setValue(compute(string));
        return mutableCompute;
    }

    private int compute(String string){

        int sLenght = string.length()-1;
        int sum = 0;
        int num;
        for (int i = 0; i < sLenght; i++){
            num = Character.getNumericValue(string.charAt(i));
            sum += num;
        }
        return sum;
    }

}
