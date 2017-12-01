package com.fdo.prasanga.studentmanagementsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

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

/**
 * Created by prasanga on 3/17/17.
 */
public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String ... params) {
        String type = params[0];

        //String login_url = "http://10.0.2.2/Prasanga/newDB/login.php";//Uncomment these if you're using in localhost
        String login_url = "http://10.0.2.2/skyManagement/studentManagementSystem/login.php";


        String registerStudent_url = "http://10.0.2.2/skyManagement/studentManagementSystem/registerStudent.php";
        String getGrade_url = "http://10.0.2.2/skyManagement/studentManagementSystem/selectGrade.php";
        String switchVehicle_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/switchVehicle.php";
        String transferParcel_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/transferParcel.php";
        String completeDelivery_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/completeDelivery.php";
        String userID_url = "https://rapiddelivery.000webhostapp.com/MobilePhp/userid.php";

        if (type.equals("Login")) { //Login
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
              //  String vID = params[1];
              //  String latitude = params[2];
               // String longitude = params[3];

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

      else  if (type.equals("getGrade")) { //Switch the courier vehicle
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


         else if (type.equals("TransferParcel")){//transfer a parcel to another vehicle
                try {
                    String courierID = params[1];
                    String vehicleID = params[2];
                    String parcelID = params[3];


                    URL url = new URL(transferParcel_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream , "UTF-8"));
                    String post_data = URLEncoder.encode("vehicleID","UTF-8")+"="+URLEncoder.encode(vehicleID,"UTF-8")+"&"
                            +URLEncoder.encode("courierID","UTF-8")+"="+URLEncoder.encode(courierID,"UTF-8")+"&"
                            +URLEncoder.encode("parcelID","UTF-8")+"="+URLEncoder.encode(parcelID,"UTF-8");
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
          if(result.equals("Login success!")){ //If login is success
             alertDialog.setMessage(result);
             alertDialog.show();

            Intent intent = new Intent(context, MenuActivity.class);//Forwarding back to menu
            alertDialog.setMessage(result);
            alertDialog.show();
            context.startActivity(intent);
            ((Activity)context).finish();
       }
        else {//Registration possibilities need to be considered
              String grade = result.replaceAll("[^A-Z]", "");//Checking whether the echo sends any numbers. If it contains any numbers, it's not "Grade"

              if (grade.equals("")) {  //It has no letters.
                  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context );
                  SharedPreferences.Editor editor= sharedPreferences.edit();
                  editor.putString("grade", result);//Saving the extracted result from the database
                  editor.apply();
                  Intent intent = new Intent(context, FeesActivity.class);//Currently calender is under Fees class
                  context.startActivity(intent);

 /*                 Intent intent = new Intent(context, FeesActivity.class);//Currently calender is under Fees class
                  intent.putExtra("grade", result);//Passing selected Date to the previous view
                  //intent.putExtra("fee", "12332");//Do the math here
                  context.startActivity(intent);
                  //context.finish();
*/
                 // alertDialog.setMessage(result);//temp
                 // alertDialog.show();
              }
            //  alertDialog.show();
           //   alertDialog.setMessage(student_ID);
            //  alertDialog.show();
          }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }



}


