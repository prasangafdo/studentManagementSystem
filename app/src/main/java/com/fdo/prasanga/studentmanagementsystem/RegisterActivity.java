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
                    //ck_RegistrationFee.

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
    /*    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this);//Retrieving all saved data.
        String first_name = preferences.getString("first_name", null);
        String last_name = preferences.getString("last_name", null);
        String address = preferences.getString("address", null);
        String dateOfBirth = preferences.getString("dateOfBirth", null);
        String grade = preferences.getString("grade", null);
        String parentNum = preferences.getString("parentNum", null);

        boolean saveData = preferences.getBoolean("saveData", false);//If not found, it's false
        //Restoring the filled information

        if (saveData){

            et_FirstName.setText(valueOf(saveData));
            et_LastName.setText(last_name);
            et_Address.setText(address);
            et_DateofBirth.setText(dateOfBirth);
            et_Grade.setText(grade);
            et_ParentNum.setText(parentNum);
            ck_RegistrationFee.isChecked();

            SharedPreferences.Editor editor = preferences.edit();

            editor.clear();//removing saved data from the memory
            editor.apply();
            editor.putBoolean("saveData", false);//Save data is now cleared.
        }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
        startActivity(intent);//Going back to menu on back button press
        finish();
    }
}
