package com.example.esgi.newsandroid.network;

import com.example.esgi.newsandroid.models.Comment;

import java.util.ArrayList;
import java.util.List;

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
import retrofit2.http.Url;

/**
 * Created by Vincent on 14/07/2017.
 */

public interface CommentsNetwork {

    String ID_COMMENT = "comment";

    @GET("/comments")
    Call<ArrayList<Comment>> getComments(@Header("Authorization") String auth);

    @GET("/comments/{id}")
    Call<Comment> getCommentById(@Path(ID_COMMENT) String idComment, @Header("Authorization") String auth);

    @DELETE("/comments/{id}")
    Call<Void> deleteComments(@Path(ID_COMMENT) String idComment, @Header("Authorization") String auth);

    @PUT("/comments/{id}")
    Call<Void> updateComment(@Path(ID_COMMENT) String idComment, @Header("Authorization") String auth);

    @POST("/comments")
    Call<Void> createComment(@Header("Authorization") String auth, @Body Comment newComment);

    @GET("/comments")
    @Headers("Content-Type: application/json")
    Call<ArrayList<Comment>> getCommentsForNews(@Header("Authorization") String token, @Query("criteria") String criteria);

}
