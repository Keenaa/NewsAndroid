package com.example.esgi.newsandroid.network;

import com.example.esgi.newsandroid.models.Post;
import com.example.esgi.newsandroid.models.Topic;

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
 * Created by meryl on 26/06/2017.
 */

public interface PostsNetwork {
    String ID_POST = "post";

    @GET("/posts")
    Call<ArrayList<Post>> getPosts(@Header("Authorization") String auth);

    @GET("/posts/{id}")
    Call<Topic> getPostById(@Path(ID_POST) String idPost, @Header("Authorization") String auth);

    @DELETE("/posts/{id}")
    Call<Void> deletePost(@Path(ID_POST) String idPost, @Header("Authorization") String auth);

    @PUT("/posts/{id}")
    Call<Void> updatePost(@Path(ID_POST) String idPost, @Header("Authorization") String auth);

    @POST("/posts")
    Call<Void> createPost(@Header("Authorization") String auth, @Body Post newPost);
}
