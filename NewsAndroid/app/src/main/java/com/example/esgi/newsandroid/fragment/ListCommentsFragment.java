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
import com.example.esgi.newsandroid.models.Comment;
import com.example.esgi.newsandroid.network.ApiService;
import com.example.esgi.newsandroid.view.adapter.ListCommentsAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCommentsFragment extends Fragment {



    RecyclerView commentRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton addComment;


    ArrayList<Comment> comments;
    ListCommentsAdapter listCommentsAdapter;

    public ListCommentsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_list_comments, container, false);

        commentRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_fragment_list_comments);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_fragment_list_comments);

        initView();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addComment = (FloatingActionButton) view.findViewById(R.id.fab_add_comment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAddComment();
            }
        });
    }
    public void displayAddComment(){
        FragmentManager fm = getFragmentManager();
        Fragment fragment = AddCommentFragment.newInstance();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initView(){
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        getComments();
        commentRecyclerView.setLayoutManager(llm);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getComments();
            }
        });

    }

    private void getComments(){

        ApiService.getInstance(getContext()).getComments(new ApiService.ApiResult<ArrayList<Comment>>() {
            @Override
            public void success(ArrayList<Comment> res) {
                commentRecyclerView.setAdapter(new ListCommentsAdapter(res));
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void error(int code, String message) {

            }
        });
    }

    public static ListCommentsFragment newInstance() {

        Bundle args = new Bundle();

        ListCommentsFragment fragment = new ListCommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
