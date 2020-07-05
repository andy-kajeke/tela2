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

    @ColumnInfo(name = ExecutionLogConstants.LINE_NUMBER)
    private String lineNumber;

    @ColumnInfo(name = ExecutionLogConstants.METHOD_NAME)
    private String methodName;


}
