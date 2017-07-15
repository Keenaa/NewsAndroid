package com.example.esgi.newsandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.esgi.newsandroid.R;
import com.example.esgi.newsandroid.models.SessionData;
import com.example.esgi.newsandroid.network.ApiService;
import com.example.esgi.newsandroid.models.Login;
import com.google.gson.Gson;


/**
 * Created by meryl on 13/06/2017.
 */

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login_button;
    TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);
        login_button = (Button)findViewById(R.id.login_button);
        signin = (TextView)findViewById(R.id.login_signin);

        final Login login = new Login();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setEmail(email.getText().toString());
                login.setPassword(password.getText().toString());
                Gson gson = new Gson();
                String login_print = gson.toJson(login);
                Log.d("LOGIN : ", login_print);
                ApiService.getInstance(getApplicationContext()).authentication(login, new ApiService.ApiResult<String>() {
                    @Override
                    public void success(String res) {
                        Log.d("token",res);
                        SessionData.getINSTANCE().setToken(res);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void error(int code, String message) {
                        System.out.println(code + "  :  " + message);
                    }
                });
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(goToNextActivity);
            }
        });
    }
}
