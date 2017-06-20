package com.example.esgi.newsandroid.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by meryl on 20/06/2017.
 */

public class Token {

    @SerializedName("token")
    private String value;

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
