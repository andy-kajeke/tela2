package com.planetsystems.tela.data.confirmTimeOnTaskAttendance;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncTableConstants;

@Entity(tableName = SyncConfirmTimeOnTaskAttendanceConstants.TABLE_NAME)
public class SyncConfirmTimeOnTaskAttendance {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.STATUS)
    private String status;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.COMMENT)
    private String comment;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.EMPLOYEE_NO)
    private String employeeNumber;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.SUPERVISOR_STATUS)
    private String supervisionStatus;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.SUPERVISOR_ID)
    private String supervisorId;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.TASK_ID)
    private String taskId;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.TRANSACTION_DATE)
    private String transactionDate;

    @ColumnInfo(name = SyncConfirmTimeOnTaskAttendanceConstants.IS_UPLOADED)
    private boolean isUploaded;

    public SyncConfirmTimeOnTaskAttendance(String status, String comment, String employeeId, String employeeNumber, String supervisionStatus,
                                           String supervisorId, String taskId, String transactionDate, boolean isUploaded) {
        this.status = status;
        this.comment = comment;
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.supervisionStatus = supervisionStatus;
        this.supervisorId = supervisorId;
        this.taskId = taskId;
        this.transactionDate = transactionDate;
        this.isUploaded = isUploaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getSupervisionStatus() {
        return supervisionStatus;
    }

    public void setSupervisionStatus(String supervisionStatus) {
        this.supervisionStatus = supervisionStatus;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }
}