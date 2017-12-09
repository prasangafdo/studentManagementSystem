package com.fdo.prasanga.studentmanagementsystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeesActivity extends AppCompatActivity {

    EditText et_Student_ID;
    Button btn_toActivity2;
    String Student_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_Student_ID = (EditText) findViewById(R.id.et_fees_StudentID);
        btn_toActivity2 = (Button) findViewById(R.id.btn_toActivity2);

        btn_toActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student_ID = et_Student_ID.getText().toString();


                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FeesActivity.this);//Saving Student ID
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("Student_ID", Student_ID);
                editor.apply();

                String type = "getGrade";
                BackgroundWorker backgroundWorker = new BackgroundWorker(FeesActivity.this);
                backgroundWorker.execute(type, Student_ID);
                finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
