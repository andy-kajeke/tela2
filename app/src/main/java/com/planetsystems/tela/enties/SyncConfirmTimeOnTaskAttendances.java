package com.planetsystems.tela.enties;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncTableConstants;

@Entity(tableName = SyncTableConstants.SyncClockOuts)
public class SyncConfirmTimeOnTaskAttendances {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "dateCreated")
    private String dateCreated;

    @ColumnInfo(name = "dateUpdated")
    private String dateUpdated;

    @ColumnInfo(name = "status")
    private int status;

    @ColumnInfo(name = "comment")
    private String comment;

    @ColumnInfo(name = "employeeId")
    @NonNull
    private String employeeId;

    @ColumnInfo(name = "employeeNumber")
    @NonNull
    private String employeeNumber;

    @ColumnInfo(name = "endTime")
    @NonNull
    private String endTime;

    @ColumnInfo(name = "startTime")
    @NonNull
    private String startTime;

    @ColumnInfo(name = "supervisionStatus")
    private String supervisionStatus;

    @ColumnInfo(name = "supervisorId")
    private String supervisorId;

    @ColumnInfo(name = "taskDescription")
    private String taskDescription;

    @ColumnInfo(name = "taskId")
    private String taskId;

    @ColumnInfo(name = "transactionDate")
    private String transactionDate;

    public SyncConfirmTimeOnTaskAttendances(@NonNull String id, String dateCreated, String dateUpdated, int status, String comment,
                                            @NonNull String employeeId, @NonNull String employeeNumber, @NonNull String endTime,
                                            @NonNull String startTime, String supervisionStatus, String supervisorId, String taskDescription,
                                            String taskId, String transactionDate) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.comment = comment;
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.endTime = endTime;
        this.startTime = startTime;
        this.supervisionStatus = supervisionStatus;
        this.supervisorId = supervisorId;
        this.taskDescription = taskDescription;
        this.taskId = taskId;
        this.transactionDate = transactionDate;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NonNull
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NonNull String employeeId) {
        this.employeeId = employeeId;
    }

    @NonNull
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(@NonNull String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @NonNull
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(@NonNull String endTime) {
        this.endTime = endTime;
    }

    @NonNull
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(@NonNull String startTime) {
        this.startTime = startTime;
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

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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
}
