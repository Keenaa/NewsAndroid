package com.example.esgi.newsandroid.network;


import com.example.esgi.newsandroid.manager.UserManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meryl on 14/06/2017.
 */

public class AuthenticationNetwork {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://esgi-2017.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService service = retrofit.create(ApiService.class);

    public void login(UserManager user, String password){
        Map<String, String > map = new HashMap<String, String>();
        map.put("email", user.getEmail());
        map.put("password", password);

        JSONObject loginCredentials = new JSONObject(map);
        Call<String> login = service.login(loginCredentials);
        System.out.print(login);

    }

    public void signIn(String email, String password, String firstname, String lastname){

    }
}