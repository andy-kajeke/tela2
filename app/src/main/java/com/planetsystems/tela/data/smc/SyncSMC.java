package com.planetsystems.tela.data.smc;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncSMCConstant.TABLE_NAME)
public class SyncSMC {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = SyncSMCConstant.ID)
    private int id;

    @ColumnInfo(name = SyncSMCConstant.COMMENT)
    private String comment;

    @ColumnInfo(name = SyncSMCConstant.VISIT_DATE)
    private String visitDate;

    @ColumnInfo(name = SyncSMCConstant.DEPLOYMENT_UNIT_ID)
    private String deploymentUnitId;

    @ColumnInfo(name = SyncSMCConstant.HEAD_TEACHER_PRESENT)
    private String headTeacherPresent;

    @ColumnInfo(name = SyncSMCConstant.P_1)
    private String p1;

    @ColumnInfo(name = SyncSMCConstant.P_2)
    private String p2;

    @ColumnInfo(name = SyncSMCConstant.P_3)
    private String p3;

    @ColumnInfo(name = SyncSMCConstant.P_4)
    private String p4;

    @ColumnInfo(name = SyncSMCConstant.P_5)
    private String p5;

    @ColumnInfo(name = SyncSMCConstant.P_6)
    private String p6;

    @ColumnInfo(name = SyncSMCConstant.P_7)
    private String p7;

    @ColumnInfo(name = SyncSMCConstant.SMC_CODE)
    private String smcCode;

    @ColumnInfo(name = SyncSMCConstant.STAFF_NOT_TEACHING)
    private String staffNotTeaching;

    @ColumnInfo(name = SyncSMCConstant.STAFF_PRESENT)
    private String staffPresent;

    @ColumnInfo(name = SyncSMCConstant.STAFF_TEACHING)
    private String staffTeaching;

    @ColumnInfo(name = SyncSMCConstant.IS_UPLOADED)
    private boolean isUploaded;

    public SyncSMC(String comment, String visitDate, String deploymentUnitId, String headTeacherPresent, String p1, String p2, String p3, String p4,
                   String p5, String p6, String p7, String smcCode, String staffNotTeaching, String staffPresent, String staffTeaching, boolean isUploaded) {
        this.comment = comment;
        this.visitDate = visitDate;
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
        this.isUploaded = isUploaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
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

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }
}
