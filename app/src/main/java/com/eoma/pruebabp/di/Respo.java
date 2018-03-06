package com.eoma.pruebabp.di;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Respo {

    @SerializedName("success")
    @Expose
    private int success;

    @SerializedName("message")
    @Expose
    private String message;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

}
