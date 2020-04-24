package com.planetsystems.tela.data.timetable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncTableConstants;

@Entity(tableName = SyncTimeTableConstant.TABLE_NAME)
public class SyncTimeTable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncTimeTableConstant.PRIMARY_KEY_COLUMN_NAME)
    private int primaryKey;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncTimeTableConstant.STATUS)
    private String status;

    @ColumnInfo(name = SyncTimeTableConstant.CLASS_ID)
    private String classId;

    @ColumnInfo(name = SyncTimeTableConstant.CLASS_UNIT)
    private String classUnit;

    @ColumnInfo(name =  SyncTimeTableConstant.DAY)
    private String day;

    @ColumnInfo(name = SyncTimeTableConstant.EMPLOYEE_NAME)
    private String employName;

    @ColumnInfo(name = SyncTimeTableConstant.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncTimeTableConstant.EMPLOYEE_NO)
    private String employeeNo;

    @ColumnInfo(name = SyncTimeTableConstant.END_TIME)
    private String endTime;

    @ColumnInfo(name = SyncTimeTableConstant.SCHOOL_ID)
    private String schoolId;

    @ColumnInfo(name = SyncTimeTableConstant.SCHOOL_NAME)
    private String schoolName;

    @ColumnInfo(name = SyncTimeTableConstant.START_TIME)
    private String startTime;

    @ColumnInfo(name = SyncTimeTableConstant.SUBJECT)
    private String subject;

    @ColumnInfo(name = SyncTimeTableConstant.SCHOOL_ID)
    private String subjectId;

    @ColumnInfo(name = SyncTimeTableConstant.TASK_ID)
    private String taskId;

    @ColumnInfo(name = SyncTimeTableConstant.TASK_NAME)
    private String taskName;
}
