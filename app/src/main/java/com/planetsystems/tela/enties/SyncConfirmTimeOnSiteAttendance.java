package com.planetsystems.tela.enties;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.constants.SyncConfirmTimeOnSiteAttendanceName;

@Entity(tableName = SyncConfirmTimeOnSiteAttendanceName.TABLE_NAME)
public class SyncConfirmTimeOnSiteAttendance {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.ID)
    private String id;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.STATUS)
    private String status;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.EMPLOYEE_NO)
    private String employeeNo;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.SUPERVISION_DATE)
    private String supervisionDate;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.SUPERVISION_DAY)
    private String supervisionDay;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.SUPERVISION_STATUS)
    private String supervisionStatus;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.SUPERVISION_COMMENT)
    private String supervisionComment;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.SUPERVISOR_ID)
    private String supervisionId;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = SyncConfirmTimeOnSiteAttendanceName.ROW_ID)
    private String rowId;

    public SyncConfirmTimeOnSiteAttendance(@NonNull String id, String dateCreated, String dateUpdated, String status, String employeeId, String employeeNo, String supervisionDate, String supervisionDay, String supervisionStatus, String supervisionComment, String supervisionId, String rowVer, String rowId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.employeeId = employeeId;
        this.employeeNo = employeeNo;
        this.supervisionDate = supervisionDate;
        this.supervisionDay = supervisionDay;
        this.supervisionStatus = supervisionStatus;
        this.supervisionComment = supervisionComment;
        this.supervisionId = supervisionId;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getSupervisionDate() {
        return supervisionDate;
    }

    public void setSupervisionDate(String supervisionDate) {
        this.supervisionDate = supervisionDate;
    }

    public String getSupervisionDay() {
        return supervisionDay;
    }

    public void setSupervisionDay(String supervisionDay) {
        this.supervisionDay = supervisionDay;
    }

    public String getSupervisionStatus() {
        return supervisionStatus;
    }

    public void setSupervisionStatus(String supervisionStatus) {
        this.supervisionStatus = supervisionStatus;
    }

    public String getSupervisionComment() {
        return supervisionComment;
    }

    public void setSupervisionComment(String supervisionComment) {
        this.supervisionComment = supervisionComment;
    }

    public String getSupervisionId() {
        return supervisionId;
    }

    public void setSupervisionId(String supervisionId) {
        this.supervisionId = supervisionId;
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
