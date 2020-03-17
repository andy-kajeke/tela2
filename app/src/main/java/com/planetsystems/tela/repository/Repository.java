package com.planetsystems.tela.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.clockOut.SyncClockOutsDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnTaskAttendanceDao;
import com.planetsystems.tela.dao.SyncEmployeeMaterialRequestDao;
import com.planetsystems.tela.dao.SyncEmployeeTimeOffRequestDMsDao;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.clockOut.SyncClockOuts;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class Repository {

    private EmployeeRoleDao employeeRoleDao;
    private SyncTeacherDao syncTeacherDao;
    private SyncClockOutsDao syncClockOutsDao;
    private SyncClockInDao syncClockInDao;
    private SyncConfirmTimeOnSiteAttendanceDao timeOnSiteAttendanceDao;
    private SyncConfirmTimeOnTaskAttendanceDao timeOnTaskAttendanceDao;
    private SyncEmployeeMaterialRequestDao syncEmployeeMaterialRequestDao;
    private SyncAttendanceRecordDao syncAttendanceRecordDao;
    private SyncEmployeeTimeOffRequestDMsDao syncEmployeeTimeOffRequestDMsDao;

    public Repository(Application application) {
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        employeeRoleDao = telaRoomDatabase.getEmployeeRoleDao();
        syncTeacherDao = telaRoomDatabase.getSyncTeachersDao();
        syncClockOutsDao = telaRoomDatabase.getSyncClockOuts();
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
        syncAttendanceRecordDao = telaRoomDatabase.getSyncAttendanceRecordsDao();
        timeOnSiteAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnSiteAttendanceDao();
        timeOnTaskAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnTaskAttendancesDao();
        syncEmployeeMaterialRequestDao = telaRoomDatabase.getSyncEmployeeMaterialRequest();
        syncAttendanceRecordDao = telaRoomDatabase.getSyncAttendanceRecordsDao();
        syncEmployeeTimeOffRequestDMsDao = telaRoomDatabase.getSyncEmployeeTimeOffRequestDMsDao();
    }

    //Enroll new staff member

    public void addNewStaff(final SyncTeacher syncTeacher){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncTeacherDao.addNewStaff(syncTeacher);
            }
        });
    }

    //Fetch all enrolled staff members
    public LiveData<List<SyncTeacher>> getAllTeachers(){
        return syncTeacherDao.getAllTeachers();
    }

    public void addNew(final SyncClockOuts syncClockOuts){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncClockOutsDao.addNew(syncClockOuts);
            }
        });
    }
}
