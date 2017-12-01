package com.fdo.prasanga.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FeesActivity extends AppCompatActivity {
    Button btn_getDate;
    TextView tv_CurrentDate, tv_DateOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees);



        btn_getDate = (Button) findViewById(R.id.btn_getDate);
        tv_CurrentDate = (TextView) findViewById(R.id.tv_CurrentDate);
        tv_DateOutput = (TextView) findViewById(R.id.tv_DateOutput);
       // ck_RegistrationFee = (CheckBox) findViewById(R.id.ck_RegistrationFee);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        tv_CurrentDate.setText(date);//Setting the current date

                btn_getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FeesActivity.this, CalenderActivity.class);
                startActivity(intent);
/*)
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FeesActivity.this);//Saving all filled data.
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("first_name",et_FirstName.getText().toString());
                editor.putString("last_name",et_LastName.getText().toString());
                editor.putString("address",et_Address.getText().toString());
                editor.putString("dateOfBirth",et_DateofBirth.getText().toString());
                editor.putString("grade",et_Grade.getText().toString());
                editor.putString("parentNum",et_ParentNum.getText().toString());
                editor.putBoolean("saveData", true);//Data is now saved
                editor.apply();

                ck_RegistrationFee.isChecked();//Checkbox is checked
                */
            }
        });

        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("selectedDate");
        tv_DateOutput.setText(selectedDate);

    }
}
