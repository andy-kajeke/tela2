package com.planetsystems.tela.constants;

public class Urls {
    public static final String DEVICE_IMEI = "354633111523205";
    public static final String BASE_URL = "http://tela.planetsystems.co:8080/weca/webapi/";
    public static final String DEVICE_OWNERSHIP =  BASE_URL + "attendance/school/";
    public static final String ALL_TEACHERS_IN_SCHOOL = BASE_URL + "attendance/teachers/";
    public static final String SYNC_TEACHER_URL = BASE_URL + "attendance/teachers/" + DEVICE_IMEI;
}
