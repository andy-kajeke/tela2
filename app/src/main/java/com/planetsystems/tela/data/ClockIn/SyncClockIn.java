package com.planetsystems.tela.data.ClockIn;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncClockInTableConstants.TABLE_NAME)
public class SyncClockIn {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_PRIMARY_KEY)
    private int primaryKey;

    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_EMPLOYEE_NUMBER)
    private String employeeNo;

    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_LATITUDE)
    private String latitude;

    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_LONGITUDE)
    private String longitude;

    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_CLOCK_IN_DATE)
    private String clockInDate;

    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_DAY)
    private String day;

    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_CLOCK_IN_TIME)
    private String clockInTime;

    @ColumnInfo(name = SyncClockInTableConstants.COLUMN_SCHOOL_ID)
    private String schoolId;

    public SyncClockIn(String employeeNo, String employeeId, String latitude, String longitude, String clockInDate, String day, String clockInTime, String schoolId) {
        this.employeeNo = employeeNo;
        this.employeeId = employeeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.clockInDate = clockInDate;
        this.day = day;
        this.clockInTime = clockInTime;
        this.schoolId = schoolId;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
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

    public String getClockInDate() {
        return clockInDate;
    }

    public void setClockInDate(String clockInDate) {
        this.clockInDate = clockInDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(String clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "SyncClockIn{" +
                "primaryKey=" + primaryKey +
                ", employeeNo='" + employeeNo + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", clockInDate='" + clockInDate + '\'' +
                ", day='" + day + '\'' +
                ", clockInTime='" + clockInTime + '\'' +
                ", schoolId='" + schoolId + '\'' +
                '}';
    }
}
