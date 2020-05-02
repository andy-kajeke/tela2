package com.planetsystems.tela.constants;

import static com.planetsystems.tela.activities.MainActivity.SchoolDeviceIMEINumber;

public class Urls {
    public static final String DEVICE_IMEI = "354633111523205";
    public static final String BASE_URL = "http://tela.planetsystems.co:8080/weca/webapi/attendance/";
    public static final String DEVICE_OWNERSHIP =  BASE_URL + "school/";
    public static final String SYNC_TEACHER_URL = BASE_URL + "teachers/" + DEVICE_IMEI;
    public static final String CLOCK_IN_UPLOAD_URL = BASE_URL + "clockin";
    public static final String CLOCK_OUT_UPLOAD_URL = BASE_URL + "clockout";
    public static final String SYNC_TIME_TABLE_URL = BASE_URL + "timetable/" + DEVICE_IMEI;
}
