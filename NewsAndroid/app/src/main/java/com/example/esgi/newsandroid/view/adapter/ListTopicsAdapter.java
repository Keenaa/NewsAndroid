package com.example.esgi.newsandroid.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.Topic;
import com.example.esgi.newsandroid.view.holder.ListTopicsViewHolder;

import java.util.List;

/**
 * Created by meryl on 22/06/2017.
 */

public class ListTopicsAdapter extends RecyclerView.Adapter<ListTopicsViewHolder> {

    List<Topic> topics;

    public ListTopicsAdapter(List<Topic> topics){
        this.topics = topics;
    }

    @Override
    public ListTopicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View cell;
        cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_topics, parent, false);
        return new ListTopicsViewHolder(cell);
    }

    @Override
    public void onBindViewHolder(ListTopicsViewHolder holder, int position){
        Topic topic = topics.get(position);
        holder.bind(topic);
    }

    @Override
    public int getItemCount(){
        return topics.size();
    }
}
