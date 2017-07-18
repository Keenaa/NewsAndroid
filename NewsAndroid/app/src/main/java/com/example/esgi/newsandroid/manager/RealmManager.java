package com.example.esgi.newsandroid.manager;

import com.example.esgi.newsandroid.models.Comment;
import com.example.esgi.newsandroid.models.Post;
import com.example.esgi.newsandroid.models.Topic;
import com.example.esgi.newsandroid.network.ApiService;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Guillaume Chb Alias Shemana
 * 18/07/2017
 */

public class RealmManager {
    private static final RealmManager ourInstance = new RealmManager();
    private Realm realmInstance;
    public static RealmManager getInstance() {
        return ourInstance;
    }

    private RealmManager() {

    }

    public void getCommentByNewsId(final String idNews, final ApiService.ApiResult<ArrayList<Comment>> callback) {
      /*  realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ArrayList<Comment> comments = new ArrayList<>();
                RealmResults<Comment> commentRealmResults = realm.where(Comment.class).equalTo("news",idNews).findAll();
                if(commentRealmResults.size() == 0 || commentRealmResults == null) {
                    callback.error(404,"Error when retrieve comments in localStorage");
                    return;
                }
                comments.addAll(commentRealmResults);
                callback.success(comments);
            }
        });
        */
    }
}
