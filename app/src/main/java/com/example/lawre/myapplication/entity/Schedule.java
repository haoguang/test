package com.example.lawre.myapplication.entity;

import com.example.lawre.myapplication.others.IdGenerator;

import java.util.GregorianCalendar;

/**
 * Created by haoguang on 12/27/2016.
 */

public class Schedule {
    public static final String representor = "S";
    private static int scheduleNo = 0;
    private String ScheeduleID;
    private GregorianCalendar time;
    private String task;

    @Override
    public String toString() {
        return "Schedule{" +
                "ScheeduleID='" + ScheeduleID + '\'' +
                ", time=" + time +
                ", task='" + task + '\'' +
                '}';
    }

    public String getScheeduleID() {
        return ScheeduleID;
    }

    public void setScheeduleID(String scheeduleID) {
        ScheeduleID = scheeduleID;
    }

    public GregorianCalendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Schedule(String scheeduleID, GregorianCalendar time, String task) {

        ScheeduleID = scheeduleID;
        this.time = time;
        this.task = task;
    }

    public static String getNewID(){
        scheduleNo++;
        return representor + IdGenerator.generateID(scheduleNo);
    }
}
