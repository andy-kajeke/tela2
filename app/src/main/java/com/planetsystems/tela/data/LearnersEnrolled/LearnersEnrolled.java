package com.planetsystems.tela.data.LearnersEnrolled;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = LearnersEnrolledConstants.TABLE_NAME)
public class LearnersEnrolled {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = LearnersEnrolledConstants.COLUMN_PRIMARY_KEY)
    private int primaryKey;

    @ColumnInfo(name = LearnersEnrolledConstants.COLUMN_SCHOOL_ID)
    private String schoolId;

    @ColumnInfo(name = LearnersEnrolledConstants.COLUMN_DEVICE_NO)
    private String deviceNo;

    @ColumnInfo(name = LearnersEnrolledConstants.COLUMN_CLASS_ID)
    private String classId;

    @ColumnInfo(name = LearnersEnrolledConstants.COLUMN_TOTAL_MALE)
    private String totalMale;

    @ColumnInfo(name = LearnersEnrolledConstants.COLUMN_TOTAL_FEMALE)
    private String totalFemale;

    @ColumnInfo(name = LearnersEnrolledConstants.COLUMN_SUBMISSION_DATE)
    private String submissionDate;

    @ColumnInfo(name = LearnersEnrolledConstants.COLUMN_IS_UPLOADED)
    private boolean is_uploaded;

    public LearnersEnrolled(String schoolId, String deviceNo, String classId, String totalMale, String totalFemale, String submissionDate, boolean is_uploaded) {
        this.schoolId = schoolId;
        this.deviceNo = deviceNo;
        this.classId = classId;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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
