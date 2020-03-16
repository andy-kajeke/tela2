package com.planetsystems.tela.enties;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncTableConstants;

@Entity(tableName = SyncTableConstants.SyncEmployeeTimeOffRequestDMs)
public class SyncEmployeeTimeOffRequestDMs {

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

    @ColumnInfo(name = "approvalStatus")
    private String approvalStatus;

    @ColumnInfo(name = "confirmation")
    private String confirmation;

    @ColumnInfo(name = "deploymentSite")
    private String deploymentSite;

    @ColumnInfo(name = "deploymentSiteId")
    private String deploymentSiteId;

    @ColumnInfo(name = "deploymentUnit")
    private String deploymentUnit;

    @ColumnInfo(name = "deploymentUnitId")
    private String deploymentUnitId;

    @ColumnInfo(name = "employee")
    private String employee;

    @ColumnInfo(name = "employeeId")
    private String employeeId;

    @ColumnInfo(name = "employeeRequestType")
    private String employeeRequestType;

    @ColumnInfo(name = "fromDate")
    private String fromDate;

    @ColumnInfo(name = "fromTime")
    private String fromTime;

    @ColumnInfo(name = "generalComment")
    private String generalComment;

    @ColumnInfo(name = "requestDate")
    private String requestDate;

    @ColumnInfo(name = "approvalDate")
    private String approvalDate;

    @ColumnInfo(name = "toDate")
    private String toDate;

    @ColumnInfo(name = "toTime")
    private String toTime;

    @ColumnInfo(name = "typeOfLeave")
    private String typeOfLeave;

    public SyncEmployeeTimeOffRequestDMs(@NonNull String id, String dateCreated, String dateUpdated, int status, String comment,
                                         String approvalStatus, String confirmation, String deploymentSite,
                                         String deploymentSiteId, String deploymentUnit, String deploymentUnitId,
                                         String employee, String employeeId, String employeeRequestType,
                                         String fromDate, String fromTime, String generalComment, String requestDate,
                                         String approvalDate, String toDate, String toTime, String typeOfLeave) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.comment = comment;
        this.approvalStatus = approvalStatus;
        this.confirmation = confirmation;
        this.deploymentSite = deploymentSite;
        this.deploymentSiteId = deploymentSiteId;
        this.deploymentUnit = deploymentUnit;
        this.deploymentUnitId = deploymentUnitId;
        this.employee = employee;
        this.employeeId = employeeId;
        this.employeeRequestType = employeeRequestType;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.generalComment = generalComment;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.toDate = toDate;
        this.toTime = toTime;
        this.typeOfLeave = typeOfLeave;
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

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getDeploymentSite() {
        return deploymentSite;
    }

    public void setDeploymentSite(String deploymentSite) {
        this.deploymentSite = deploymentSite;
    }

    public String getDeploymentSiteId() {
        return deploymentSiteId;
    }

    public void setDeploymentSiteId(String deploymentSiteId) {
        this.deploymentSiteId = deploymentSiteId;
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeRequestType() {
        return employeeRequestType;
    }

    public void setEmployeeRequestType(String employeeRequestType) {
        this.employeeRequestType = employeeRequestType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getGeneralComment() {
        return generalComment;
    }

    public void setGeneralComment(String generalComment) {
        this.generalComment = generalComment;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getTypeOfLeave() {
        return typeOfLeave;
    }

    public void setTypeOfLeave(String typeOfLeave) {
        this.typeOfLeave = typeOfLeave;
    }
}
