package com.example.lawre.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.lawre.myapplication.others.IdGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by haoguang on 12/27/2016.
 */

public class Meeting{
    public static final String representor = "M";
    private static int meetingNo = 1000;
    private String meetingID;
    private String title;
    private GregorianCalendar date;
    private GregorianCalendar time;
    private String Objective;
    private String venue;
    private ArrayList<MeetingMember> members;
    private ArrayList<Schedule> schedules;
    private ArrayList<Document> Documents;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");







    public Meeting(String meetingID, String title, GregorianCalendar date, GregorianCalendar time, String objective,String venue, ArrayList<MeetingMember> members, ArrayList<Schedule> schedules, ArrayList<Document> documents) {
        this.meetingID = meetingID;
        this.title = title;
        this.date = date;
        this.time = time;
        Objective = objective;
        this.venue = venue;
        this.members = members;
        this.schedules = schedules;
        Documents = documents;
    }

    public Meeting(String meetingID, String title, GregorianCalendar date, GregorianCalendar time, String objective, String venue) {
        this.meetingID = meetingID;
        this.title = title;
        this.date = date;
        this.time = time;
        Objective = objective;
        this.venue = venue;
    }


    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public GregorianCalendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getObjective() {
        return Objective;
    }

    public void setObjective(String objective) {
        Objective = objective;
    }

    public ArrayList<MeetingMember> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<MeetingMember> members) {
        this.members = members;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Document> getDocuments() {
        return Documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        Documents = documents;
    }





    @Override
    public String toString() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tf=new SimpleDateFormat("HH:mm:ss");
        return
                //"meetingID='" + meetingID + '\'' +
                "Title = '" + title + '\'' +
                "\nDate = " + sdf.format(date.getTime())+
                "\nTime = " + tf.format(time.getTime()) +
                "\nVenue = '" + venue + '\'';
               // ", Objective='" + Objective;
    }



    public static String getNewID(){
        meetingNo++;
        return representor + IdGenerator.generateID(meetingNo);
    }
}
