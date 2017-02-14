package com.example.lawre.myapplication.databaseEntity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lawre.myapplication.entity.Meeting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 * Created by lawre on 12/29/2016.
 */

public class Parser extends AsyncTask<Void,Void,Boolean> {


    Context c;
    ListView lv;
    String data;
    Meeting meet;


    ArrayList<Meeting> meeting = new ArrayList<>();
    ProgressDialog pd;

    public Parser(ListView lv, String data, Context c) {

        this.lv = lv;
        this.data = data;
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing......Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        if(isParsed)
        {

            ArrayAdapter<Meeting> adapter= new ArrayAdapter<>(c,android.R.layout.simple_list_item_1,meeting);

            lv.setAdapter(adapter);

        }else
        {
            Toast.makeText(c,"Unable to Parse",Toast.LENGTH_SHORT).show();
        }

        pd.dismiss();
    }

    private Boolean parse()
    {


        try
        {
            //add that data to json array first
            JSONArray ja = new JSONArray(data);
            //create jo obj to hold a single item
            JSONObject jo=null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            GregorianCalendar date = new GregorianCalendar() ;
            GregorianCalendar time = new GregorianCalendar();



            meeting.clear();


            //loop thru array
            for(int i=0;i<ja.length();i++)
            {

                jo=ja.getJSONObject(i);

                //Retriove name
                String id = jo.getString("MeetingID");
                String title = jo.getString("Title");


                String dateString = jo.getString("Date");
                String timeString = jo.getString("Time");


               String objective = jo.getString("Objective");
                String location = jo.getString("Location");


                try {
                    Date d = dateFormat.parse(dateString);
                    date.setTime(d);

                    time = new GregorianCalendar();
                    time.setTime(timeFormat.parse(timeString));

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                 //System.out.println("ThomasLaw \nDate : "+dateFormat.format(date.getTime())+"\nTime : "+timeFormat.format(time.getTime()));

                meet = new Meeting(id,title,date,time,objective,location);

                //meet.setMeetingID(id);
               //meet.setTitle(title);
               // meet.setDate(date);
               // meet.setTime(time);
               // meet.setObjective(objective);
               // meet.setVenue(location);

                meeting.add(meet);





            }
            return true;

        }catch (JSONException e){
            e.printStackTrace();
        }

        return false;
    }

}
