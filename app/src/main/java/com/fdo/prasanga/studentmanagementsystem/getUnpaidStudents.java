package com.fdo.prasanga.studentmanagementsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 * Created by Prasanga Fernando on 12/2/2017.
 */

public class getUnpaidStudents extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    //Context c;
    // String address;
    ListView lv;

   // String setStudentID_url = "http://10.0.2.2/skyManagement/studentManagementSystem/getUnpaidStudentInfo.php";
        String setStudentID_url = "http://rapiddelivery.000webhostapp.com/skyManagement/studentManagementSystem/getUnpaidStudentInfo.php";

    getUnpaidStudents(Context ctx) {
        context = ctx;

    }

    public getUnpaidStudents(Context c, String setStudentID_url, ListView lv) {
        this.context = c;
        this.setStudentID_url = setStudentID_url;
        this.lv = lv;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];


        if (type.equals("setStudent_ID")) {
            try {
                // String student_ID = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                String student_ID = params[1];//remove this
                URL url = new URL(setStudentID_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("student_ID", "UTF-8") + "=" + URLEncoder.encode(student_ID, "UTF-8");//+"&"
                // +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                ///////////////////

                ////////////////
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                StringBuffer sb=new StringBuffer();
                if(bufferedReader != null) {
                    while ((line=bufferedReader.readLine()) != null) {
                        result = sb.append(line+"n").toString();
                    }
                }else {
                    return null;
                }//////////////////
                //Json array

                try {
                    //ADD THAT DATA TO JSON ARRAY FIRST
                    JSONArray ja = new JSONArray(result);
                    //CREATE JO OBJ TO HOLD A SINGLE ITEM
                    JSONObject jo = null;
                    //Student_IDs.clear();
                    //LOOP THRU ARRAY
                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        //RETRIEVE NAME
                        String first_name = jo.getString("first_Name");//This is the column name you want toi retrieve
                        String last_name = jo.getString("last_Name");
                        String address = jo.getString("user_Address");
                        String grade = jo.getString("grade");
                        String p_num = jo.getString("parent_Contact_Num");
                        String name = first_name+ " "+last_name;

                        //alertDialog.setMessage("Name: "+name+"\nAddress: "+address+"\nGrade: "+grade+"\nParent's contact number: "+p_num);
                        //alertDialog.show(); //Uncomment to get the result through am alert dialog

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", name);//Saving the extracted result from the database
                        editor.putString("address", address);
                        editor.putString("grade", grade);
                        editor.putString("p_num", p_num);
                        editor.apply();

                        Intent intent = new Intent(context, UnpaidStudentInfoActivity.class);
                        context.startActivity(intent);
/////////////////////////////////
                        //  Student_IDs.add(student_ID);//Adding name to the arraylist

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /////////////////
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
        //   String next_due_date = "nnnnnn";


        //  alertDialog.setMessage(result);



    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}