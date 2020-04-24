package com.planetsystems.tela.constants;

public class Urls {
    public static final String DEVICE_IMEI = "354633111523205";
    public static final String BASE_URL = "http://tela.planetsystems.co:8080/weca/webapi/attendance/";
    public static final String DEVICE_OWNERSHIP =  BASE_URL + "school/";
    public static final String ALL_TEACHERS_IN_SCHOOL = BASE_URL + "teachers/";
    public static final String SYNC_TEACHER_URL = BASE_URL + "teachers/" + DEVICE_IMEI;
    public static final String CLOCK_IN = BASE_URL + "clockin";
    public static final String CLOCK_OUT = BASE_URL + "clockout";
    public static final String TIMETABLE = BASE_URL + "timetable/" + DEVICE_IMEI;
}
