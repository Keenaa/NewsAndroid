package com.example.esgi.newsandroid.fragment;

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
import com.example.esgi.newsandroid.models.Topic;
import com.example.esgi.newsandroid.network.ApiService;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.id.message;

/**
 * Created by meryl on 28/06/2017.
 */

public class AddTopicFragment extends Fragment {
    EditText title;
    EditText summary;
    Button submit;

    Topic topic;

    public AddTopicFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_topic, container, false);
        title = (EditText) view.findViewById(R.id.et_title_add);
        summary = (EditText) view.findViewById(R.id.et_summary_add);
        submit = (Button) view.findViewById(R.id.bt_send_add);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topic = new Topic();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topic.setTitle(title.getText().toString());
                topic.setContent(summary.getText().toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDate = simpleDateFormat.format(new Date());
                topic.setDate(currentDate);
                sendTopic();
            }
        });
    }

    public void sendTopic(){
        ApiService.getInstance(getContext()).createTopic(topic, new ApiService.ApiResult<String>(){
            @Override
            public void success(String res) {
                displayListTopics();
            }

            @Override
            public void error(int code, String message) {

            }
        });
    }

    public void displayListTopics(){
        FragmentManager fm = getFragmentManager();
        android.support.v4.app.Fragment fragment = ListTopicsFragment.newInstance();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static AddTopicFragment newInstance(){
        Bundle args = new Bundle();
        AddTopicFragment fragment = new AddTopicFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
