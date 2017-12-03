package com.fdo.prasanga.studentmanagementsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class UnpaidStudentsActivity extends AppCompatActivity {

    String url="http://10.0.2.2/skyManagement/studentManagementSystem/getUnpaidStudentID.php";

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
        final Downloader d=new Downloader(this,url,lv);


    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EXECUTE DOWNLOAD

            }
        });*/

        d.execute();
    }
}
