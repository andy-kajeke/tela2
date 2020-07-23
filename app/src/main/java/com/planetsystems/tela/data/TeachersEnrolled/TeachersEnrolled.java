package com.planetsystems.tela.data.TeachersEnrolled;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.data.Teacher.SyncTeacherTableConstants;

@Entity(tableName = TeachersEnrolledConstants.TABLE_NAME)
public class TeachersEnrolled {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TeachersEnrolledConstants.COLUMN_PRIMARY_KEY)
    private int primaryKey;

    @ColumnInfo(name = TeachersEnrolledConstants.COLUMN_SCHOOL_ID)
    private String schoolId;

    @ColumnInfo(name = TeachersEnrolledConstants.COLUMN_DEVICE_NO)
    private String deviceNo;

    @ColumnInfo(name = TeachersEnrolledConstants.COLUMN_TOTAL_MALE)
    private String totalMale;

    @ColumnInfo(name = TeachersEnrolledConstants.COLUMN_TOTAL_FEMALE)
    private String totalFemale;

    @ColumnInfo(name = TeachersEnrolledConstants.COLUMN_SUBMISSION_DATE)
    private String submissionDate;

    @ColumnInfo(name = TeachersEnrolledConstants.COLUMN_IS_UPLOADED)
    private boolean is_uploaded;

    public TeachersEnrolled(String schoolId, String deviceNo, String totalMale, String totalFemale, String submissionDate, boolean is_uploaded) {
        this.schoolId = schoolId;
        this.deviceNo = deviceNo;
        this.totalMale = totalMale;
        this.totalFemale = totalFemale;
        this.submissionDate = submissionDate;
        this.is_uploaded = is_uploaded;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getTotalMale() {
        return totalMale;
    }

    public void setTotalMale(String totalMale) {
        this.totalMale = totalMale;
    }

    public String getTotalFemale() {
        return totalFemale;
    }

    public void setTotalFemale(String totalFemale) {
        this.totalFemale = totalFemale;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public boolean isIs_uploaded() {
        return is_uploaded;
    }

    public void setIs_uploaded(boolean is_uploaded) {
        this.is_uploaded = is_uploaded;
    }
}
