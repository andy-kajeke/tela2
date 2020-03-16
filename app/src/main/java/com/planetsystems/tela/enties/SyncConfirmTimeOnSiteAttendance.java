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
}
