package com.example.esgi.newsandroid.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.Comment;
import com.example.esgi.newsandroid.view.holder.ListCommentsViewHolder;

import java.util.List;

/**
 * Created by Vincent on 14/07/2017.
 */

public class ListCommentsAdapter extends RecyclerView.Adapter<ListCommentsViewHolder> {

    private List<Comment> comments;

    public ListCommentsAdapter(List<Comment> comments){
        this.comments = comments;
    }

    @Override
    public ListCommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View cell;
        cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_comments, parent, false);
        return new ListCommentsViewHolder(cell);
    }

    @Override
    public void onBindViewHolder(ListCommentsViewHolder holder, int position){
        Comment comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount(){
        return comments.size();
    }

}
