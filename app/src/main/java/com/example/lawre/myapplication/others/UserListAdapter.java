package com.example.lawre.myapplication.others;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lawre.myapplication.R;
import com.example.lawre.myapplication.entity.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by haoguang on 1/3/2017.
 */

public class UserListAdapter extends ArrayAdapter<User> {


    public UserListAdapter(Context context, ArrayList<User> users) {
        super(context, R.layout.user_basic_info, users);

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        View rowView=inflater.inflate(R.layout.user_basic_info, parent,false);

        User user = getItem(position);

        TextView txtName = (TextView) rowView.findViewById(R.id.userName);
        TextView txtId = (TextView) rowView.findViewById(R.id.userID) ;
        ImageView proPic = (ImageView) rowView.findViewById(R.id.profilePic);

        txtName.setText(user.getUsername());
        Picasso.with(getContext())
                .load(user.getProfilePic())
                .transform(new CircleTransform())
                .resize(200, 200)
                .centerInside()
                .into(proPic);
        txtId.setText(user.getUserID());
        return rowView;

    };

}
