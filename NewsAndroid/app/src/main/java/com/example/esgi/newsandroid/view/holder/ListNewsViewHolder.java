package com.example.esgi.newsandroid.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.activity.MainActivity;
import com.example.esgi.newsandroid.models.News;
import com.example.esgi.newsandroid.models.SessionData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent on 14/07/2017.
 */

public class ListNewsViewHolder extends RecyclerView.ViewHolder {

    private TextView newsCellTitle;
    private TextView newsCellDate;
    private TextView newsCellContent;

    private News currentNews;

    public ListNewsViewHolder(View itemView){
        super(itemView);
        newsCellTitle = (TextView) itemView.findViewById(R.id.cell_news_title);
        newsCellDate = (TextView) itemView.findViewById(R.id.cell_news_date);
        newsCellContent = (TextView) itemView.findViewById(R.id.cell_news_content);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionData.getINSTANCE().setCurrentNews(currentNews);
                if(v.getContext() instanceof MainActivity) {
                    ((MainActivity)v.getContext()).displayNewsDetails();
                }

            }
        });
    }

    public void bind(News news){
        currentNews = news;
        SimpleDateFormat finalDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (news.getTitle() != null){
            newsCellTitle.setText(news.getTitle());
        }
        if(news.getDate() != null){
            SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = actualDateFormat.parse(news.getDate());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(date != null) {
                String topicDate = finalDateFormat.format(date);
                newsCellDate.setText(topicDate);
            }
        }
        if(news.getContent() != null){
            newsCellContent.setText(news.getContent());
        }
    }
}
