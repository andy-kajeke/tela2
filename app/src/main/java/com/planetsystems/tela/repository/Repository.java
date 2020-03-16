package com.planetsystems.tela.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.dao.EmployeeRoleDao;
import com.planetsystems.tela.dao.SyncClockOutsDao;
import com.planetsystems.tela.dao.SyncTeachersDao;
import com.planetsystems.tela.database.TelaRoomDatabase;
import com.planetsystems.tela.enties.SyncClockOuts;
import com.planetsystems.tela.enties.SyncTeachers;

import java.util.List;

public class Repository {

    private EmployeeRoleDao employeeRoleDao;
    private SyncTeachersDao syncTeachersDao;
    private SyncClockOutsDao syncClockOutsDao;

    public Repository(Application application) {
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        employeeRoleDao = telaRoomDatabase.getEmployeeRoleDao();
        syncTeachersDao = telaRoomDatabase.getSyncTeachersDao();
        syncClockOutsDao = telaRoomDatabase.getSyncClockOuts();
    }

    //Enroll new staff member
    public void addNewStaff(final SyncTeachers syncTeachers){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncTeachersDao.addNewStaff(syncTeachers);
            }
        });
    }

    //Fetch all enrolled staff members
    public LiveData<List<SyncTeachers>> getAllTeachers(){
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
