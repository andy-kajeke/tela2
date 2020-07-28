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
    private int requestId;

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

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_DEPLOYMENT_SITE_ID)
    private String schoolId;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_EMPLOYEE)
    private String employee;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_EMPLOYEE_NO)
    private String employeeNo;

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
    private String supervisor;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_TO_DATE)
    private String toDate;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_TO_TIME)
    private String toTime;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_TYPE_OF_LEAVE)
    private String typeOfLeave;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_IS_UPLOADED_TEACHER)
    private boolean isUploadedTeacher;

    @ColumnInfo(name = SyncEmployeeTimeOffRequestDMsConstants.COLUMN_IS_UPLOADED_SUPERVISOR)
    private boolean isUploadedSupervisor;

    public SyncEmployeeTimeOffRequestDM(String dateCreated, String dateUpdated, int status, String comment,
                                        String approvalStatus, String confirmation, String schoolId,
                                        String employee, String employeeNo, String employeeRequestType,
                                        String fromDate, String fromTime, String generalComment, String requestDate,
                                        String approvalDate, String supervisor, String toDate, String toTime, String typeOfLeave, boolean isUploadedTeacher, boolean isUploadedSupervisor) {
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.comment = comment;
        this.approvalStatus = approvalStatus;
        this.confirmation = confirmation;
        this.schoolId = schoolId;
        this.employee = employee;
        this.employeeNo = employeeNo;
        this.employeeRequestType = employeeRequestType;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.generalComment = generalComment;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.supervisor = supervisor;
        this.toDate = toDate;
        this.toTime = toTime;
        this.typeOfLeave = typeOfLeave;
        this.isUploadedTeacher = isUploadedTeacher;
        this.isUploadedSupervisor = isUploadedSupervisor;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
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

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
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

    public boolean isUploadedTeacher() {
        return isUploadedTeacher;
    }

    public void setUploadedTeacher(boolean uploadedTeacher) {
        isUploadedTeacher = uploadedTeacher;
    }

    public boolean isUploadedSupervisor() {
        return isUploadedSupervisor;
    }

    public void setUploadedSupervisor(boolean uploadedSupervisor) {
        isUploadedSupervisor = uploadedSupervisor;
    }
}
