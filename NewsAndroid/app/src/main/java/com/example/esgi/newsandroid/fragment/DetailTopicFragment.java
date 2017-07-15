package com.example.esgi.newsandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.Post;
import com.example.esgi.newsandroid.models.SessionData;
import com.example.esgi.newsandroid.models.Topic;
import java.util.ArrayList;

/**
 * Created by meryl on 25/06/2017.
 */

public class DetailTopicFragment extends Fragment {

    private Topic topic;
    private ArrayList<Post> posts;

    //RecyclerView recyclerView;
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
        initView();
        return view;
    }

    public static DetailTopicFragment newInstance(){
        Bundle args = new Bundle();
        DetailTopicFragment fragment = new DetailTopicFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /*private void loadTopicDetail(Topic topic){
        ApiService.getInstance(getContext()).getTopicById(topic, new ApiService.ApiResult<Topic>() {
            @Override
            public void success(Topic res) {
                title.setText(res.getTitle());
                content.setText(res.getContent());
                recyclerView.setAdapter(new ListPostsAdapter(res));

            }

            @Override
            public void error(int code, String message) {

            }
        });
    }*/

    private void initView(){
        topic = SessionData.getINSTANCE().getCurrentTopic();
        title.setText(topic.getTitle());
        content.setText(topic.getContent());

        //LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        //loadTopicDetail(topic);
        //recyclerView.setLayoutManager(llm);
    }
}
