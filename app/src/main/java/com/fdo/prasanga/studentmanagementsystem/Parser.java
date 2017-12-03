package com.fdo.prasanga.studentmanagementsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class Parser extends AsyncTask<Void,Integer,Integer> {
    Context c;
    ListView lv;
    String data;
    ArrayList<String> players=new ArrayList<>();
    ProgressDialog pd;
    public Parser(Context c, String data, ListView lv) {
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
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,players);
            //ADAPT TO LISTVIEW
            lv.setAdapter(adapter);
            //LISTENET
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Snackbar.make(view,players.get(position),Snackbar.LENGTH_SHORT).show();;

                    Intent intent = new Intent(c,UnpaidStudentInfoActivity.class);
                   c.startActivity(intent);



                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);//Retrieving all saved data.
                    String pickup_address = preferences.getString("pickup_address", null);
                    String delivery_address = preferences.getString("delivery_address", null);//def is the default value
                    /////////////////////////

                    Toast.makeText(c,pickup_address, Toast.LENGTH_SHORT).show();

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
        {
            //ADD THAT DATA TO JSON ARRAY FIRST
            JSONArray ja=new JSONArray(data);
            //CREATE JO OBJ TO HOLD A SINGLE ITEM
            JSONObject jo=null;
            players.clear();
            //LOOP THRU ARRAY
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                //RETRIOEVE NAME
                String name=jo.getString("student_ID");//This is the column name you want toi retrieve
              //  String name2=jo.getString("delivery_address");
/////////////////////////////////
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("pickup_address", name);//Saving the extracted result from the database
             //   editor.putString("delivery_address", name2);
                editor.apply();
/////////////////////////////////
                players.add(name);//Adding it to the arraylist
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}