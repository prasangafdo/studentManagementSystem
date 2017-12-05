package com.fdo.prasanga.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText et_FirstName, et_LastName, et_Address, et_DateofBirth, et_Grade, et_ParentNum;
    Button btn_RegisterStudent, btn_calFees;
    CheckBox ck_RegistrationFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  toolbar.setTitle("Student Registration");
      //  setSupportActionBar(toolbar);

        et_FirstName =  (EditText) findViewById(R.id.et_FirstName);
        et_LastName =  (EditText) findViewById(R.id.et_LastName);
        et_Address =  (EditText) findViewById(R.id.et_Address);
        et_DateofBirth =  (EditText) findViewById(R.id.et_DateofBirth);
        et_Grade =  (EditText) findViewById(R.id.et_Grade);
        et_ParentNum =  (EditText) findViewById(R.id.et_ParentNum);
        ck_RegistrationFee = (CheckBox) findViewById(R.id.ck_RegistrationFee);
        btn_RegisterStudent = (Button) findViewById(R.id.btn_CompleteRegistration);
        btn_calFees = (Button) findViewById(R.id.btn_calFees);

        btn_RegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ck_RegistrationFee.isChecked()){
                    //Do registration stuff
                    String type = "registerStudent";
                    String firstName = et_FirstName.getText().toString();
                    String lastName = et_LastName.getText().toString();
                    String address = et_Address.getText().toString();
                    String dateofBirth = et_DateofBirth.getText().toString();
                    String grade = et_Grade.getText().toString();
                    String parentNum = et_ParentNum.getText().toString();

                    BackgroundWorker backgroundWorker = new BackgroundWorker(RegisterActivity.this);
                    backgroundWorker.execute(type, firstName, lastName, address, dateofBirth, grade, parentNum);

                    et_FirstName.setText("");
                    et_LastName.setText("");
                    et_Address.setText("");
                    et_DateofBirth.setText("");
                    et_Grade.setText("");
                    et_ParentNum.setText("");
                }
                else{
                    //AlertDialog alertDialog = new android.app.AlertDialog.Builder().create();
                    //Toast toast = new Toast(R)
                }
            }
        });

        btn_calFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, FeesActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
        startActivity(intent);//Going back to menu on back button press
        finish();
    }
}
