package com.planetsystems.tela.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.dao.EmployeeRoleDao;
import com.planetsystems.tela.dao.SyncAttendanceRecordsDao;
import com.planetsystems.tela.dao.SyncClockInDao;
import com.planetsystems.tela.dao.SyncClockOutsDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnTaskAttendanceDao;
import com.planetsystems.tela.dao.SyncEmployeeMaterialRequestDao;
import com.planetsystems.tela.dao.SyncEmployeeTimeOffRequestDMsDao;
import com.planetsystems.tela.dao.SyncTeachersDao;
import com.planetsystems.tela.database.TelaRoomDatabase;
import com.planetsystems.tela.enties.SyncClockOuts;
import com.planetsystems.tela.enties.SyncTeacher;

import java.util.List;

public class Repository {

    private EmployeeRoleDao employeeRoleDao;
    private SyncTeachersDao syncTeachersDao;
    private SyncClockOutsDao syncClockOutsDao;
    private SyncClockInDao syncClockInDao;
    private SyncConfirmTimeOnSiteAttendanceDao timeOnSiteAttendanceDao;
    private SyncConfirmTimeOnTaskAttendanceDao timeOnTaskAttendanceDao;
    private SyncEmployeeMaterialRequestDao syncEmployeeMaterialRequestDao;
    private SyncAttendanceRecordsDao syncAttendanceRecordsDao;
    private SyncEmployeeTimeOffRequestDMsDao syncEmployeeTimeOffRequestDMsDao;

    public Repository(Application application) {
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        employeeRoleDao = telaRoomDatabase.getEmployeeRoleDao();
        syncTeachersDao = telaRoomDatabase.getSyncTeachersDao();
        syncClockOutsDao = telaRoomDatabase.getSyncClockOuts();
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
        syncAttendanceRecordsDao = telaRoomDatabase.getSyncAttendanceRecordsDao();
        timeOnSiteAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnSiteAttendanceDao();
        timeOnTaskAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnTaskAttendancesDao();
        syncEmployeeMaterialRequestDao = telaRoomDatabase.getSyncEmployeeMaterialRequest();
        syncAttendanceRecordsDao = telaRoomDatabase.getSyncAttendanceRecordsDao();
        syncEmployeeTimeOffRequestDMsDao = telaRoomDatabase.getSyncEmployeeTimeOffRequestDMsDao();
    }

    //Enroll new staff member

    public void addNewStaff(final SyncTeacher syncTeacher){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncTeachersDao.addNewStaff(syncTeacher);
            }
        });
    }

    //Fetch all enrolled staff members
    public LiveData<List<SyncTeacher>> getAllTeachers(){
        return syncTeachersDao.getAllTeachers();
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
