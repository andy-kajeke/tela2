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

}
