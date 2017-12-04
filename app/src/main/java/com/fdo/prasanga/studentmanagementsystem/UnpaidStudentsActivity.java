package com.fdo.prasanga.studentmanagementsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class UnpaidStudentsActivity extends AppCompatActivity {

    String student_ID_url="http://10.0.2.2/skyManagement/studentManagementSystem/getUnpaidStudentID.php";
    //String student_info_url="http://10.0.2.2/skyManagement/studentManagementSystem/getUnpaidStudentInfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid_students);
        //Get today date
        //select students where date = today
        //show all student id's on toast
        //put that to a table
        //Ge other relevant data from the db
        //Show them in the table

      /*  getUnpaidStudents unpaidStudents = new getUnpaidStudents(UnpaidStudentsActivity.this);
        unpaidStudents.execute("getStudent_ID");

        GridView gv_1 = (GridView) findViewById(R.id.gv_1);
        gv_1.setNumColumns(5);
*/
        final ListView lv= (ListView) findViewById(R.id.lv);
        final Student_ID_Downloader d=new Student_ID_Downloader(this,student_ID_url,lv);
        //final Student_Info_Downloader d2=new Student_Info_Downloader(this,student_info_url,lv);

     //   getUnpaidStudents get = new getUnpaidStudents(this);
      //  get.execute("getStudent_ID",Student_IDs.get(position));
    //    get.execute("getStudent_ID","555");


    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EXECUTE DOWNLOAD

            }
        });*/

        d.execute();
     //   d2.execute();
    }
}
