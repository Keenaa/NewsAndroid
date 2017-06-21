package com.example.esgi.newsandroid.network;

import com.example.esgi.newsandroid.models.Login;
import com.example.esgi.newsandroid.models.User;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationNetwork {
    String TAG_API_AUTH_TOKEN = "Authorization";

    //AUTHENTICATION
    @POST("/auth/login")
    Call<String> login(@Body Login bodyLogin);

    @POST("/auth/subscribe")
    Call<Void> signIn(@Body User bodySubscribe);

}
