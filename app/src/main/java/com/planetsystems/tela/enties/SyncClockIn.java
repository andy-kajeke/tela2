package com.planetsystems.tela.enties;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncClockInNames;

@Entity(tableName = "sync_clock_in")
public class SyncClockIn {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = SyncClockInNames.ID)
    private String id;

    @ColumnInfo(name = SyncClockInNames.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncClockInNames.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncClockInNames.STATUS)
    private String status;

    @ColumnInfo(name = SyncClockInNames.CLOCK_IN_DATE)
    private String clockInDate;

    @ColumnInfo(name = SyncClockInNames.CLOCK_IN_TIME)
    private String clockInTime;

    @ColumnInfo(name = SyncClockInNames.DAY)
    private String day;

    @ColumnInfo(name = SyncClockInNames.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncClockInNames.EMPLOYEE_NUMBER)
    private String employeeNo;

    @ColumnInfo(name = SyncClockInNames.LATITUDE)
    private String latitude;

    @ColumnInfo(name = SyncClockInNames.LONGITUDE)
    private String longitude;

    @ColumnInfo(name = SyncClockInNames.SYNC_STATUS)
    private String synStatus;

    @ColumnInfo(name = SyncClockInNames.SCHOOL_ID)
    private String schoolId;

    @ColumnInfo(name = SyncClockInNames.EMPLOYEE_FIRST_NAME)
    private String empFirstName;

    @ColumnInfo(name = SyncClockInNames.EMPLOYEE_LAST_NAME)
    private String empLastName;

    @ColumnInfo(name = SyncClockInNames.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = SyncClockInNames.ROW_ID)
    private String rowId;



}
