package com.example.esgi.newsandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.manager.RealmManager;
import com.example.esgi.newsandroid.models.Comment;
import com.example.esgi.newsandroid.models.News;
import com.example.esgi.newsandroid.models.SessionData;
import com.example.esgi.newsandroid.network.ApiService;
import com.example.esgi.newsandroid.view.adapter.ListCommentsAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailNewsFragment extends Fragment {

    private News news;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView postTitle;
    TextView postContent;
    TextView postDate;
    TextView title;
    TextView content;

    public DetailNewsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        title = (TextView) view.findViewById(R.id.tv_detail_news_title);
        content = (TextView) view.findViewById(R.id.tv_detail_news_content);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_news_list_posts);
        initView();
        return view;
    }

    public static DetailNewsFragment newInstance(){
        Bundle args = new Bundle();
        DetailNewsFragment fragment = new DetailNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void loadNewsDetail(){
        ApiService.getInstance(getContext()).getComments(new ApiService.ApiResult<Boolean>() {
            @Override
            public void success(Boolean res) {
                if (res) {
                    RealmManager.getInstance().getCommentByNewsId(news.getId(), new ApiService.ApiResult<ArrayList<Comment>>() {
                        @Override
                        public void success(ArrayList<Comment> res) {
                            recyclerView.setAdapter(new ListCommentsAdapter(res));
                        }

                        @Override
                        public void error(int code, String message) {
                            Log.d("Error RealmManager : ",message);
                        }
                    });
                }
            }

            @Override
            public void error(int code, String message) {

            }
        });
    }

    private void initView(){
        news = SessionData.getINSTANCE().getCurrentNews();
        title.setText(news.getTitle());
        content.setText(news.getContent());

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        loadNewsDetail();

    }
}
