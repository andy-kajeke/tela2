package com.planetsystems.tela.logs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = ExecutionLogConstants.TABLE_NAME)
public class ExecutionLog {
    @ColumnInfo(name = ExecutionLogConstants.ID)
    int primaryKey;

    @ColumnInfo(name = ExecutionLogConstants.MESSAGE)
    String message;

    @ColumnInfo(name = ExecutionLogConstants.TIME)
    String time;

    @ColumnInfo(name = ExecutionLogConstants.CLASS_NAME)
    String className;

    @ColumnInfo(name = ExecutionLogConstants.DEVICE_IMEI)
    String deviceNo;

    @ColumnInfo(name = ExecutionLogConstants.SCHOOL_NAME)
    String school;

    public ExecutionLog(String message, String time, String className, String deviceNo, String school) {
        this.message = message;
        this.time = time;
        this.className = className;
        this.deviceNo = deviceNo;
        this.school = school;
    }
}
