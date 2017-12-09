package com.fdo.prasanga.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle("Menu");
        //Compatible with more Android versions

        TextView tv_Reg, tv_calFees, tv_unpaidStudents;

        tv_Reg = (TextView) findViewById(R.id.tv_m_studentReg);
        tv_calFees = (TextView) findViewById(R.id.tv_m_calculateFees);
        tv_unpaidStudents = (TextView) findViewById(R.id.tv_m_unpaidStudents);

        tv_Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        tv_calFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, FeesActivity.class);
                startActivity(intent);
            }
        });


        tv_unpaidStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, UnpaidStudentsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
