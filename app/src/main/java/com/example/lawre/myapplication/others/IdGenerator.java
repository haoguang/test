package com.example.lawre.myapplication.others;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by haoguang on 12/27/2016.
 */

public class IdGenerator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("mmssHHMMddyy");
    public static String generateID(int number){
        GregorianCalendar today = (GregorianCalendar)GregorianCalendar.getInstance();
        return String.format("%s%05d",dateFormat.format(today.getTime()),number);
    }
}
