package com.planetsystems.tela.data.employeeTimeOffRequestDM;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncTableConstants;

@Entity(tableName = SyncEmployeeTimeOffRequestDMsConstants.TABLE_NAME)
public class SyncEmployeeTimeOffRequestDM {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_PRIMARY_KEY)
    @NonNull
    private int primaryKey;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_STATUS)
    private int status;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_COMMENT)
    private String comment;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_APPROVAL_STATUS)
    private String approvalStatus;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_CONFIRMATION)
    private String confirmation;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_DEPLOYMENT_SITE)
    private String deploymentSite;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_DEPLOYMENT_SITE_ID)
    private String deploymentSiteId;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_DEPLOYMENT_UNIT)
    private String deploymentUnit;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_DEPLOYMENT_UNIT_ID)
    private String deploymentUnitId;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_EMPLOYEE)
    private String employee;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_EMPLOYEE_REQUEST_TYPE)
    private String employeeRequestType;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_FROM_DATE)
    private String fromDate;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_FROM_TIME)
    private String fromTime;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_GENERAL_COMMENT)
    private String generalComment;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_REQUEST_DATE)
    private String requestDate;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_APPROVAL_DATE)
    private String approvalDate;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_SUPERVISOR_ID)
    private String supervisorId;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_TO_DATE)
    private String toDate;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_TO_TIME)
    private String toTime;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_TYPE_OF_LEAVE)
    private String typeOfLeave;

    public SyncEmployeeTimeOffRequestDM(String dateCreated, String dateUpdated, int status, String comment,
                                        String approvalStatus, String confirmation, String deploymentSite,
                                        String deploymentSiteId, String deploymentUnit, String deploymentUnitId,
                                        String employee, String employeeId, String employeeRequestType,
                                        String fromDate, String fromTime, String generalComment, String requestDate,
                                        String approvalDate, String supervisorId, String toDate, String toTime, String typeOfLeave) {
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
        this.supervisorId = supervisorId;
        this.toDate = toDate;
        this.toTime = toTime;
        this.typeOfLeave = typeOfLeave;
    }

    @NonNull
    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(@NonNull int primaryKey) {
        this.primaryKey = primaryKey;
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

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
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
