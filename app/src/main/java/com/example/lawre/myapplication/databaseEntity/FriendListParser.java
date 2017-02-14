package com.example.lawre.myapplication.databaseEntity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;
import com.example.lawre.myapplication.entity.User;
import com.example.lawre.myapplication.others.UserListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by haoguang on 1/4/2017.
 */

public class FriendListParser extends AsyncTask<Void,Void,Boolean> {

    Context c;
    ListView lv;
    String data;
    User user;


    ArrayList<User> friends = new ArrayList<>();
    ProgressDialog pd;

    public FriendListParser(ListView lv, String data, Context c) {

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

            UserListAdapter adapter= new UserListAdapter(c,friends);

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




            friends.clear();


            //loop thru array
            for(int i=0;i<ja.length();i++)
            {

                jo=ja.getJSONObject(i);

                //Retriove name
                String id = jo.getString("userID");
                String name = jo.getString("username");
                String profilePic = jo.getString("profilePic");


                user = new User(id,name,null, null, profilePic,null);
                friends.add(user);





            }
            return true;

        }catch (JSONException e){
            e.printStackTrace();
        }

        return false;
    }
}
