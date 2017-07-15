package com.example.esgi.newsandroid.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.Post;
import com.example.esgi.newsandroid.view.holder.ListPostsViewHolder;

import java.util.List;

/**
 * Created by meryl on 26/06/2017.
 */

public class ListPostsAdapter extends RecyclerView.Adapter<ListPostsViewHolder> {

    private List<Post> posts;

    public ListPostsAdapter(List<Post> posts){
        this.posts = posts;
    }

    @Override
    public ListPostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View cell;
        cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_posts, parent, false);
        return new ListPostsViewHolder(cell);
    }

    @Override
    public void onBindViewHolder(ListPostsViewHolder holder, int position){
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount(){
        return posts.size();
    }
}
