package com.example.lawre.myapplication.loginModule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lawre.myapplication.MeetingApp;
import com.example.lawre.myapplication.R;
import com.example.lawre.myapplication.entity.User;
import com.example.lawre.myapplication.others.SessionManagement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private SessionManagement session;
    private EditText userField ;
    private EditText passField;
    private final String requestURL = "http://meetsapp.chinfinitecarworld.com/loginDA/login.php";//Server url

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManagement(getApplicationContext());



            userField = (EditText) findViewById(R.id.txtUser);
            userField.requestFocus();
            passField = (EditText) findViewById(R.id.txtPassword);

            userField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (userField.getText().toString().matches("")) {
                        userField.setError("Please fill in your email or phone number..");
                    }
                }
            });

            passField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (passField.getText().toString().matches("")) {
                        passField.setError("Please fill in your password.");
                    }
                }
            });

    }




    public void loginAction(View view){
        userField = (EditText) findViewById(R.id.txtUser);
        passField = (EditText) findViewById(R.id.txtPassword);

        String email = userField.getText().toString();
        char[] password = passField.getText().toString().toCharArray();

        if(email.equals("")|| password.length<6){
            Toast toast = Toast.makeText(getApplicationContext(), R.string.error_field_empty, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            requestToServer(email, password);
        }






    }

    private void startMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MeetingApp.class);
        //Closing all the Activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Add new Flage to start new Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Starting login Activity
        startActivity(intent);
    }

    private void setUserState(User user){
        session.createLoginSession(user);
        startMainActivity();
    }

    private void requestToServer(final String email,final char[] password){



        final ProgressDialog loading = ProgressDialog.show(this, "Loging in", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestURL, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                try {
                    loading.getProgress();
                    loading.dismiss();
                    JSONObject responseMsg = new JSONObject(s);//get the json object from server

                    int success = responseMsg.getInt("success");
                    String msg = responseMsg.getString("message");
                    if(success == 1) {
                        String id = responseMsg.getString("userid");
                        String name = responseMsg.getString("username");
                        String phone = responseMsg.getString("phone");
                        String email = responseMsg.getString("email");
                        String profilePic = responseMsg.getString("profilePic");

                        User user = new User(id, name, phone, email, profilePic,null);
                        Toast.makeText(LoginActivity.this, "Successful logged in!!", Toast.LENGTH_SHORT).show();
                        setUserState(user);



                    }else
                        Toast.makeText(LoginActivity.this, "Login is not successful due to " + msg , Toast.LENGTH_SHORT).show();
                }catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Login is not successful due to Server " + e.getMessage() , Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<>();

                //Adding parameters
                params.put("Username",email);
                params.put("Password", new String(password));

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    public void createAccAction(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
