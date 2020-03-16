package com.planetsystems.tela.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.dao.EmployeeRoleDao;
import com.planetsystems.tela.dao.SyncClockInDao;
import com.planetsystems.tela.dao.SyncTeachersDao;
import com.planetsystems.tela.database.TelaRoomDatabase;
import com.planetsystems.tela.enties.SyncTeacher;

import java.util.List;

public class Repository {

    private EmployeeRoleDao employeeRoleDao;
    private SyncTeachersDao syncTeachersDao;
    private SyncClockInDao syncClockInDao;

    public Repository(Application application) {
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        employeeRoleDao = telaRoomDatabase.getEmployeeRoleDao();
        syncTeachersDao = telaRoomDatabase.getSyncTeachersDao();
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
    }

    public void addNewStaff(final SyncTeacher syncTeacher){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncTeachersDao.addNewStaff(syncTeacher);
            }
        });
    }

    public LiveData<List<SyncTeacher>> getAllTeachers(){
        return syncTeachersDao.getAllTeachers();
    }
}
