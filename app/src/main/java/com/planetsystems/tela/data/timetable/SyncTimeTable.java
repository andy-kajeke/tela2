package com.planetsystems.tela.data.timetable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncTimeTableConstant.TABLE_NAME)
public class SyncTimeTable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncTimeTableConstant.PRIMARY_KEY_COLUMN_NAME)
    private int primaryKey;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncTimeTableConstant.STATUS)
    private String status;

    @ColumnInfo(name = SyncTimeTableConstant.CLASS_ID)
    private String classId;

    @ColumnInfo(name = SyncTimeTableConstant.CLASS_UNIT)
    private String classUnit;

    @ColumnInfo(name =  SyncTimeTableConstant.DAY)
    private String day;

    @ColumnInfo(name = SyncTimeTableConstant.EMPLOYEE_NAME)
    private String employName;

    @ColumnInfo(name = SyncTimeTableConstant.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SyncTimeTableConstant.EMPLOYEE_NO)
    private String employeeNo;

    @ColumnInfo(name = SyncTimeTableConstant.END_TIME)
    private String endTime;

    @ColumnInfo(name = SyncTimeTableConstant.SCHOOL_ID)
    private String schoolId;

    @ColumnInfo(name = SyncTimeTableConstant.SCHOOL_NAME)
    private String schoolName;

    @ColumnInfo(name = SyncTimeTableConstant.START_TIME)
    private String startTime;

    @ColumnInfo(name = SyncTimeTableConstant.SUBJECT)
    private String subject;

    @ColumnInfo(name = SyncTimeTableConstant.SUBJECT_ID)
    private String subjectId;

    @ColumnInfo(name = SyncTimeTableConstant.TASK_ID)
    private String taskId;

    @ColumnInfo(name = SyncTimeTableConstant.TASK_NAME)
    private String taskName;

    public SyncTimeTable(String dateCreated, String dateUpdated, String status, String classId, String classUnit, String day, String employName, String employeeId, String employeeNo, String endTime, String schoolId, String schoolName, String startTime, String subject, String subjectId, String taskId, String taskName) {
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.classId = classId;
        this.classUnit = classUnit;
        this.day = day;
        this.employName = employName;
        this.employeeId = employeeId;
        this.employeeNo = employeeNo;
        this.endTime = endTime;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.startTime = startTime;
        this.subject = subject;
        this.subjectId = subjectId;
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassUnit() {
        return classUnit;
    }

    public void setClassUnit(String classUnit) {
        this.classUnit = classUnit;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmployName() {
        return employName;
    }

    public void setEmployName(String employName) {
        this.employName = employName;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
