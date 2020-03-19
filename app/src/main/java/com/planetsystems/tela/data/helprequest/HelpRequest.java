package com.planetsystems.tela.data.helprequest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = HelpRequestConstant.TABLE_NAME)
public class HelpRequest {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = HelpRequestConstant.ID)
    private String id;

    @ColumnInfo(name = HelpRequestConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = HelpRequestConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = HelpRequestConstant.STATUS)
    private String status;

    @ColumnInfo(name = HelpRequestConstant.APPROVED_STATUS)
    private String approvalStatus;

    @ColumnInfo(name = HelpRequestConstant.CONFIRMATION)
    private String confirmation;

    @ColumnInfo(name = HelpRequestConstant.COMMENT)
    private String comment;

    @ColumnInfo(name = HelpRequestConstant.DEPLOYMENT_SITE)
    private String deploymentSite;

    @ColumnInfo(name = HelpRequestConstant.DEPLOYMENT_SITE_ID)
    private String deploymentSiteId;

    @ColumnInfo(name = HelpRequestConstant.HELP_CATEGORY)
    private String helpCategory;

    @ColumnInfo(name = HelpRequestConstant.PRIORITY)
    private String priority;

    @ColumnInfo(name = HelpRequestConstant.STAFF_CODE)
    private String staffCode;

    @ColumnInfo(name = HelpRequestConstant.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = HelpRequestConstant.ROW_ID)
    private String rowId;

    @ColumnInfo(name = HelpRequestConstant.REQUESTED_DATE)
    private String requestDate;

    @ColumnInfo(name = HelpRequestConstant.APPROVAL_DATE)
    private String approvalDate;

    public HelpRequest(@NonNull String id, String dateCreated, String dateUpdated, String status, String approvalStatus, String confirmation, String comment, String deploymentSite, String deploymentSiteId, String helpCategory, String priority, String staffCode, String rowVer, String rowId, String requestDate, String approvalDate) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.approvalStatus = approvalStatus;
        this.confirmation = confirmation;
        this.comment = comment;
        this.deploymentSite = deploymentSite;
        this.deploymentSiteId = deploymentSiteId;
        this.helpCategory = helpCategory;
        this.priority = priority;
        this.staffCode = staffCode;
        this.rowVer = rowVer;
        this.rowId = rowId;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getHelpCategory() {
        return helpCategory;
    }

    public void setHelpCategory(String helpCategory) {
        this.helpCategory = helpCategory;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
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

    @NonNull
    @Override
    public String toString() {
        return this.id;
    }
}
