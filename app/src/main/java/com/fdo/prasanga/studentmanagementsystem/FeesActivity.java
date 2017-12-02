package com.fdo.prasanga.studentmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

        et_Student_ID = (EditText) findViewById(R.id.et_fees_StudentID);
        btn_toActivity2 = (Button) findViewById(R.id.btn_toActivity2);

        btn_toActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeesActivity.this, Fees2Activity.class);
                Student_ID = et_Student_ID.getText().toString();


                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FeesActivity.this);//Saving Student ID
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("Student_ID", Student_ID);
                editor.apply();

                String type = "getGrade";
                //String studentID1 = et_fees_StudentID.getText().toString();
                BackgroundWorker backgroundWorker = new BackgroundWorker(FeesActivity.this);
                backgroundWorker.execute(type, Student_ID);

               // intent.putExtra("Student_ID", Student_ID);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
/*

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FeesActivity.this);//Retrieving all saved data.
        String student_ID = preferences.getString("Student_ID", null);//Post this student ID to the php file
        String grade = preferences.getString("grade", "def");//def is the default value
        String selected_Date = preferences.getString("selected_Date", null);

        if (grade.equals("TextView")){
            Toast.makeText(getApplicationContext(), "Grade is null now", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(getApplicationContext(), grade, Toast.LENGTH_SHORT).show();
            et_fees_StudentID.setText(student_ID);//Setting back the studentID
            tv_DateOutput.setText(selected_Date);
            tv_grade.setText(grade);

            //Calculating fees
            if (Integer.parseInt(grade)>5){

                Toast.makeText(getApplicationContext(),"Fee is 4000",Toast.LENGTH_SHORT).show();
                tv_Fee.setText("Rs: 4000.00");

            }
            else
            {
                Toast.makeText(getApplicationContext(),"Fee is 3000",Toast.LENGTH_SHORT).show();
                tv_Fee.setText("Rs. 3000.00");
            }
        }
      /*   */
    }
}
