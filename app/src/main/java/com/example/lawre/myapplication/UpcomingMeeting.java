package com.example.lawre.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lawre.myapplication.databaseEntity.Downloader;
import com.example.lawre.myapplication.entity.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class UpcomingMeeting extends Fragment {



    ListView tt;

    private String UPLOAD_URL = "http://meetsapp.chinfinitecarworld.com/select.php";


    public UpcomingMeeting() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_upcoming_meeting, container, false);

        tt = (ListView) view.findViewById(R.id.list);
        final Downloader d = new Downloader(getActivity(),UPLOAD_URL,tt);
        d.execute();


        selectedItem();


        return view;
    }



    public void selectedItem(){
        tt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Meeting item = (Meeting) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity().getApplicationContext(),MeetingInfo.class);


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


                intent.putExtra("meetingid",item.getMeetingID());
                intent.putExtra("date",""+dateFormat.format(item.getDate().getTime()));
                intent.putExtra("time",""+timeFormat.format(item.getTime().getTime()));
                intent.putExtra("title",item.getTitle());
                intent.putExtra("Objective",item.getObjective());
                intent.putExtra("location",item.getVenue());
                startActivity(intent);

            }
       });

    }


    public void viewMeetingDetail(View v){

                Intent intent = new Intent(getActivity().getApplicationContext(), MeetingInfo.class);
                startActivity(intent);

    }
}
