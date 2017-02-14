package com.example.lawre.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.lawre.myapplication.others.IdGenerator;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by haoguang on 12/27/2016.
 */

public class User implements Parcelable {
    public static final String representor = "U";
    private static int userNo = 0;
    private String UserID;
    private String Username;
    private String PhoneNo;
    private String Email;
    private String ProfilePic;
    private char[] password;
    private ArrayList<User> friends;
    private ArrayList<Notification> notifications;

    public User(String userID, String username, String phoneNo, String email, char[] password) {

        UserID = userID;
        Username = username;
        PhoneNo = phoneNo;
        Email = email;
        this.password = password;
    }

    // Using the `in` variable, we can retrieve the values that
    // we originally wrote into the `Parcel`.  This constructor is usually
    // private so that only the `CREATOR` field can access.
    public User(Parcel in){
        UserID = in.readString();
        Username = in.readString();
        PhoneNo = in.readString();
        Email = in.readString();
        ProfilePic = in.readString();
    }

    // This is where you write the values you want to save to the `Parcel`.
    // The `Parcel` class has methods defined to help you save all of your values.
    // Note that there are only methods defined for simple values, lists, and other Parcelable objects.
    // You may need to make several classes Parcelable to send the data you want.
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(UserID);
        out.writeString(Username);
        out.writeString(PhoneNo);
        out.writeString(Email);
        out.writeString(ProfilePic);

    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public User(String userID, String username, String phoneNo, String email, String profilePic, char[] password) {
        UserID = userID;
        Username = username;
        PhoneNo = phoneNo;
        Email = email;
        ProfilePic = profilePic;
        this.password = password;
    }

    public User(String userID, String username, String phoneNo, String email, String profilePic, char[] password, ArrayList<User> friends, ArrayList<Notification> notifications) {

        UserID = userID;
        Username = username;
        PhoneNo = phoneNo;
        Email = email;
        ProfilePic = profilePic;
        this.password = password;
        this.friends = friends;
        this.notifications = notifications;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID='" + UserID + '\'' +
                ", Username='" + Username + '\'' +
                ", PhoneNo='" + PhoneNo + '\'' +
                ", Email='" + Email + '\'' +
                ", ProfilePic=" + ProfilePic +
                ", password=" + Arrays.toString(password) +
                '}';
    }
    public static String getNewID(){
        userNo++;
        return representor + IdGenerator.generateID(userNo);
    }
}
