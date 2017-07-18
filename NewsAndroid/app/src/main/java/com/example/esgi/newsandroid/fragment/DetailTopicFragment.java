package com.example.esgi.newsandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.Post;
import com.example.esgi.newsandroid.models.SessionData;
import com.example.esgi.newsandroid.models.Topic;
import com.example.esgi.newsandroid.network.ApiService;
import com.example.esgi.newsandroid.view.adapter.ListPostsAdapter;

import java.util.ArrayList;

/**
 * Created by meryl on 25/06/2017.
 */

public class DetailTopicFragment extends Fragment {

    private Topic topic;
    private ArrayList<Post> posts;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView postTitle;
    TextView postContent;
    TextView postDate;
    TextView title;
    TextView content;

    public DetailTopicFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_detail, container, false);
        title = (TextView) view.findViewById(R.id.tv_detail_topic_title);
        content = (TextView) view.findViewById(R.id.tv_detail_topic_content);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_topic_list_posts);
        initView();
        return view;
    }

    public static DetailTopicFragment newInstance(){
        Bundle args = new Bundle();
        DetailTopicFragment fragment = new DetailTopicFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void loadTopicDetail(Topic topic){
        ApiService.getInstance(getContext()).getPostsForTopic(topic, new ApiService.ApiResult<ArrayList<Post>>() {

            @Override
            public void success(ArrayList<Post> res) {
                recyclerView.setAdapter(new ListPostsAdapter(res));
            }

            @Override
            public void error(int code, String message) {

            }
        });
    }

    private void initView(){
        topic = SessionData.getINSTANCE().getCurrentTopic();
        title.setText(topic.getTitle());
        content.setText(topic.getContent());

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        loadTopicDetail(topic);
    }
}
