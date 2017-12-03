package com.fdo.prasanga.studentmanagementsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Prasanga Fernando on 12/2/2017.
 */

public class getUnpaidStudents extends AsyncTask<String, Void, String>{
    Context context;
    AlertDialog alertDialog;

    getUnpaidStudents(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String ... params) {
        String type = params[0];

        //String login_url = "http://10.0.2.2/Prasanga/newDB/login.php";//Uncomment these if you're using in localhost
        String getUnpaidStudentID_url = "http://10.0.2.2/skyManagement/studentManagementSystem/getUnpaidStudentID.php";

        //Remove the rest
        String registerStudent_url = "http://10.0.2.2/skyManagement/studentManagementSystem/registerStudent.php";
        String getGrade_url = "http://10.0.2.2/skyManagement/studentManagementSystem/selectGrade.php";
        String updateFees_url = "http://10.0.2.2/skyManagement/studentManagementSystem/updateFees.php";

        String transferParcel_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/transferParcel.php";
        String completeDelivery_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/completeDelivery.php";
        String userID_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/userid.php";

        if (type.equals("getStudent_ID")) {
            try {
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

              //  String password = "temp";//remove this
                URL url = new URL(getUnpaidStudentID_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                String post_data = URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");//+"&"
                      //  +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result= "";
                String line= "";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (type.equals("registerStudent")){// Registering a student
            try {

                String firstName = params[1];
                String lastName = params[2];
                String address = params[3];
                String dateofBirth = params[4];
                String grade = params[5];
                String parentNum = params[6];


                URL url = new URL(registerStudent_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");//Using POST method
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                String post_data = URLEncoder.encode("firstName","UTF-8")+"="+URLEncoder.encode(firstName,"UTF-8")+"&"
                        +URLEncoder.encode("lastName","UTF-8")+"="+URLEncoder.encode(lastName,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                        +URLEncoder.encode("dateofBirth","UTF-8")+"="+URLEncoder.encode(dateofBirth,"UTF-8")+"&"
                        +URLEncoder.encode("grade","UTF-8")+"="+URLEncoder.encode(grade,"UTF-8")+"&"
                        +URLEncoder.encode("parentNum","UTF-8")+"="+URLEncoder.encode(parentNum,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();//Sending data to the hosted php file
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result= "";
                String line= "";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else  if (type.equals("getGrade")) { //Getting grade from the student table
            try {
                String studentID = params[1];

                URL url = new URL(getGrade_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                String post_data = URLEncoder.encode("studentID","UTF-8")+"="+URLEncoder.encode(studentID,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result= "";
                String line= "";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        else if (type.equals("updateFees")){//Update Fees table
            try {
                String studentID = params[1];
                String next_Date = params[2];
                String current_Date = params[3];
                String fee = params[4];

                URL url = new URL(updateFees_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                String post_data = URLEncoder.encode("studentID","UTF-8")+"="+URLEncoder.encode(studentID,"UTF-8")+"&"
                        +URLEncoder.encode("next_Date","UTF-8")+"="+URLEncoder.encode(next_Date,"UTF-8")+"&"
                        +URLEncoder.encode("fee","UTF-8")+"="+URLEncoder.encode(fee,"UTF-8")+"&"
                        +URLEncoder.encode("current_Date","UTF-8")+"="+URLEncoder.encode(current_Date,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result= "";
                String line= "";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        else if (type.equals("completeDelivery")){//Complete Delivery
            try {
                String parcelID = params[1];

                URL url = new URL(completeDelivery_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                String post_data = URLEncoder.encode("parcelID","UTF-8")+"="+URLEncoder.encode(parcelID,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result= "";
                String line= "";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    public void onPostExecute(String result) {
       alertDialog.setMessage(result);
        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}
