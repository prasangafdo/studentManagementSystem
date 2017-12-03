package com.fdo.prasanga.studentmanagementsystem;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UnpaidStudentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid_student_info);

        TextView tv_pickupAddress, tv_deliveryAddress;

        tv_pickupAddress = (TextView) findViewById(R.id.tv_pickup_Address);
        tv_deliveryAddress = (TextView) findViewById(R.id.tv_delivery_Address);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);//Retrieving all saved data.
        String pickup_address = preferences.getString("pickup_address", null);
        String delivery_address = preferences.getString("delivery_address", null);//def is the default value

        tv_pickupAddress.setText(pickup_address);
        tv_deliveryAddress.setText(delivery_address);
    }
}
