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
import com.example.esgi.newsandroid.models.News;
import com.example.esgi.newsandroid.models.SessionData;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailNewsFragment extends Fragment {

    private News news;

    //RecyclerView recyclerView;
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
        initView();
        return view;
    }

    public static DetailNewsFragment newInstance(){
        Bundle args = new Bundle();
        DetailNewsFragment fragment = new DetailNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /*private void loadNewsDetail(News news){
        ApiService.getInstance(getContext()).getNewsById(news, new ApiService.ApiResult<news>() {
            @Override
            public void success(News res) {
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
        news = SessionData.getINSTANCE().getCurrentNews();
        title.setText(news.getTitle());
        content.setText(news.getContent());

        //LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        //loadNewsDetail(news);
        //recyclerView.setLayoutManager(llm);
    }
}
