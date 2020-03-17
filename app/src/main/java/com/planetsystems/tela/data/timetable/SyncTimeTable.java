package com.planetsystems.tela.data.timetable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = SyncTimeTableConstant.TABLE_NAME)
public class SyncTimeTable {
    @NonNull
    @ColumnInfo(name = SyncTimeTableConstant.ID)
    @PrimaryKey
    private String id;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SyncTimeTableConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SyncTimeTableConstant.STATUS)
    private String status;

    @ColumnInfo(name = SyncTimeTableConstant.DAY)
    private String day;

    @ColumnInfo(name = SyncTimeTableConstant.EMPLOYEE_NAME)
    private String employeeName;

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

    @ColumnInfo(name = SyncTimeTableConstant.ROW_ID)
    private String rowId;

    @ColumnInfo(name = SyncTimeTableConstant.ROW_VER)
    private String rowVer;

    public SyncTimeTable(@NonNull String id, String dateCreated, String dateUpdated, String status, String day, String employeeName, String employeeId, String employeeNo, String endTime, String schoolId, String schoolName, String startTime, String subject, String subjectId, String taskId, String taskName, String rowId, String rowVer) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.day = day;
        this.employeeName = employeeName;
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
        this.rowId = rowId;
        this.rowVer = rowVer;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getRowVer() {
        return rowVer;
    }

    public void setRowVer(String rowVer) {
        this.rowVer = rowVer;
    }
}
