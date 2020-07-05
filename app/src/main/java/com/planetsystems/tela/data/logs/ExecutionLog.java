package com.planetsystems.tela.data.logs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = ExecutionLogConstants.TABLE_NAME)
public class ExecutionLog {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ExecutionLogConstants.ID)
    private int primaryKey;

    @ColumnInfo(name = ExecutionLogConstants.MESSAGE)
    private String message;

    @ColumnInfo(name = ExecutionLogConstants.DATE)
    private String date;

    @ColumnInfo(name = ExecutionLogConstants.TIME)
    private String time;

    @ColumnInfo(name = ExecutionLogConstants.CLASS_NAME)
    private String className;

    @ColumnInfo(name = ExecutionLogConstants.DEVICE_IMEI)
    private String deviceNo;

    @ColumnInfo(name = ExecutionLogConstants.SCHOOL_NAME)
    private String school;

    @ColumnInfo(name = ExecutionLogConstants.DATA_STRING)
    private String data;

    public ExecutionLog(String message, String date, String time, String className, String deviceNo, String school, String data) {
        this.message = message;
        this.date = date;
        this.time = time;
        this.className = className;
        this.deviceNo = deviceNo;
        this.school = school;
        this.data = data;
    }

    public ExecutionLog(String message, String date, String time, String className, String deviceNo, String school) {
        this.message = message;
        this.date = date;
        this.time = time;
        this.className = className;
        this.deviceNo = deviceNo;
        this.school = school;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
