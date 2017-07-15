package com.example.esgi.newsandroid.models;

import com.example.esgi.newsandroid.models.User;

/**
 * Created by meryl on 20/06/2017.
 */

public class SessionData {

    private static final SessionData INSTANCE = new SessionData();
    private String token;
    private User currentUser;
    private Topic currentTopic;
    private Post currentPost;
    private News currentNews;
    private Comment currentComment;

    public static SessionData getINSTANCE() {
        return INSTANCE;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Topic getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(Topic currentTopic) {
        this.currentTopic = currentTopic;
    }

    public Post getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }

    public News getCurrentNews() {
        return currentNews;
    }

    public void setCurrentNews(News currentNews) {
        this.currentNews = currentNews;
    }

    public Comment getCurrentComment() {
        return currentComment;
    }

    public void setCurrentComment(Comment currentComment) {
        this.currentComment = currentComment;
    }


}
