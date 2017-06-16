package com.example.esgi.newsandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.manager.UserManager;
import com.example.esgi.newsandroid.network.AuthenticationNetwork;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by meryl on 13/06/2017.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    EditText email;

    @BindView(R.id.login_password)
    EditText password;

    @BindView(R.id.login_button)
    Button login_button;

    @BindView(R.id.login_get_password)
    TextView get_password;

    @BindView(R.id.login_signin)
    TextView signin;

    UserManager user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setEmail(email.getText().toString());
                AuthenticationNetwork authenticationNetwork = new AuthenticationNetwork();
                authenticationNetwork.login(user, password.getText().toString());
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
