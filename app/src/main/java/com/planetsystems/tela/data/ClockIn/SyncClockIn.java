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
}
