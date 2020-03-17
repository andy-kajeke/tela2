package com.planetsystems.tela.data.timetable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncTimeTableConstant.TABLE_NAME)
public class SyncTimeTable {
    @NonNull
    @ColumnInfo(name = SyncTimeTableConstant.ID)
    @PrimaryKey
    private String id;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncTimeTableConstant.STATUS)
    private String status;

    @ColumnInfo(name = SyncTimeTableConstant.DAY)
    private String day;

    @ColumnInfo(name = SyncTimeTableConstant.EMPLOYEE_NAME)
    private String employeeName;

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

    @ColumnInfo(name = SyncTimeTableConstant.SUBJECT_ID)
    private String subjectId;

    @ColumnInfo(name = SyncTimeTableConstant.TASK_ID)
    private String taskId;

    @ColumnInfo(name = SyncTimeTableConstant.START_TIME)
    private String taskName;

    @ColumnInfo(name = SyncTimeTableConstant.ROW_ID)
    private String rowId;

    @ColumnInfo(name = SyncTimeTableConstant.ROW_VER)
    private String rowVer;
}
