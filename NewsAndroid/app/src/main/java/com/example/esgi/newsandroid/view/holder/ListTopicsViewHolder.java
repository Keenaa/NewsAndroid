package com.example.esgi.newsandroid.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.activity.MainActivity;
import com.example.esgi.newsandroid.models.SessionData;
import com.example.esgi.newsandroid.models.Topic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by meryl on 22/06/2017.
 */

public class ListTopicsViewHolder extends RecyclerView.ViewHolder {

    private TextView topicCellTitle;
    private TextView topicCellDate;
    private TextView topicCellContent;

    private Topic currentTopic;

    public ListTopicsViewHolder(View itemView){
        super(itemView);
        topicCellTitle = (TextView) itemView.findViewById(R.id.cell_topics_title);
        topicCellDate = (TextView) itemView.findViewById(R.id.cell_topics_date);
        topicCellContent = (TextView) itemView.findViewById(R.id.cell_topics_content);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionData.getINSTANCE().setCurrentTopic(currentTopic);
                if(v.getContext() instanceof  MainActivity) {
                    ((MainActivity)v.getContext()).displayTopicDetails();
                }

            }
        });
    }

    public void bind(Topic topic){
        currentTopic = topic;
        SimpleDateFormat finalDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (topic.getTitle() != null){
            topicCellTitle.setText(topic.getTitle());
        }
        if(topic.getContent() != null){
            topicCellContent.setText(topic.getContent());
        }
    }
}
