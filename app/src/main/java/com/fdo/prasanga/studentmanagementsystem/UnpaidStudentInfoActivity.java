package com.fdo.prasanga.studentmanagementsystem;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class UnpaidStudentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid_student_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Unpaid Student information");

        TextView tv_name, tv_address, tv_grade, tv_P_Num;

        tv_name = (TextView) findViewById(R.id.tv_up_Name);
        tv_address = (TextView) findViewById(R.id.tv_up_Address);
        tv_grade = (TextView) findViewById(R.id.tv_up_grade);
        tv_P_Num = (TextView) findViewById(R.id.tv_up_p_Num);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);//Retrieving all saved data.
       String name = preferences.getString("name", null);//Redundent
       String address = preferences.getString("address", null);
        String grade = preferences.getString("grade", null);
        String p_num = preferences.getString("p_num", null);
 /*
*/
        tv_name.setText(name);
      tv_address.setText(address);
        tv_grade.setText(grade);
        tv_P_Num.setText(p_num);  /**/
        //String student_info_url="http://10.0.2.2/skyManagement/studentManagementSystem/getUnpaidStudentInfo.php";

      //  final ListView lv= (ListView) findViewById(R.id.lv_m);
      //  final Student_Info_Downloader d=new Student_Info_Downloader(this,student_info_url,lv);
      //  d.execute();
    }
}
