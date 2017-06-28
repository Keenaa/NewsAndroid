package com.example.esgi.newsandroid.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.esgi.newsandroid.models.Login;
import com.example.esgi.newsandroid.models.SessionData;
import com.example.esgi.newsandroid.models.Topic;
import com.example.esgi.newsandroid.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meryl on 14/06/2017.
 */

public class ApiService {
    public static final int HTTP_200 = 200;
    public static final int HTTP_201 = 201;
    public static final int HTTP_204 = 204;

    public static final String API_URL = "https://esgi-2017.herokuapp.com";
    private static final ApiService INSTANCE = new ApiService();

    private static Context mContext;

    protected AuthenticationNetwork authenticationNetwork;
    protected TopicsNetwork topicsNetwork;

    private Retrofit retrofit;

    protected ApiService() {
        this.initRetrofitClient();
    }

    public static ApiService getInstance(Context context) {
        mContext = context;
        return INSTANCE;
    }

    private void initRetrofitClient() {
        // clean old retrofit client if exists
        if (this.retrofit != null) {
            this.retrofit = null;
        }
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson));

        this.retrofit = retrofitBuilder.build();

        this.authenticationNetwork = retrofit.create(AuthenticationNetwork.class);
        this.topicsNetwork = retrofit.create(TopicsNetwork.class);
    }

    // AUTH SERVICES
    public void authentication(Login login, final ApiResult<String> callback){
        Log.d("START AUTH", "OK");
        if(verifyConnection()){
            Log.d("CONNECTION", "OK");
            Call<String> call = this.authenticationNetwork.login(login);
            Log.d("URL", this.authenticationNetwork.login(login).request().url().toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("RESPONSE CODE", " " + response.code());
                    if(response.code() == HTTP_200){
                        callback.success(response.body());
                    } else {
                        try {
                            callback.error(response.code(),response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void createUser(User user, final ApiResult<String> callback){
        Log.d("START SIGNIN", "OK");
        if(verifyConnection()){
            Log.d("CONNECTION", "OK");
            Call<Void> call = this.authenticationNetwork.signIn(user);
            Log.d("URL", this.authenticationNetwork.signIn(user).request().url().toString());
            call.enqueue(new Callback<Void>() {

                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("RESPONSE CODE", " " + response.code());
                    if(response.code() == HTTP_201){
                        Log.d("SUCCESS", "OK");
                        callback.success("Good");
                    } else {
                        try {
                            callback.error(response.code(),response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    //TOPICS
    public void getTopics(final ApiResult<ArrayList<Topic>> callback){
        if(verifyConnection()){
            Call<ArrayList<Topic>> call = this.topicsNetwork.getTopics("Bearer " + SessionData.getINSTANCE().getToken());
            call.enqueue(new Callback<ArrayList<Topic>>() {
                @Override
                public void onResponse(Call<ArrayList<Topic>> call, Response<ArrayList<Topic>> response) {
                    int statusCode = response.code();
                    if(statusCode == HTTP_200){
                        ArrayList<Topic> topics = response.body();
                        callback.success(topics);
                    } else {
                        callback.error(statusCode, response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Topic>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void getTopicById(Topic topic, final ApiResult<Topic> callback){
        if (verifyConnection()){
            Call<Topic> call = this.topicsNetwork.getTopicById(topic.getId(), "Bearer "+ SessionData.getINSTANCE().getToken());
            call.enqueue(new Callback<Topic>() {
                @Override
                public void onResponse(Call<Topic> call, Response<Topic> response) {
                    int statusCode = response.code();
                    if (statusCode == HTTP_200){
                        Topic topic = response.body();
                        callback.success(topic);
                    } else {
                        callback.error(statusCode, response.message());
                    }
                }

                @Override
                public void onFailure(Call<Topic> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void deleteTopic(Topic topic, final ApiResult<String> callback){
       if(verifyConnection()){
           Call<Void> call = this.topicsNetwork.deleteTopic(topic.getId(), "Bearer "+ SessionData.getINSTANCE().getToken());
           call.enqueue(new Callback<Void>() {
               @Override
               public void onResponse(Call<Void> call, Response<Void> response) {
                   int statusCode = response.code();
                   if (statusCode == HTTP_204){
                       callback.success("DELETED");
                   } else {
                       callback.error(statusCode, response.message());
                   }
               }

               @Override
               public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
               }
           });
       }
    }

    public void updateTopic(Topic topic, final ApiResult<String> callback){
        if(verifyConnection()){
            Call<Void> call = this.topicsNetwork.deleteTopic(topic.getId(), "Bearer "+ SessionData.getINSTANCE().getToken());
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    int statusCode = response.code();
                    if (statusCode == HTTP_204){
                        callback.success("DELETED");
                    } else {
                        callback.error(statusCode, response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }






    public interface ApiResult<T> {
        void success(T res);

        void error(int code, String message);
    }

    public boolean verifyConnection() {
        if (mContext == null) {
            return false;
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
    }
    private void onConnectionError(final ApiResult callback) {
        String error = "network not avaiable";
        Log.d(getClass().getSimpleName(), error);
        callback.error(-1, error);
    }
}
