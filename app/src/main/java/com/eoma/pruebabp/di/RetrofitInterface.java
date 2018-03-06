package com.eoma.pruebabp.di;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/pruebaBP/Login.php") @FormUrlEncoded
    Call<Respo> tryLogin(@Field("user") String user, @Field("pass") String pass);

    @GET("/pruebaBP/User.php")
    Call<RespoList> getUser();

    @POST("/pruebaBP/sendMyEmail.php") @FormUrlEncoded
    Call<Respo> sendEmail(@Field("receiver") String receiver);
}
