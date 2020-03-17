package com.planetsystems.tela.data.timetble;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncTimeTableConstant.TABLE_NAME)
public class SyncTimeTable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = SyncTimeTableConstant.ID)
    private String id;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name =  SyncTimeTableConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncTimeTableConstant.STATUS)
    private String status;

    @ColumnInfo(name = SyncTimeTableConstant.DEPLOYMENT_UNIT)
    private String deploymentUnit;

    @ColumnInfo(name = SyncTimeTableConstant.DEPLOYMENT_UNIT_ID)
    private String deploymentUnitId;

    @ColumnInfo(name = SyncTimeTableConstant.HEAD_TEACHER_PRESENT)
    private String headTeacherPresent;

    @ColumnInfo(name = SyncTimeTableConstant.P_1)
    private String p1;

    @ColumnInfo(name = SyncTimeTableConstant.P_2)
    private String p2;

    @ColumnInfo(name = SyncTimeTableConstant.P_3)
    private String p3;

    @ColumnInfo(name = SyncTimeTableConstant.P_4)
    private String p4;

    @ColumnInfo(name = SyncTimeTableConstant.P_5)
    private String p5;

    @ColumnInfo(name = SyncTimeTableConstant.P_6)
    private String p6;

    @ColumnInfo(name = SyncTimeTableConstant.P_7)
    private String p7;

    @ColumnInfo(name = SyncTimeTableConstant.SMC_CODE)
    private String smcCode;

    @ColumnInfo(name = SyncTimeTableConstant.STAFF_NOT_TEACHING)
    private String staffNotTeaching;

    @ColumnInfo(name = SyncTimeTableConstant.STAFF_PRESENT)
    private String staffPresent;

    @ColumnInfo(name = SyncTimeTableConstant.STAFF_TEACHING)
    private String staffTeaching;

    @ColumnInfo(name = SyncTimeTableConstant.ROW_VER)
    private String rowVer;

    @ColumnInfo(name = SyncTimeTableConstant.ROW_ID)
    private String rowId;

    public SyncTimeTable(@NonNull String id, String dateCreated, String dateUpdated, String status, String deploymentUnit, String deploymentUnitId, String headTeacherPresent, String p1, String p2, String p3, String p4, String p5, String p6, String p7, String smcCode, String staffNotTeaching, String staffPresent, String staffTeaching, String rowVer, String rowId) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.deploymentUnit = deploymentUnit;
        this.deploymentUnitId = deploymentUnitId;
        this.headTeacherPresent = headTeacherPresent;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.smcCode = smcCode;
        this.staffNotTeaching = staffNotTeaching;
        this.staffPresent = staffPresent;
        this.staffTeaching = staffTeaching;
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

    public String getHeadTeacherPresent() {
        return headTeacherPresent;
    }

    public void setHeadTeacherPresent(String headTeacherPresent) {
        this.headTeacherPresent = headTeacherPresent;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public String getP6() {
        return p6;
    }

    public void setP6(String p6) {
        this.p6 = p6;
    }

    public String getP7() {
        return p7;
    }

    public void setP7(String p7) {
        this.p7 = p7;
    }

    public String getSmcCode() {
        return smcCode;
    }

    public void setSmcCode(String smcCode) {
        this.smcCode = smcCode;
    }

    public String getStaffNotTeaching() {
        return staffNotTeaching;
    }

    public void setStaffNotTeaching(String staffNotTeaching) {
        this.staffNotTeaching = staffNotTeaching;
    }

    public String getStaffPresent() {
        return staffPresent;
    }

    public void setStaffPresent(String staffPresent) {
        this.staffPresent = staffPresent;
    }

    public String getStaffTeaching() {
        return staffTeaching;
    }

    public void setStaffTeaching(String staffTeaching) {
        this.staffTeaching = staffTeaching;
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
