package com.example.esgi.newsandroid.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.Comment;
import com.example.esgi.newsandroid.models.News;
import com.example.esgi.newsandroid.models.Topic;
import com.example.esgi.newsandroid.network.ApiService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCommentFragment extends Fragment {
    EditText title;
    EditText summary;
    Button submit;

    News news;
    Comment comment;

    public AddCommentFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_comment, container, false);
        title = (EditText) view.findViewById(R.id.et_title_add);
        summary = (EditText) view.findViewById(R.id.et_summary_add);
        submit = (Button) view.findViewById(R.id.bt_send_add);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        comment = new Comment();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment.setTitle(title.getText().toString());
                comment.setContent(summary.getText().toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDate = simpleDateFormat.format(new Date());
                comment.setDate(currentDate);
                sendComment();
            }
        });
    }

    public void sendComment(){
        ApiService.getInstance(getContext()).createComment(comment, new ApiService.ApiResult<String>(){
            @Override
            public void success(String res) {
                displayListComments();
            }

            @Override
            public void error(int code, String message) {

            }
        });
    }

    public void displayListComments(){
        FragmentManager fm = getFragmentManager();
        android.support.v4.app.Fragment fragment = ListCommentsFragment.newInstance();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static AddCommentFragment newInstance(){
        Bundle args = new Bundle();
        AddCommentFragment fragment = new AddCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
