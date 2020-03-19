package com.planetsystems.tela.data.employeeMaterialRequest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncEmployeeMaterialRequestConstant.TABLE_NAME)
public class SyncEmployeeMaterialRequest {

    @PrimaryKey
    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.ID)
    @NonNull
    private String id;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.STATUS)
    private String status;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.APPROVED_STATUS)
    private String approvedStatus;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.CONFIRMATION)
    private String confirmation;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.COMMENT)
    private String comment;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.DATE_REQUIRED)
    private String dateRequired;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.DEPLOYMENT_SITE)
    private String deploymentSite;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.DEPLOYMENT_SITE_ID)
    private String deploymentSiteId;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.DEPLOYMENT_UNIT)
    private String deploymentUnit;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.DEPLOYMENT_UNIT_ID)
    private String deploymentUnitId;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.EMPLOYEE)
    private String employee;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.EMPLOYEE_REQUEST_TYPE)
    private String employeeRequestType;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.ITEM_ID)
    private String itemId;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.ITEM_NAME)
    private String itemName;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.QUANTITY)
    private String quantity;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.REQUEST_DATE)
    private String requestDate;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.APPROVAL_DATE)
    private String approval;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = SyncEmployeeMaterialRequestConstant.ROW_ID)
    private String rowId;

    public SyncEmployeeMaterialRequest(@NonNull String id, String dateCreated, String dateUpdated, String status, String approvedStatus, String confirmation, String comment, String dateRequired, String deploymentSite, String deploymentSiteId, String deploymentUnit, String deploymentUnitId, String employee, String employeeId, String employeeRequestType, String itemId, String itemName, String quantity, String requestDate, String approval, String rowVer, String rowId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.approvedStatus = approvedStatus;
        this.confirmation = confirmation;
        this.comment = comment;
        this.dateRequired = dateRequired;
        this.deploymentSite = deploymentSite;
        this.deploymentSiteId = deploymentSiteId;
        this.deploymentUnit = deploymentUnit;
        this.deploymentUnitId = deploymentUnitId;
        this.employee = employee;
        this.employeeId = employeeId;
        this.employeeRequestType = employeeRequestType;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.requestDate = requestDate;
        this.approval = approval;
        this.rowVer = rowVer;
        this.rowId = rowId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovedStatus() {
        return approvedStatus;
    }

    public void setApprovedStatus(String approvedStatus) {
        this.approvedStatus = approvedStatus;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateRequired() {
        return dateRequired;
    }

    public void setDateRequired(String dateRequired) {
        this.dateRequired = dateRequired;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getRowVer() {
        return rowVer;
    }

    public void setRowVer(String rowVer) {
        this.rowVer = rowVer;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }
}
