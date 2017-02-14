package com.example.lawre.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lawre.myapplication.entity.Notification;

import java.util.HashMap;
import java.util.Map;

public class NotificationDisplay extends AppCompatActivity {

    String id1, remark1, userID1, reference1;
    char type1, status1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_display);

        TextView notiID = (TextView) findViewById(R.id.noticeID);
        TextView type = (TextView) findViewById(R.id.noticeType);
        TextView remark = (TextView) findViewById(R.id.noticeRemark);
        TextView status = (TextView) findViewById(R.id.noticeStatus);

        Intent in = this.getIntent();
        id1 = in.getStringExtra("notificationID");
        type1 = in.getCharExtra("type", (char) 0 );
        remark1 = in.getStringExtra("remark");
        status1 = in.getCharExtra("status", (char) 0 );
        userID1 = in.getStringExtra("userID");
        reference1 = in.getStringExtra("reference");

        String statuss ="";
        String typetype ="";

        if(status1=='p'){
            statuss = "pending";
        }

        if(type1=='u'){
            typetype ="User";

        }else if(type1=='m'){
            typetype ="Meeting";
        }

        notiID.setText(id1);
        type.setText(typetype);
        remark.setText(remark1);
        status.setText(statuss);

    }

    public void attend(View view){

        char status = 'a';
        Notification attend = new Notification(id1, type1, remark1, status, reference1, userID1);
        String url = getApplicationContext().getString(R.string.attendURL);
        makeServiceCall(this, url, attend);
        finish();
    }

    public void notAttend(View view){
        char status = 'i';
        Notification notAttend = new Notification(id1, type1, remark1, status, reference1, userID1);
        String url = getApplicationContext().getString(R.string.attendURL);
        makeServiceCall(this, url, notAttend);
        finish();
    }

    public void makeServiceCall(Context context, String url, final Notification notice){
        RequestQueue queue = Volley.newRequestQueue(context);
        //Send data
        try {
            StringRequest postRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //System.out.println(notice.getNotificationID());
                            Toast.makeText(getApplicationContext(), "Record saved." + response, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error." + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("NotificationID", notice.getNotificationID());
                    //System.out.println(notice.getNotificationID());

                    params.put("Type", notice.getType()+"");
                    //System.out.println(notice.getType()+"");

                    params.put("Remark", notice.getRemark());
                    //System.out.println(notice.getRemark());

                    params.put("Status", notice.getStatus()+"");
                    //System.out.println(notice.getStatus()+"");

                    params.put("Reference", notice.getReference());
                    //System.out.println(notice.getReference());

                    params.put("UserID", notice.getUserID());
                   // System.out.println(notice.getUserID());
                    return params;
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

