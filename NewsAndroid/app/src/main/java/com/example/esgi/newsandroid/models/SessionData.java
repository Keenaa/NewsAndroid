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
}
