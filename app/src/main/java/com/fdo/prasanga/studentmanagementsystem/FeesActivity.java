package com.fdo.prasanga.studentmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FeesActivity extends AppCompatActivity {
    Button btn_getDate, btn_calculateFees, btn_feePaid;
    TextView tv_CurrentDate, tv_DateOutput, tv_grade, tv_Fee;
    EditText et_fees_StudentID;
    String grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);


        btn_calculateFees = (Button) findViewById(R.id.btn_calculateFees);
        btn_getDate = (Button) findViewById(R.id.btn_getDate);
        btn_feePaid = (Button) findViewById(R.id.btn_completePaytment);
        tv_CurrentDate = (TextView) findViewById(R.id.tv_CurrentDate);
        tv_DateOutput = (TextView) findViewById(R.id.tv_DateOutput);
        tv_grade = (TextView) findViewById(R.id.tv_grade);
        tv_Fee = (TextView) findViewById(R.id.tv_Fee);
        et_fees_StudentID = (EditText) findViewById(R.id.et_fees_StudentID);
       // ck_RegistrationFee = (CheckBox) findViewById(R.id.ck_RegistrationFee);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        tv_CurrentDate.setText(date);//Setting the current date




                btn_getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FeesActivity.this);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.putString("Student_ID", et_fees_StudentID.getText().toString());
                editor.putString("grade", tv_grade.getText().toString());
               // editor.putString("calculatedFee", tv_Fee.getText().toString());
                editor.apply();
                Intent intent = new Intent(FeesActivity.this, CalenderActivity.class);
                startActivity(intent);
                finish();
            }
        });
                 btn_calculateFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = "getGrade";
                String studentID1 = et_fees_StudentID.getText().toString();
                BackgroundWorker backgroundWorker = new BackgroundWorker(FeesActivity.this);
                backgroundWorker.execute(type, studentID1);


                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FeesActivity.this);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("Student_ID", et_fees_StudentID.getText().toString());
                editor.apply();
            }
        });

        btn_feePaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = "updateFees";
                //Calculate fees and send
            }
        });

        Intent intent1 = getIntent();
        String selectedDate = intent1.getStringExtra("selectedDate");
        tv_DateOutput.setText(selectedDate);

    }


    @Override
    protected void onResume() {
        super.onResume();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FeesActivity.this);//Retrieving all saved data.
        String student_ID = preferences.getString("Student_ID", null);//Post this student ID to the php file
        String grade = preferences.getString("grade", null);
        String selected_Date = preferences.getString("selected_Date", null);


        et_fees_StudentID.setText(student_ID);//Setting back the studentID
        tv_DateOutput.setText(selected_Date);
        tv_grade.setText(grade);


            if (Integer.parseInt(grade)>5){

                Toast.makeText(getApplicationContext(),"Fee is 4000",Toast.LENGTH_SHORT).show();
                tv_Fee.setText("Rs: 4000.00");

            }
           else
            {
                Toast.makeText(getApplicationContext(),"Fee is 3000",Toast.LENGTH_SHORT).show();
                tv_Fee.setText("Rs. 3000.00");
            }
              //  int_fee =4000;
/*

*/



    }
}
