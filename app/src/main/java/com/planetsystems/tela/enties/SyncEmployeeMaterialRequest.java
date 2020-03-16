package com.planetsystems.tela.enties;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncEmployeeMaterialRequestName;

@Entity(tableName = SyncEmployeeMaterialRequestName.TABLE_NAME)
public class SyncEmployeeMaterialRequest {

    @PrimaryKey
    @ColumnInfo(name = SyncEmployeeMaterialRequestName.ID)
    @NonNull
    private String id;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.STATUS)
    private String status;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.APPROVED_STATUS)
    private String approvedStatus;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.CONFIRMATION)
    private String confirmation;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.COMMENT)
    private String comment;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.DATE_REQUIRED)
    private String dateRequired;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.DEPLOYMENT_SITE)
    private String deploymentSite;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.DEPLOYMENT_SITE_ID)
    private String deploymentSiteId;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.DEPLOYMENT_UNIT)
    private String deploymentUnit;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.DEPLOYMENT_UNIT_ID)
    private String deploymentUnitId;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.EMPLOYEE)
    private String employee;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.EMPLOYEE_REQUEST_TYPE)
    private String employeeRequestType;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.ITEM_ID)
    private String itemId;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.ITEM_NAME)
    private String itemName;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.QUANTITY)
    private String quantity;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.REQUEST_DATE)
    private String requestDate;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.APPROVAL_DATE)
    private String approval;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = SyncEmployeeMaterialRequestName.ROW_ID)
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
