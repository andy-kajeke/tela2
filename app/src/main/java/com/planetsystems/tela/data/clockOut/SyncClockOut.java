package com.planetsystems.tela.data.clockOut;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncClockOutTableConstant.TABLE_NAME)
public class SyncClockOut {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "dateCreated")
    private String dateCreated;

    @ColumnInfo(name = "dateUpdated")
    private String dateUpdated;

    @ColumnInfo(name = "status")
    private int status;

    @ColumnInfo(name = "clockOutDate")
    @NonNull
    private String clockOutDate;

    @ColumnInfo(name = "clockOutTime")
    @NonNull
    private String clockOutTime;

    @ColumnInfo(name = "comment")
    private String comment;

    @ColumnInfo(name = SyncClockOutTableConstant.DAY_COLUMN_NAME)
    @NonNull
    private String day;

    @ColumnInfo(name = "employeeId")
    @NonNull
    private String employeeId;

    @ColumnInfo(name = "employeeNo")
    @NonNull
    private String employeeNo;

    @ColumnInfo(name = "latitude")
    @NonNull
    private String latitude;

    @ColumnInfo(name = "longitude")
    @NonNull
    private String longitude;

    @ColumnInfo(name = "synStatus")
    @NonNull
    private String synStatus;

    @ColumnInfo(name = "schoolId")
    @NonNull
    private String schoolId;

    @ColumnInfo(name = "schoolName")
    @NonNull
    private String schoolName;

    @ColumnInfo(name = "empFirstName")
    @NonNull
    private String empFirstName;

    @ColumnInfo(name = "empLastName")
    @NonNull
    private String empLastName;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @NonNull
    public String getClockOutDate() {
        return clockOutDate;
    }

    public void setClockOutDate(@NonNull String clockOutDate) {
        this.clockOutDate = clockOutDate;
    }

    @NonNull
    public String getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(@NonNull String clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NonNull
    public String getDay() {
        return day;
    }

    public void setDay(@NonNull String day) {
        this.day = day;
    }

    @NonNull
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NonNull String employeeId) {
        this.employeeId = employeeId;
    }

    @NonNull
    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(@NonNull String employeeNo) {
        this.employeeNo = employeeNo;
    }

    @NonNull
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(@NonNull String latitude) {
        this.latitude = latitude;
    }

    @NonNull
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(@NonNull String longitude) {
        this.longitude = longitude;
    }

    @NonNull
    public String getSynStatus() {
        return synStatus;
    }

    public void setSynStatus(@NonNull String synStatus) {
        this.synStatus = synStatus;
    }

    @NonNull
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(@NonNull String schoolId) {
        this.schoolId = schoolId;
    }

    @NonNull
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(@NonNull String schoolName) {
        this.schoolName = schoolName;
    }

    @NonNull
    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(@NonNull String empFirstName) {
        this.empFirstName = empFirstName;
    }

    @NonNull
    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(@NonNull String empLastName) {
        this.empLastName = empLastName;
    }

    public SyncClockOut(@NonNull String id, String dateCreated, String dateUpdated, int status, @NonNull String clockOutDate, @NonNull String clockOutTime,
                        String comment, @NonNull String day, @NonNull String employeeId, @NonNull String employeeNo,
                        @NonNull String latitude, @NonNull String longitude, @NonNull String synStatus,
                        @NonNull String schoolId, @NonNull String schoolName, @NonNull String empFirstName,
                        @NonNull String empLastName) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.clockOutDate = clockOutDate;
        this.clockOutTime = clockOutTime;
        this.comment = comment;
        this.day = day;
        this.employeeId = employeeId;
        this.employeeNo = employeeNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.synStatus = synStatus;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
    }
}
