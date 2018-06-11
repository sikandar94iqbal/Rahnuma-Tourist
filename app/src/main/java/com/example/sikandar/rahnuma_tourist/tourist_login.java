package com.example.sikandar.rahnuma_tourist;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.example.sikandar.*;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class tourist_login extends Activity {

    EditText ET_NAME,ET_PASS;
    String login_name,login_pass;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourist_login);

        ET_NAME=(EditText) findViewById(R.id.G_Login_UN_ET);
        ET_PASS=(EditText) findViewById(R.id.G_login_pass_ET);

        TextView signup = (TextView)findViewById(R.id.signup_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tourist_login.this,tourist_signup.class));
            }
        });
    }
    public void user_login(View view)
    {
        login_name=ET_NAME.getText().toString();
        login_pass=ET_PASS.getText().toString();
        String method="login";
        BackgroundTask backgroundTask=new BackgroundTask(this);

        if(login_name.isEmpty() || login_pass.isEmpty()){
            Toast.makeText(tourist_login.this,"Fields missing..",Toast.LENGTH_LONG).show();
        }
        else {
            if (isNetworkAvailable()) {
                backgroundTask.execute(method, login_name, login_pass);
            } else {
                Toast.makeText(tourist_login.this, "No network", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void login_to_signup(View view) {
        Intent intent = new Intent(tourist_login.this, tourist_signup.class);
        startActivity(intent);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}

