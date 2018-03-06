package com.eoma.pruebabp;

/**
 * Created by Erick on 10/02/2018.
 */

public class RespuestaPOJO {

    private Integer success;
    private String message;

    public RespuestaPOJO(Integer success, String message) {
        this.success = success;
        this.message = message;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
