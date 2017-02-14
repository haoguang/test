package com.example.lawre.myapplication.entity;

import com.example.lawre.myapplication.others.IdGenerator;

/**
 * Created by haoguang on 12/27/2016.
 */

public class Notification {
    //representation for type and status
    public static final char USER_NOTIFICATION = 'u';
    public static final char MEETING_NOTIFICATION = 'm';
    public static final char PENDING = 'p';
    public static final char ACCEPTED = 'a';
    public static final char IGNORED = 'i';
    //variable declaration
    public static final String representor = "N";
    private static int notificationNo = 0;
    private String notificationID;
    private char type;
    private String remark;
    private char status;
    private String reference; //userID
    private String userID;

    public Notification(String notificationID, char type, String remark, char status, String reference, String userID) {
        this.notificationID = notificationID;
        this.type = type;
        this.remark = remark;
        this.status = status;
        this.reference = reference;
        this.userID = userID;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Notification: " + '\n' +
                "notificationID= " + notificationID + '\n' +
                "type(u-User, m-Meeting)= " + type + '\n';
    }

    public static String getNewID(){
        notificationNo++;
        return representor + IdGenerator.generateID(notificationNo);
    }
}
