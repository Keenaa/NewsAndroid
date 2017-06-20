package com.example.esgi.newsandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.manager.UserManager;
import com.example.esgi.newsandroid.network.AuthenticationNetwork;

/**
 * Created by meryl on 13/06/2017.
 */

public class SignInActivity extends AppCompatActivity {

    Button signin_button = (Button)findViewById(R.id.signin_button);
    EditText email = (EditText)findViewById(R.id.signin_email);
    EditText firstname = (EditText)findViewById(R.id.signin_firstname);
    EditText lastname = (EditText)findViewById(R.id.signin_lastname);
    EditText password = (EditText)findViewById(R.id.signin_password);


    UserManager user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setEmail(email.getText().toString());
                user.setFirstname(firstname.getText().toString());
                user.setLastname(lastname.getText().toString());
              //  AuthenticationNetwork authenticationNetwork = new AuthenticationNetwork();
                //authenticationNetwork.signIn(user, password.getText().toString());
            }
        });
    }
}
