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


}
