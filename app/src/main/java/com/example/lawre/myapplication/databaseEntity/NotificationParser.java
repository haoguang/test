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

        import com.example.lawre.myapplication.entity.Notification;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;


/**
 * Created by lawre on 12/29/2016.
 */

public class NotificationParser extends AsyncTask<Void,Void,Boolean> {


    Context c;
    ListView lv;
    String data;
    Notification notice;


    ArrayList<Notification> notification = new ArrayList<>();
    ProgressDialog pd;

    public NotificationParser(ListView lv, String data, Context c) {

        this.lv = lv;
        this.data = data;
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Notification Parser");
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

            ArrayAdapter<Notification> adapter= new ArrayAdapter<>(c,android.R.layout.simple_list_item_1,notification);

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

            notification.clear();


            //loop thru array
            for(int i=0;i<ja.length();i++)
            {

                jo=ja.getJSONObject(i);

                //Retrieve name
                String id = jo.getString("NotificationID");
                String type = jo.getString("Type");
                String remark = jo.getString("Remark");
                String status = jo.getString("Status");
                String reference = jo.getString("Reference");
                String userID = jo.getString("UserID");

                char type1 = type.charAt(0);
                char status1 = status.charAt(0);

                notice = new Notification(id ,type1, remark, status1, reference, userID);

                notification.add(notice);

            }
            return true;

        }catch (JSONException e){
            e.printStackTrace();
        }

        return false;
    }

}
