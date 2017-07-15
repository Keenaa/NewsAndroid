package com.example.esgi.newsandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.User;
import com.example.esgi.newsandroid.network.ApiService;

/**
 * Created by meryl on 13/06/2017.
 */

public class SignInActivity extends AppCompatActivity {

    Button signin_button;
    EditText email;
    EditText firstname;
    EditText lastname;
    EditText password;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signin_button = (Button)findViewById(R.id.signin_button);
        email = (EditText)findViewById(R.id.signin_email);
        firstname = (EditText)findViewById(R.id.signin_firstname);
        lastname = (EditText)findViewById(R.id.signin_lastname);
        password = (EditText)findViewById(R.id.signin_password);
        user = new User();

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setFirstname(firstname.getText().toString());
                user.setLastname(lastname.getText().toString());
                ApiService.getInstance(getApplicationContext()).createUser(user, new ApiService.ApiResult<String>() {
                    @Override
                    public void success(String res) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void error(int code, String message) {
                        System.out.println(code + "  :  " + message);
                    }
                });
            }
        });
    }
}
