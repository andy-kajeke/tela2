package com.planetsystems.tela.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.dao.EmployeeRoleDao;
import com.planetsystems.tela.dao.SyncClockInDao;
import com.planetsystems.tela.dao.SyncClockOutsDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnTaskAttendancesDao;
import com.planetsystems.tela.dao.SyncTeachersDao;
import com.planetsystems.tela.database.TelaRoomDatabase;
import com.planetsystems.tela.enties.SyncClockOuts;
import com.planetsystems.tela.enties.SyncConfirmTimeOnSiteAttendance;
import com.planetsystems.tela.enties.SyncTeacher;

import java.util.List;

public class Repository {

    private EmployeeRoleDao employeeRoleDao;
    private SyncTeachersDao syncTeachersDao;
    private SyncClockOutsDao syncClockOutsDao;
    private SyncClockInDao syncClockInDao;
    private SyncConfirmTimeOnSiteAttendanceDao timeOnSiteAttendanceDao;
    private SyncConfirmTimeOnTaskAttendancesDao timeOnTaskAttendancesDao;

    public Repository(Application application) {
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        employeeRoleDao = telaRoomDatabase.getEmployeeRoleDao();
        syncTeachersDao = telaRoomDatabase.getSyncTeachersDao();
        syncClockOutsDao = telaRoomDatabase.getSyncClockOuts();
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
        timeOnSiteAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnSiteAttendanceDao();
        timeOnTaskAttendancesDao = telaRoomDatabase.getSyncConfirmTimeOnTaskAttendancesDao();
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
