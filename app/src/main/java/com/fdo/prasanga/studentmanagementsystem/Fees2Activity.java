package com.fdo.prasanga.studentmanagementsystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fees2Activity extends AppCompatActivity {
    Button btn_completePayment;
    TextView tv_CurrentDate, tv_grade, tv_Fee, tv_studentID;
    DatePicker dtp_calendar;
    String StudentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees2);

         btn_completePayment = (Button) findViewById(R.id.btn_completePaytment);
        tv_CurrentDate = (TextView) findViewById(R.id.tv_CurrentDate);

        tv_grade = (TextView) findViewById(R.id.tv_grade);
        tv_Fee = (TextView) findViewById(R.id.tv_Fee);
        tv_studentID = (TextView) findViewById(R.id.tv_studentID);
        dtp_calendar = (DatePicker) findViewById(R.id.dtp_Calendar);


        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        tv_CurrentDate.setText(date);//Setting the current date


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Fees2Activity.this);//Retrieving all saved data.
        String student_ID = preferences.getString("Student_ID", null);
        String grade = preferences.getString("grade", "def");//def is the default value

        tv_grade.setText(grade);
        tv_studentID.setText(student_ID);


        if (Integer.parseInt(grade)>5){//Calculating fees

          //  Toast.makeText(getApplicationContext(),"Fee is 4000",Toast.LENGTH_SHORT).show();//Just for debugging
            tv_Fee.setText("4000.00");

        }
        else
        {
           // Toast.makeText(getApplicationContext(),"Fee is 3000", Toast.LENGTH_SHORT).show();
            tv_Fee.setText("3000.00");
        }

      btn_completePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CalendarDate date = new CalendarDate();//Generating date from the subclass
                date.setDate(Integer.toString(dtp_calendar.getYear()),Integer.toString(dtp_calendar.getMonth()+1), Integer.toString(dtp_calendar.getDayOfMonth()));

                String type = "updateFees";
                String studentID = tv_studentID.getText().toString();
                String next_Date = date.getDate();
                String current_Date = tv_CurrentDate.getText().toString();
                String fee = tv_Fee.getText().toString();
                //Calculate fees and send

                Toast.makeText(getApplicationContext(),"St: "+studentID+" N Date: "+next_Date+ "C date: "+current_Date, Toast.LENGTH_LONG).show();

                BackgroundWorker backgroundWorker = new BackgroundWorker(Fees2Activity.this);
                backgroundWorker.execute(type,studentID, next_Date, current_Date, fee);//Put strings to execute

            }
        });

    }
}
