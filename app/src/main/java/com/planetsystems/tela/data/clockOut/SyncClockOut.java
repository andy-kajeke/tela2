package com.planetsystems.tela.data.clockOut;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncClockOutTableConstant.TABLE_NAME)
public class SyncClockOut {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncClockOutTableConstant.PRIMARY_KEY)
    @NonNull
    private int primaryKey;


    @ColumnInfo(name = SyncClockOutTableConstant.DATE_COLUMN_NAME)
    private String date;

    @ColumnInfo(name = SyncClockOutTableConstant.TIME_COLUMN_NAME)
    private String time;

    @ColumnInfo(name = SyncClockOutTableConstant.COMMENT_COLUMN_NAME)
    private String comment;

    @ColumnInfo(name = SyncClockOutTableConstant.DAY_COLUMN_NAME)
    private String day;

    @NonNull
    @ColumnInfo(name = SyncClockOutTableConstant.EMPLOYEE_NUMBER_COLUMN_NAME)
    private String employeeNumber;

    @ColumnInfo(name = SyncClockOutTableConstant.LATITUDE_COLUMN_NAME)
    private String latitude;

    @ColumnInfo(name = SyncClockOutTableConstant.LONGITUDE_COLUMN_NAME)
    private String longitude;

    @ColumnInfo(name = SyncClockOutTableConstant.STATUS_COLUMN_NAME)
    private String status;

    @ColumnInfo(name = SyncClockOutTableConstant.SCHOOL_ID_COLUMN_NAME)
    private String schoolId;

    @ColumnInfo(name = SyncClockOutTableConstant.SCHOOL_NAME_COLUMN_NAME)
    private String schoolName;

    @ColumnInfo(name = SyncClockOutTableConstant.FIRST_NAME_COLUMN_NAME)
    private String firstName;

    @ColumnInfo(name = SyncClockOutTableConstant.LAST_NAME_TABLE_NAME)
    private String lastName;

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @NonNull
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(@NonNull String employeeNumber) {
        this.employeeNumber = employeeNumber;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public SyncClockOut(String date, String time, String comment, String day, @NonNull String employeeNumber, String latitude, String longitude, String status, String schoolId, String schoolName, String firstName, String lastName) {
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.day = day;
        this.employeeNumber = employeeNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "SyncClockOut{" +
                "primaryKey=" + primaryKey +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", comment='" + comment + '\'' +
                ", day='" + day + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", status='" + status + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
