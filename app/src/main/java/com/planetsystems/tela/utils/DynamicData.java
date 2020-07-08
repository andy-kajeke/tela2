package com.planetsystems.tela.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.planetsystems.tela.activities.mainActivity.MainActivity.SchoolDeviceIMEINumber;

public class DynamicData {
    public static String getSchoolID(String schoolDevice) {
        //TODO: put codes here for finding school id
        schoolDevice = SchoolDeviceIMEINumber;
        return schoolDevice;
    }

    public static String getLatitude() {
        return "354633111523205";
    }

    public static String getLongitude() {
        return "77887766";
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
