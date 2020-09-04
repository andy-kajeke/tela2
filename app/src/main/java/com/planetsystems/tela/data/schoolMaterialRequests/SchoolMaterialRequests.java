package com.planetsystems.tela.data.schoolMaterialRequests;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SchoolMaterialRequestsConstants.TABLE_NAME)
public class SchoolMaterialRequests {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = SchoolMaterialRequestsConstants.PRIMARY_KEY)
    private int primaryKey;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.EMPLOYEE_NO)
    private String employeeNo;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.EMPLOYEE_NAME)
    private String employeeName;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.EMPLOYEE_REQUEST_TYPE)
    private String employeeRequestType;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.COMMENT)
    private String comment;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.REQUEST_DATE)
    private String requestDate;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.SCHOOL_ID)
    private String schoolId;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.CLASS_ID)
    private String classId;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.ITEM_NAME)
    private String itemName;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.ITEM_ID)
    private String itemId;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.QUANTITY)
    private String quantity;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.DATE_REQUIRED)
    private String dateRequired;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.APPROVAL_STATUS)
    private String approvalStatus;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.APPROVAL_DATE)
    private String approvalDate;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.REQUEST_ID)
    private String requestId;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.SUPERVISOR_ID)
    private String supervisor;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.IS_UPLOADED_TEACHER)
    private boolean isUploadedTeacher;

    @ColumnInfo(name = SchoolMaterialRequestsConstants.IS_UPLOADED_SUPERVISOR)
    private boolean isUploadedSupervisor;

    public SchoolMaterialRequests(String employeeNo, String employeeName, String employeeRequestType, String comment, String requestDate, String schoolId,
                                  String classId, String itemName, String itemId, String quantity, String dateRequired, String approvalStatus, String approvalDate,
                                  String requestId, String supervisor, boolean isUploadedTeacher, boolean isUploadedSupervisor) {
        this.employeeNo = employeeNo;
        this.employeeName = employeeName;
        this.employeeRequestType = employeeRequestType;
        this.comment = comment;
        this.requestDate = requestDate;
        this.schoolId = schoolId;
        this.classId = classId;
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.dateRequired = dateRequired;
        this.approvalStatus = approvalStatus;
        this.approvalDate = approvalDate;
        this.requestId = requestId;
        this.supervisor = supervisor;
        this.isUploadedTeacher = isUploadedTeacher;
        this.isUploadedSupervisor = isUploadedSupervisor;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
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

    public String getEmployeeRequestType() {
        return employeeRequestType;
    }

    public void setEmployeeRequestType(String employeeRequestType) {
        this.employeeRequestType = employeeRequestType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(String dateRequired) {
        this.dateRequired = dateRequired;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
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
