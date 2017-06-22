package com.example.esgi.newsandroid.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.esgi.newsandroid.models.Topic;

/**
 * Created by meryl on 22/06/2017.
 */

public class ListTopicsViewHolder extends RecyclerView.ViewHolder {


    private Topic currentTopic;

    public ListTopicsViewHolder(View itemView){
        super(itemView);
    }

    public void bind(Topic topic){
        currentTopic = topic;

    }
}
