package com.planetsystems.tela.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;
import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.currentLatitude;
import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.currentLongitude;

public class DynamicData {
    public static String getSchoolID(String schoolDevice) {
        //TODO: put codes here for finding school id
        schoolDevice = SchoolDeviceIMEINumber;
        return schoolDevice;
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
}
