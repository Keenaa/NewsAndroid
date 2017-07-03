package com.example.esgi.newsandroid.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.activity.MainActivity;
import com.example.esgi.newsandroid.models.Post;
import com.example.esgi.newsandroid.models.SessionData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by meryl on 26/06/2017.
 */

public class ListPostsViewHolder extends RecyclerView.ViewHolder {

    private TextView postCellTitle;
    private TextView postCellDate;
    private TextView postCellContent;

    private Post currentPost;

    public ListPostsViewHolder(View itemView) {
        super(itemView);
        postCellTitle = (TextView) itemView.findViewById(R.id.cell_post_title);
        postCellDate = (TextView) itemView.findViewById(R.id.cell_post_date);
        postCellContent = (TextView) itemView.findViewById(R.id.cell_post_content);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionData.getINSTANCE().setCurrentPost(currentPost);
                if (v.getContext() instanceof MainActivity) {
                    ((MainActivity) v.getContext()).displayListTopics();
                }

            }
        });
    }

    public void bind(Post post) {
        currentPost = post;
        SimpleDateFormat finalDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (post.getTitle() != null) {
            postCellTitle.setText(post.getTitle());
        }
        if (post.getDate() != null) {
            SimpleDateFormat actualDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = actualDateFormat.parse(post.getDate());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            String postDate = finalDateFormat.format(date);
            postCellDate.setText(postDate);
        }
        if (post.getContent() != null) {
            postCellContent.setText(post.getContent());
        }
    }
}