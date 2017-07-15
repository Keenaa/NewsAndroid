package com.example.esgi.newsandroid.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.News;
import com.example.esgi.newsandroid.view.holder.ListNewsViewHolder;

import java.util.List;

/**
 * Created by Vincent on 14/07/2017.
 */

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder> {

    private List<News> newses;

    public ListNewsAdapter(List<News> news){
        this.newses = news;
    }

    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View cell;
        cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list_news, parent, false);
        return new ListNewsViewHolder(cell);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position){
        News news = newses.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount(){
        return newses.size();
    }
}
