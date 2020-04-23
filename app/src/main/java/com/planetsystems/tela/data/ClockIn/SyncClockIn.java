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
    @ColumnInfo(name = SyncClockInTableConstants.DATABASE_ID)
    private int dbID;

    @NonNull
    @ColumnInfo(name = SyncClockInTableConstants.ID)
    private String id;

    @ColumnInfo(name = SyncClockInTableConstants.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncClockInTableConstants.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncClockInTableConstants.STATUS)
    private String status;

    @ColumnInfo(name = SyncClockInTableConstants.CLOCK_IN_DATE)
    private String clockInDate;

    @ColumnInfo(name = SyncClockInTableConstants.CLOCK_IN_TIME)
    private String clockInTime;

    @ColumnInfo(name = SyncClockInTableConstants.DAY)
    private String day;

    @ColumnInfo(name = SyncClockInTableConstants.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncClockInTableConstants.EMPLOYEE_NUMBER)
    private String employeeNo;

    @ColumnInfo(name = SyncClockInTableConstants.LATITUDE)
    private String latitude;

    @ColumnInfo(name = SyncClockInTableConstants.LONGITUDE)
    private String longitude;

    @ColumnInfo(name = SyncClockInTableConstants.SYNC_STATUS)
    private String synStatus;

    @ColumnInfo(name = SyncClockInTableConstants.SCHOOL_ID)
    private String schoolId;

    @ColumnInfo(name = SyncClockInTableConstants.EMPLOYEE_FIRST_NAME)
    private String empFirstName;

    @ColumnInfo(name = SyncClockInTableConstants.EMPLOYEE_LAST_NAME)
    private String empLastName;

    @ColumnInfo(name = SyncClockInTableConstants.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = SyncClockInTableConstants.ROW_ID)
    private String rowId;

    @Ignore
    public SyncClockIn(String employeeId, String employeeNo, String latitude, String longitude, String clockInDate, String day, String clockInTime, String schoolId) {
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClockInDate() {
        return clockInDate;
    }

    public void setClockInDate(String clockInDate) {
        this.clockInDate = clockInDate;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(String clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
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

    public String getSynStatus() {
        return synStatus;
    }

    public void setSynStatus(String synStatus) {
        this.synStatus = synStatus;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getRowVer() {
        return rowVer;
    }

    public void setRowVer(String rowVer) {
        this.rowVer = rowVer;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    @NonNull
    public int getDbID() {
        return dbID;
    }

    public void setDbID(@NonNull int dbID) {
        this.dbID = dbID;
    }

    public SyncClockIn(@NonNull String id, String dateCreated, String dateUpdated, String status, String clockInDate, String clockInTime, String day, String employeeId, String employeeNo, String latitude, String longitude, String synStatus, String schoolId, String empFirstName, String empLastName, String rowVer, String rowId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.clockInDate = clockInDate;
        this.clockInTime = clockInTime;
        this.day = day;
        this.employeeId = employeeId;
        this.employeeNo = employeeNo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.synStatus = synStatus;
        this.schoolId = schoolId;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.rowVer = rowVer;
        this.rowId = rowId;
    }

    @Override
    public String toString() {
        return "SyncClockIn{" +
                "dbID='" + dbID + '\'' +
                ", id='" + id + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", status='" + status + '\'' +
                ", clockInDate='" + clockInDate + '\'' +
                ", clockInTime='" + clockInTime + '\'' +
                ", day='" + day + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", synStatus='" + synStatus + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", empFirstName='" + empFirstName + '\'' +
                ", empLastName='" + empLastName + '\'' +
                ", rowVer='" + rowVer + '\'' +
                ", rowId='" + rowId + '\'' +
                '}';
    }
}
