package com.fdo.prasanga.studentmanagementsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Prasanga Fernando on 12/3/2017.
 */

public class Student_ID_Parser extends AsyncTask<Void,Integer,Integer> {
    Context c;
    ListView lv;
    String data;
    ArrayList<String> Student_Info=new ArrayList<>();
    ProgressDialog pd;
    public Student_ID_Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.data = data;
        this.lv = lv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing ....Please wait");
        pd.show();
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer == 1)
        {
            //ADAPTER
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,Student_Info);
            //ADAPT TO LISTVIEW
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    getUnpaidStudents getUnpaidStudents = new getUnpaidStudents(c);
                    getUnpaidStudents.execute("setStudent_ID", Student_Info.get(position));
                }
            });
        }else
        {
            Toast.makeText(c,"Unable to Parse", Toast.LENGTH_SHORT).show();
        }
        pd.dismiss();
    }
    //PARSE RECEIVED DATA
    private int parse()
    {
        try
        {   //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja=new JSONArray(data);
            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo=null;
            Student_Info.clear();
            //LOOP THROUGH ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                //RETRIEVE NAME
                String student_ID=jo.getString("student_ID");//This is the column name you want toi retrieve
                String next_due_date=jo.getString("next_due_date");

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                 editor.putString("student_ID", student_ID);//Saving the extracted result from the database
                editor.apply();
                Student_Info.add(student_ID);//Adding name to the arraylist
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}