package com.fdo.prasanga.studentmanagementsystem;

/**
 * Created by Prasanga Fernando on 12/2/2017.
 */

public class CalendarDate {

    private String year, month, day, selected_Date;
    public void setDate(String year, String month, String day){
        this.year = year;
        this.month = month;
        this.day = day;
        this.selected_Date = year+"-"+month+"-"+day;
    }

    public String getDate(){
        return selected_Date;
    }
}
