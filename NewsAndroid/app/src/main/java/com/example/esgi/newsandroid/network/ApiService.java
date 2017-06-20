package com.example.esgi.newsandroid.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.esgi.newsandroid.models.Login;
import com.example.esgi.newsandroid.models.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by meryl on 14/06/2017.
 */

public class ApiService {
    public static final int HTTP_200 = 200;
    public static final int HTTP_204 = 204;
    public static final int HTTP_400 = 400;
    public static final int HTTP_401 = 401;
    public static final int HTTP_404 = 404;

    public static final String API_URL = "https://esgi-2017.herokuapp.com";
    private static final String TAG_STATUS = " status: ";
    private static final ApiService INSTANCE = new ApiService();
    private static Context mContext;
    protected AuthenticationNetwork authenticationNetwork;
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
    }

    // USERS SERVICES
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
