package com.example.lawre.myapplication.FriendManagement;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.example.lawre.myapplication.R;
import com.example.lawre.myapplication.databaseEntity.FriendListDownloader;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendListFragment extends Fragment {

    ListView list;
    private final String requestURL = "http://meetsapp.chinfinitecarworld.com/loginDA/getFriendList.php";

    public FriendListFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //get view to enable us to get list from the layout
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);
        list=(ListView)view.findViewById(R.id.friendListView);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String id = getArguments().getString("userID");



        final FriendListDownloader d = new FriendListDownloader(getActivity(),requestURL,id,list);
        d.execute();


    }

}
