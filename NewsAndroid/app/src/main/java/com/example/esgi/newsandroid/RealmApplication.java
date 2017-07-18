package com.example.esgi.newsandroid;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Guillaume Chb Alias Shemana
 * 18/07/2017
 */

public class RealmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
