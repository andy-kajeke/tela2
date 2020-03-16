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

}
