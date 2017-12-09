package com.fdo.prasanga.studentmanagementsystem;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");

        final EditText et_Username, et_Password;

        et_Username = (EditText) findViewById(R.id.et_login_Usrename);
        et_Password = (EditText) findViewById(R.id.et_login_Password);
     /*
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username =et_Username.getText().toString();
                String password =et_Password.getText().toString();
                String type = "Login";

                BackgroundWorker backgroundWorker = new BackgroundWorker(LoginActivity.this);
               // backgroundWorker.execute(type, username, password);//Sending login credentials to the background
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);//Just remove this
                startActivity(intent);
            }
        });
*/

        TextView cv_login;

        cv_login = (TextView) findViewById(R.id.textView);

        cv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
               // startActivity(intent);
                String username =et_Username.getText().toString();
                String password =et_Password.getText().toString();
                String type = "Login";

                BackgroundWorker backgroundWorker = new BackgroundWorker(LoginActivity.this);
                backgroundWorker.execute(type, username, password);//Sending login credentials to the background
               // Intent intent = new Intent(LoginActivity.this, MenuActivity.class);//Just remove this
               // startActivity(intent);

            }
        });

    }
}
