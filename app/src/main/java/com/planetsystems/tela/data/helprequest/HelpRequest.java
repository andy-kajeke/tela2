package com.planetsystems.tela.data.helprequest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = HelpRequestConstant.TABLE_NAME)
public class HelpRequest {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = HelpRequestConstant.PRIMARY_KEY)
    private int primaryKey;

    @ColumnInfo(name = HelpRequestConstant.RequestID)
    private String requestID;

    @ColumnInfo(name = HelpRequestConstant.APPROVAL_STATUS)
    private String approvalStatus;

    @ColumnInfo(name = HelpRequestConstant.SUPERVISOR)
    private String supervisor;

    @ColumnInfo(name = HelpRequestConstant.COMMENT)
    private String comment;

    @ColumnInfo(name = HelpRequestConstant.DEPLOYMENT_SITE_ID)
    private String schoolID;

    @ColumnInfo(name = HelpRequestConstant.HELP_CATEGORY)
    private String requestType;

    @ColumnInfo(name = HelpRequestConstant.PRIORITY)
    private String priority;

    @ColumnInfo(name = HelpRequestConstant.STAFF_CODE)
    private String employeeNo;

    @ColumnInfo(name = HelpRequestConstant.EMPLOYEE_NAME)
    private String employeeName;

    @ColumnInfo(name = HelpRequestConstant.REQUESTED_DATE)
    private String requestDate;

    @ColumnInfo(name = HelpRequestConstant.APPROVAL_DATE)
    private String approvalDate;

    @ColumnInfo(name = HelpRequestConstant.COLUMN_IS_UPLOADED_TEACHER)
    private boolean isUploadedTeacher;

    @ColumnInfo(name = HelpRequestConstant.COLUMN_IS_UPLOADED_SUPERVISOR)
    private boolean isUploadedSupervisor;

    public HelpRequest(String requestID, String approvalStatus, String supervisor, String comment, String schoolID, String requestType, String priority,
                       String employeeNo, String employeeName, String requestDate, String approvalDate, boolean isUploadedTeacher, boolean isUploadedSupervisor) {
        this.requestID = requestID;
        this.approvalStatus = approvalStatus;
        this.supervisor = supervisor;
        this.comment = comment;
        this.schoolID = schoolID;
        this.requestType = requestType;
        this.priority = priority;
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.isUploadedTeacher = isUploadedTeacher;
        this.isUploadedSupervisor = isUploadedSupervisor;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
