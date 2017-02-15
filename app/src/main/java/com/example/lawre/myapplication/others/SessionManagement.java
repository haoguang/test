package com.example.lawre.myapplication.others;

/**
 * Created by haoguang on 1/2/2017.
 */
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.lawre.myapplication.entity.User;
import com.example.lawre.myapplication.loginModule.LoginActivity;

public class SessionManagement {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "MeetsAppPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    //User information (make variable public to access from outside)
    public static final String KEY_USERID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PIC = "profilePic";

    // Constructor
    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //create user login session
    public void createLoginSession(User user){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        //storing user information to pref
        editor.putString(KEY_USERID, user.getUserID());
        editor.putString(KEY_NAME,user.getUsername());
        editor.putString(KEY_PHONE, user.getPhoneNo());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PIC, user.getProfilePic());
        // commit changes
        editor.commit();
    }

    /** check login status, if then user is not logged in,
     * it will redirect user to login page
     * else won't do anything
     */
    public void checkLogin(){
        //Check login status
        if(!this.isLoggedIn()){
            Intent intent = new Intent(_context, LoginActivity.class);
            //Closing all the Activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //Add new Flage to start new Activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Starting login Activity
            _context.startActivity(intent);
        System.out.println("Hello world");
            System.out.println("This is hao guang.");
        }
    }

    /**
     * Get stored session data and return as User type
     **/
    public User getUserDetails(){

        String id = pref.getString(KEY_USERID,null);
        String name = pref.getString(KEY_NAME, null);
        String phone = pref.getString(KEY_PHONE, null);
        String email = pref.getString(KEY_EMAIL, null);
        String profilePic = pref.getString(KEY_PIC, null);

        return new User(id, name, phone, email, profilePic, null);
    }

    /**
     * Clear session details
     **/
    public void logoutUser(){
        //clearing all data from shared preferences
        editor.clear();
        editor.commit();

        //After logout redirect user to login activity
        Intent intent = new Intent(_context, LoginActivity.class);
        //Closing all activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //Add new Flag to start new Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Starting login activity
        _context.startActivity(intent);
    }

    //check login status
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


}
