package com.fdo.prasanga.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class CalenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        final DatePicker calender;
        Button btn_NextDateConfirmed;

        calender = (DatePicker) findViewById(R.id.datePicker1);
        btn_NextDateConfirmed = (Button) findViewById(R.id.btn_NextDateConfirmed);

       // DatePicker.
        btn_NextDateConfirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String   day  = (Integer.toString(calender.getDayOfMonth()));
                String   month= (Integer.toString(calender.getMonth()+1));
                String   year = (Integer.toString(calender.getYear()));

                String selected_Date = year+"-"+month+"-"+day;

                Intent intent = new Intent(CalenderActivity.this, FeesActivity.class);//Currently calender is under Fees class
                intent.putExtra("selectedDate", selected_Date);//Passing selected Date to the previous view
                startActivity(intent);
            }
        });
                //DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // Intent intent = new Intent(CalenderActivity.this, RegisterActivity.class);
      //  startActivity(intent);
        finish();//Killing the activity
    }
}
