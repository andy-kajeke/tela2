package com.planetsystems.tela.data.clockOut;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = SyncClockOutTableConstant.TABLE_NAME)
public class SyncClockOut {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncClockOutTableConstant.PRIMARY_KEY)
    @NonNull
    private int primaryKey;

    @ColumnInfo(name = SyncClockOutTableConstant.DATE_COLUMN_NAME)
    private String clockOutDate;

    @ColumnInfo(name = SyncClockOutTableConstant.DAY_COLUMN_NAME)
    private String day;

    @ColumnInfo(name = SyncClockOutTableConstant.TIME_COLUMN_NAME)
    private String clockOutTime;

    @ColumnInfo(name = SyncClockOutTableConstant.COMMENT_COLUMN_NAME)
    private String comment;

    @NonNull
    @ColumnInfo(name = SyncClockOutTableConstant.EMPLOYEE_NUMBER_COLUMN_NAME)
    private String employeeNo;

    @ColumnInfo(name = SyncClockOutTableConstant.EMPLOYEE_ID_COLUMN_NAME)
    private String employeeId;

    @ColumnInfo(name = SyncClockOutTableConstant.LATITUDE_COLUMN_NAME)
    private String latitude;

    @ColumnInfo(name = SyncClockOutTableConstant.LONGITUDE_COLUMN_NAME)
    private String longitude;

    @ColumnInfo(name = SyncClockOutTableConstant.SCHOOL_ID_COLUMN_NAME)
    private String schoolId;

    @ColumnInfo(name = SyncClockOutTableConstant.SCHOOL_NAME_COLUMN_NAME)
    private String schoolName;

    @ColumnInfo(name = SyncClockOutTableConstant.FIRST_NAME_COLUMN_NAME)
    private String firstName;

    @ColumnInfo(name = SyncClockOutTableConstant.LAST_NAME_TABLE_NAME)
    private String lastName;

    @ColumnInfo(name = SyncClockOutTableConstant.IS_UPLOADED_COLUMN_NAME)
    private boolean isUploaded;

    @ColumnInfo(name = SyncClockOutTableConstant.FINGER_PRINT_COLUMN_NAME)
    private final byte[] fingerPrint;

    ////////////////////////////////////////constructor////////////////////////////////////////////
    public SyncClockOut(String clockOutDate, String day, String clockOutTime, String comment, @NonNull String employeeNo, String employeeId, String latitude,
                        String longitude, String schoolId, String schoolName, String firstName, String lastName, boolean isUploaded, byte[] fingerPrint) {
        this.clockOutDate = clockOutDate;
        this.day = day;
        this.clockOutTime = clockOutTime;
        this.comment = comment;
        this.employeeNo = employeeNo;
        this.employeeId = employeeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isUploaded = isUploaded;
        this.fingerPrint = fingerPrint;
    }

    ////////////////////////////////////////GETTERS and SETTERS///////////////////////////////////////////
    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getClockOutDate() {
        return clockOutDate;
    }

    public void setClockOutDate(String clockOutDate) {
        this.clockOutDate = clockOutDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(String clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NonNull
    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(@NonNull String employeeNo) {
        this.employeeNo = employeeNo;
    }

    @NonNull
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NonNull String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    public byte[] getFingerPrint() {
        return fingerPrint;
    }

    @Override
    public String toString() {
        return "SyncClockOut{" +
                "primaryKey=" + primaryKey +
                ", clockOutDate='" + clockOutDate + '\'' +
                ", day='" + day + '\'' +
                ", clockOutTime='" + clockOutTime + '\'' +
                ", comment='" + comment + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isUploaded=" + isUploaded +
                ", fingerPrint=" + Arrays.toString(fingerPrint) +
                '}';
    }
}
