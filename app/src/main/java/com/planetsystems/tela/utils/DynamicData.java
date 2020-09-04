package com.planetsystems.tela.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;
import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.currentLatitude;
import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.currentLongitude;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.mSharedPreferences;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.school_Device_Number;

public class DynamicData {

    public static String getSchoolID(String schoolID) {
        schoolID = mSharedPreferences.getString(school_Device_Number, "");
        return schoolID;
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

}
