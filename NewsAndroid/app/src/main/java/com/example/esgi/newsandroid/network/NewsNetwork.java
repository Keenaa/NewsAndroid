package com.example.esgi.newsandroid.network;

import com.example.esgi.newsandroid.models.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


/**
 * Created by Vincent on 14/07/2017.
 */

public interface NewsNetwork {

    String ID_NEWS = "news";

    @GET("/news")
    Call<ArrayList<News>> getNews(@Header("Authorization") String auth);

    @GET("/news/{id}")
    Call<News> getNewsById(@Path(ID_NEWS) String idNews, @Header("Authorization") String auth);

    @DELETE("/news/{id}")
    Call<Void> deleteNews(@Path(ID_NEWS) String idNews, @Header("Authorization") String auth);

    @PUT("/news/{id}")
    Call<Void> updateNews(@Path(ID_NEWS) String idNews, @Header("Authorization") String auth);

    @POST("/news")
    Call<Void> createNews(@Header("Authorization") String auth, @Body News newNews);
}
