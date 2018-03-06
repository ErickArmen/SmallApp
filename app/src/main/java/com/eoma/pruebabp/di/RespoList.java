package com.eoma.pruebabp.di;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RespoList {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("apellido_paterno")
    @Expose
    private String apellidoPaterno;

    @SerializedName("apellido_materno")
    @Expose
    private String apellidoMaterno;

    @SerializedName("edad")
    @Expose
    private int edad;

    @SerializedName("hobbies")
    @Expose
    private List<String> hobbies;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
