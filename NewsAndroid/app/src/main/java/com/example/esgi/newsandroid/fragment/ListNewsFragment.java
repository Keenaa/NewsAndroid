package com.example.esgi.newsandroid.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.News;
import com.example.esgi.newsandroid.network.ApiService;
import com.example.esgi.newsandroid.view.adapter.ListNewsAdapter;

import com.example.esgi.newsandroid.R;

import java.util.ArrayList;

/**
 * Created by vincent DURPOIX on 14/07/2017
 */
public class ListNewsFragment extends Fragment {

    RecyclerView newsRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton addNews;

    ArrayList<News> news;

    public ListNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_list_news, container, false);

        newsRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_fragment_list_news);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_fragment_list_news);
        initView();
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addNews = (FloatingActionButton) view.findViewById(R.id.fab_add_news);
        addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAddNews();
            }
        });
    }
    public void displayAddNews(){
        FragmentManager fm = getFragmentManager();
        Fragment fragment = AddNewsFragment.newInstance();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initView(){
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        newsRecyclerView.setLayoutManager(llm);
        getNews();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();
            }
        });

    }

    private void getNews() {

        ApiService.getInstance(getContext()).getNews(new ApiService.ApiResult<ArrayList<News>>() {
            @Override
            public void success(ArrayList<News> res) {
                newsRecyclerView.setAdapter(new ListNewsAdapter(res));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void error(int code, String message) {

            }
        });
    }

    public static ListNewsFragment newInstance() {

        Bundle args = new Bundle();

        ListNewsFragment fragment = new ListNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
