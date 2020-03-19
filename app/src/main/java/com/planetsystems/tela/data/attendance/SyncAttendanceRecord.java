package com.planetsystems.tela.data.attendance;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncTableConstants;

@Entity(tableName = SyncTableConstants.SyncAttendanceRecords)
public class SyncAttendanceRecord {

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

    @ColumnInfo(name = "deploymentUnit")
    private String deploymentUnit;

    @ColumnInfo(name = "deploymentUnitId")
    private String deploymentUnitId;

    @ColumnInfo(name = "femaleAbsent")
    private String femaleAbsent;

    @ColumnInfo(name = "femalePresent")
    private String femalePresent;

    @ColumnInfo(name = "maleAbsent")
    private String maleAbsent;

    @ColumnInfo(name = "malePresent")
    private String malePresent;

    @ColumnInfo(name = "submissionDate")
    private String submissionDate;

    @ColumnInfo(name = "supervisorId")
    private String supervisorId;

    @ColumnInfo(name = "taskDay")
    private String taskDay;

    public SyncAttendanceRecord(@NonNull String id, String dateCreated, String dateUpdated, int status,
                                String comment, String deploymentUnit, String deploymentUnitId, String femaleAbsent,
                                String femalePresent, String maleAbsent, String malePresent, String submissionDate,
                                String supervisorId, String taskDay) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.comment = comment;
        this.deploymentUnit = deploymentUnit;
        this.deploymentUnitId = deploymentUnitId;
        this.femaleAbsent = femaleAbsent;
        this.femalePresent = femalePresent;
        this.maleAbsent = maleAbsent;
        this.malePresent = malePresent;
        this.submissionDate = submissionDate;
        this.supervisorId = supervisorId;
        this.taskDay = taskDay;
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

    public String getDeploymentUnit() {
        return deploymentUnit;
    }

    public void setDeploymentUnit(String deploymentUnit) {
        this.deploymentUnit = deploymentUnit;
    }

    public String getDeploymentUnitId() {
        return deploymentUnitId;
    }

    public void setDeploymentUnitId(String deploymentUnitId) {
        this.deploymentUnitId = deploymentUnitId;
    }

    public String getFemaleAbsent() {
        return femaleAbsent;
    }

    public void setFemaleAbsent(String femaleAbsent) {
        this.femaleAbsent = femaleAbsent;
    }

    public String getFemalePresent() {
        return femalePresent;
    }

    public void setFemalePresent(String femalePresent) {
        this.femalePresent = femalePresent;
    }

    public String getMaleAbsent() {
        return maleAbsent;
    }

    public void setMaleAbsent(String maleAbsent) {
        this.maleAbsent = maleAbsent;
    }

    public String getMalePresent() {
        return malePresent;
    }

    public void setMalePresent(String malePresent) {
        this.malePresent = malePresent;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getTaskDay() {
        return taskDay;
    }

    public void setTaskDay(String taskDay) {
        this.taskDay = taskDay;
    }
}
