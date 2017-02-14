package com.example.lawre.myapplication.loginModule;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;

import android.util.Base64;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lawre.myapplication.R;
import com.example.lawre.myapplication.entity.User;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private CheckBox chkShowPwd;
    private EditText txtPassword;
    private EditText txtUser;
    private EditText txtEmail;
    private EditText txtPhone;
    private final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final String requestURL = "http://meetsapp.chinfinitecarworld.com/loginDA/registerMember.php";//Server url
    private final int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private ImageView profilePic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUser = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        profilePic = (ImageView) findViewById(R.id.imagePreview);

        txtUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(txtUser.getText().toString().matches("")) {
                    txtUser.setError("Please fill in your name.");
                }
            }
        });

        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(txtEmail.getText().toString().matches("")){
                    txtEmail.setError("Please fill in your email.");
                }else if(!txtEmail.getText().toString().matches(emailRegex)){
                    txtEmail.setError("Your email format is wrong.");
                }
            }
        });

        txtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(txtPhone.getText().toString().matches("")) {
                    txtPhone.setError("Please fill in your phone number");
                }else if(!txtPhone.getText().toString().matches("^[0-9]+$")){
                    txtPhone.setError("Please fill in your phone number with digit only.");
                }
            }
        });

        txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(txtPassword.getText().toString().toCharArray().length < 6) {
                    txtPassword.setError("Please key in a password more than 6 character.");
                }
            }
        });

    }

    public void chooseImage(View view){
        //to choose image
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"), PICK_IMAGE_REQUEST);
    }

    public String getImageString(Bitmap bmp){
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
        byte[] imageBytes = byteArray.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri filePath = data.getData();
            try{
                //Getting bitmap from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting the bitmap to imageview
                profilePic.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void register(View view){
        //find view
        txtUser = (EditText)  findViewById(R.id.txtName);
        txtEmail = (EditText)  findViewById(R.id.txtEmail);
        txtPhone = (EditText)  findViewById(R.id.txtPhone);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        String name = txtUser.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        char[] password = txtPassword.getText().toString().toCharArray();

        if(name.equals("")|| email.equals("")||phone.equals("")||password.length<6){
            Toast toast = Toast.makeText(getApplicationContext(), R.string.error_field_empty, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            User user = new User(User.getNewID(),name,phone,email,password);
            sendToServer(user);
        }


    }

    private void sendToServer(final User user){

        final ProgressDialog loading = ProgressDialog.show(this, "Creating User", "Please wait...", false, false);
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
                        Toast.makeText(RegisterActivity.this, "Successful registered!!", Toast.LENGTH_SHORT).show();
                        finish();//end this activity and go back to the previous activity.
                    }else
                        Toast.makeText(RegisterActivity.this, "Registration is not successful due to " + msg , Toast.LENGTH_SHORT).show();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String imgString = getImageString(bitmap);
                Map<String, String> params = new Hashtable<>();

                //Adding parameters
                params.put("Id", user.getUserID());
                params.put("Name", user.getUsername());
                params.put("PhoneNo", user.getPhoneNo());
                params.put("Email", user.getEmail());
                params.put("Password", new String(user.getPassword()));
                params.put("ProfilePic", imgString);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void showPassword(View view){
        chkShowPwd = (CheckBox) findViewById(R.id.chkShowPwd);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        if(chkShowPwd.isChecked()){
            txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }


}
