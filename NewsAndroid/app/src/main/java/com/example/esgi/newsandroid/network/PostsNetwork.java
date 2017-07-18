package com.example.esgi.newsandroid.network;

import com.example.esgi.newsandroid.models.Post;
import com.example.esgi.newsandroid.models.Topic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by meryl on 26/06/2017.
 */

public interface PostsNetwork {

    @GET("/posts")
    @Headers("Content-Type: application/json")
    Call<ArrayList<Post>> getPostsForTopic(@Header("Authorization") String token, @Query("criteria") String criteria);
}
