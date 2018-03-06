package com.eoma.pruebabp;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class PruebaViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creadores;

    @Inject
    public PruebaViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creadores) {
        this.creadores = creadores;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> creador = creadores.get(modelClass);
        if (creador == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creadores.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creador = entry.getValue();
                    break;
                }
            }
        }
        if (creador == null) {
            throw new IllegalArgumentException("Clase VM desconocida " + modelClass);
        }
        try {
            return (T) creador.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}