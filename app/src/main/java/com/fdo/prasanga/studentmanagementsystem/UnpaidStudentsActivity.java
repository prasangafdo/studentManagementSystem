package com.fdo.prasanga.studentmanagementsystem;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class UnpaidStudentsActivity extends AppCompatActivity {

    //String student_ID_url="http://10.0.2.2/skyManagement/studentManagementSystem/getUnpaidStudentID.php";
    String student_ID_url="http://rapiddelivery.000webhostapp.com/skyManagement/studentManagementSystem/getUnpaidStudentID.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid_students);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Unpaid Students' ID List");

        final ListView lv= (ListView) findViewById(R.id.lv);
        final Student_ID_Downloader d=new Student_ID_Downloader(this,student_ID_url,lv);
        d.execute();

    }
}
