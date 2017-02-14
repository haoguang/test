package com.example.lawre.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lawre.myapplication.databaseEntity.Downloader1;
import com.example.lawre.myapplication.entity.Notification;

import java.util.ArrayList;
import java.util.List;

import static com.example.lawre.myapplication.R.layout.notification_detail;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationActivity extends Fragment {

    ListView list1;
    private String UPLOAD_URL = "http://meetsapp.chinfinitecarworld.com/notification.php";


    public NotificationActivity() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

        //Notification

        list1 = (ListView) rootView.findViewById(R.id.list2);
        final Downloader1 d = new Downloader1(getActivity(), UPLOAD_URL, list1);
        d.execute();

        selectedItem();

        return rootView;
    }

    public void selectedItem(){
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notification item = (Notification) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity().getApplicationContext(),NotificationDisplay.class);

                intent.putExtra("notificationID",item.getNotificationID());
                intent.putExtra("type",item.getType());
                intent.putExtra("remark",item.getRemark());
                intent.putExtra("status",item.getStatus());
                intent.putExtra("userID",item.getUserID());
                intent.putExtra("reference",item.getReference());
                startActivity(intent);

            }
        });

    }

    public void viewNotificationDetail(View v){

        Intent intent = new Intent(getActivity().getApplicationContext(), NotificationDisplay.class);
        startActivity(intent);

    }

}
