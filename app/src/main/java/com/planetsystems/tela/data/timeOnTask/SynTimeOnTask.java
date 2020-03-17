package com.planetsystems.tela.data.timeOnTask;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = SynTimeOnTaskConstant.TABLE_NAME)
public class SynTimeOnTask {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = SynTimeOnTaskConstant.ID)
    private String id;

    @ColumnInfo(name = SynTimeOnTaskConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SynTimeOnTaskConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SynTimeOnTaskConstant.STATUS)
    private String status;

    @ColumnInfo(name = SynTimeOnTaskConstant.ACTION_STATUS)
    private String actionStatus;

    @ColumnInfo(name = SynTimeOnTaskConstant.COMMENT)
    private String comment;

    @ColumnInfo(name = SynTimeOnTaskConstant.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SynTimeOnTaskConstant.EMPLOYEE_NUMBER)
    private String employeeNumber;

    @ColumnInfo(name = SynTimeOnTaskConstant.TASK_ID)
    private String taskId;

    @ColumnInfo(name = SynTimeOnTaskConstant.TRANSACTION_TIME)
    private String transactionTime;

    @ColumnInfo(name = SynTimeOnTaskConstant.TRANSACTION_DATE)
    private String transactionDate;

    @ColumnInfo(name = SynTimeOnTaskConstant.ROW_ID)
    private String rowId;

    @ColumnInfo(name = SynTimeOnTaskConstant.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = SynTimeOnTaskConstant.EMPLOYEE_FIRST_NAME)
    private String employeeFirstName;

    @ColumnInfo(name = SynTimeOnTaskConstant.EMPLOYEE_LAST_NAME)
    private String employeeLastName;

    @ColumnInfo(name = SynTimeOnTaskConstant.END_TIME)
    private String endTime;

    @ColumnInfo(name = SynTimeOnTaskConstant.START_TIME)
    private String startTime;

    @ColumnInfo(name = SynTimeOnTaskConstant.TASK_NAME)
    private String taskName;

}
