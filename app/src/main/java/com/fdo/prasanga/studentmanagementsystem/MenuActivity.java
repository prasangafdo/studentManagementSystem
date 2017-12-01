package com.fdo.prasanga.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle("Menu");
        //Compatible with more Android versions

        Button btn_Register, btn_ViewDetails, btn_CalculateFees, btn_ViewUnpaidStudents;

        btn_Register = (Button) findViewById(R.id.btn_Register);
        btn_ViewDetails =(Button) findViewById(R.id.btn_ViewStudentDetails);
        btn_CalculateFees =(Button) findViewById(R.id.btn_CalculateFees);
        btn_ViewUnpaidStudents =(Button) findViewById(R.id.btn_ViewUnpaidStudents);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_ViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, StudentDetailsActivity.class);
                startActivity(intent);
            }
        });

        btn_CalculateFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, FeesActivity.class);
                startActivity(intent);
            }
        });

        btn_ViewUnpaidStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, UnpaidStudentsActivity.class);
                startActivity(intent);
            }
        });

    }
}
