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
import com.example.esgi.newsandroid.models.News;
import com.example.esgi.newsandroid.models.Topic;
import com.example.esgi.newsandroid.network.ApiService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewsFragment extends Fragment {
    EditText title;
    EditText content;
    Button submit;

    News news;

    public AddNewsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_news, container, false);
        title = (EditText) view.findViewById(R.id.et_title_add);
        content = (EditText) view.findViewById(R.id.et_summary_add);
        submit = (Button) view.findViewById(R.id.bt_send_add);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        news = new News();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news.setTitle(title.getText().toString());
                news.setContent(content.getText().toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDate = simpleDateFormat.format(new Date());
                news.setDate(currentDate);
                sendNews();
            }
        });
    }

    public void sendNews(){
        ApiService.getInstance(getContext()).createNews(news, new ApiService.ApiResult<String>(){
            @Override
            public void success(String res) {
                displayListNews();
            }

            @Override
            public void error(int code, String message) {

            }
        });
    }

    public void displayListNews(){
        FragmentManager fm = getFragmentManager();
        android.support.v4.app.Fragment fragment = ListNewsFragment.newInstance();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static AddNewsFragment newInstance(){
        Bundle args = new Bundle();
        AddNewsFragment fragment = new AddNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
