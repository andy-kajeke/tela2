package com.planetsystems.tela.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;
import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.currentLatitude;
import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.currentLongitude;

public class DynamicData {

    private static final String SCHOOL_ID = "com.planetsystems.tela.utils.DynamicData.SCHOOL_ID";
    public static String getSchoolID(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SCHOOL_ID, "");
    }

    public static double getLatitude(double latitude) {
        latitude = currentLatitude;
        return latitude;
    }

    public static double getLongitude(double longitude) {
        longitude = currentLongitude;
        return longitude;
    }

    public static String getDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        return sdf.format(d);
    }

    public static String getTime() {
        long date = System.currentTimeMillis();
        SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
        return  time.format(date);
    }

    public static String getDate() {
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        return  dateFormat.format(date);
    }

    public static String getSchoolName() {
        return "Buganda Road";
    }

    public static void setSchoolID(String phoneNumber, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SCHOOL_ID, phoneNumber);
        editor.apply();
    }
}
