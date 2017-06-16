package com.example.esgi.newsandroid.network;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by meryl on 14/06/2017.
 */

public interface ApiService {
    public static final String ENDPOINT = "https://esgi-2017.herokuapp.com/api/v1";

    //AUTHENTICATION
    @POST("/auth/login")
    Call<String> login(@Body JSONObject bodyLogin);

    @POST("/auth/subscribe")
    void subscribe(@Body JSONObject bodySubscribe, Callback<JSONObject> callback);

    //COMMENT
    //NEWS
    //POST
    //TOPIC
    //USERS

}
