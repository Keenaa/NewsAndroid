package com.example.esgi.newsandroid.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.activity.MainActivity;
import com.example.esgi.newsandroid.models.Comment;
import com.example.esgi.newsandroid.models.SessionData;
import com.example.esgi.newsandroid.models.Topic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent on 14/07/2017.
 */

public class ListCommentsViewHolder extends RecyclerView.ViewHolder {

    private TextView commentCellTitle;
    private TextView commentCellDate;
    private TextView commentCellContent;

    private Comment currentComment;

    public ListCommentsViewHolder(View itemView){
        super(itemView);
        commentCellTitle = (TextView) itemView.findViewById(R.id.cell_comments_title);
        commentCellDate = (TextView) itemView.findViewById(R.id.cell_comments_date);
        commentCellContent = (TextView) itemView.findViewById(R.id.cell_comments_content);
    }

    public void bind(Comment comment){
        currentComment = comment;
        SimpleDateFormat finalDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (comment.getTitle() != null){
            commentCellTitle.setText(comment.getTitle());
        }
        if(comment.getDate() != null){
            SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = actualDateFormat.parse(comment.getDate());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(date != null) {
                String topicDate = finalDateFormat.format(date);
                commentCellDate.setText(topicDate);
            }
        }
        if(comment.getContent() != null){
            commentCellContent.setText(comment.getContent());
        }
    }
}
