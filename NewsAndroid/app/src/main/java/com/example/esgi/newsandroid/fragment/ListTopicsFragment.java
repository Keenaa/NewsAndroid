package com.example.esgi.newsandroid.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.Topic;
import com.example.esgi.newsandroid.network.ApiService;
import com.example.esgi.newsandroid.view.adapter.ListTopicsAdapter;

import java.util.ArrayList;

/**
 * Created by meryl on 22/06/2017.
 */

public class ListTopicsFragment extends Fragment {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton addTopic;


    ArrayList<Topic> topics;
    ListTopicsAdapter listTopicsAdapter;

    public ListTopicsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_list_topics, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_fragment_list_topics);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_fragment_list_topics);

        initView();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addTopic = (FloatingActionButton) view.findViewById(R.id.fab_add_topic);
        addTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAddTopic();
            }
        });
    }
    public void displayAddTopic(){
        FragmentManager fm = getFragmentManager();
        Fragment fragment = AddTopicFragment.newInstance();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initView(){
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        getTopics();
        recyclerView.setLayoutManager(llm);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTopics();
            }
        });

    }

    private void getTopics(){

        ApiService.getInstance(getContext()).getTopics(new ApiService.ApiResult<ArrayList<Topic>>() {
            @Override
            public void success(ArrayList<Topic> res) {
                recyclerView.setAdapter(new ListTopicsAdapter(res));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void error(int code, String message) {

            }
        });
    }

    public static ListTopicsFragment newInstance() {

        Bundle args = new Bundle();

        ListTopicsFragment fragment = new ListTopicsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
