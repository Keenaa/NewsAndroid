package com.example.esgi.newsandroid.manager;

import com.example.esgi.newsandroid.models.News;

import java.util.ArrayList;

/**
 * Created by Guillaume Chb Alias Shemana
 * 18/07/2017
 */

public class NewsManager {
    private static final NewsManager ourInstance = new NewsManager();

    public static NewsManager getInstance() {
        return ourInstance;
    }
    public ArrayList<News> newses = new ArrayList<>();
    private NewsManager() {
    }

}
