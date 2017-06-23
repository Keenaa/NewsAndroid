package com.example.esgi.newsandroid.network;

import com.example.esgi.newsandroid.models.Topic;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by meryl on 22/06/2017.
 */

public interface TopicsNetwork {

    public static final String ID_TOPIC = "topic";

    @GET("/topics")
    Call<ArrayList<Topic>> getTopics(@Header("Authorization") String auth);

    @GET("/topics/{id")
    Call<Topic> getTopicById(@Path(ID_TOPIC) String idTopic);

    @DELETE("/topics/{id}")
    Call<Void> deleteTopic(@Path(ID_TOPIC) String idTopic);

    @PUT("/topics/{id}")
    Call<Void> updateTopic(@Path(ID_TOPIC) String idTopic);
}
