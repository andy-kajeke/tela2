package com.planetsystems.tela;

import android.app.Application;
import android.content.Context;

//import androidx.constraintlayout.widget.Constraints;
import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.data.clockOut.SyncClockOutDao;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.SyncConfirmTimeOnTaskAttendanceDao;
import com.planetsystems.tela.data.employeeMaterialRequest.SyncEmployeeMaterialRequestDao;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDMDao;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.employeeRole.EmployeeRoleDao;
import com.planetsystems.tela.data.helprequest.HelpRequestDao;
import com.planetsystems.tela.data.smc.SyncSMCDao;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTaskDao;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;
import com.planetsystems.tela.workers.SyncTeacherWorker;

import java.util.List;

public class Repository {
    private static Repository INSTANCE;

    private EmployeeRoleDao employeeRoleDao;
    private SyncTeacherDao syncTeacherDao;
    private SyncClockOutDao syncClockOutDao;
    private SyncClockInDao syncClockInDao;
    private SyncConfirmTimeOnSiteAttendanceDao timeOnSiteAttendanceDao;
    private SyncConfirmTimeOnTaskAttendanceDao timeOnTaskAttendanceDao;
    private SyncEmployeeMaterialRequestDao syncEmployeeMaterialRequestDao;
    private SyncAttendanceRecordDao syncAttendanceRecordDao;
    private SyncEmployeeTimeOffRequestDMDao syncEmployeeTimeOffRequestDMDao;
    private SynTimeOnTaskDao synTimeOnTaskDao;
    private HelpRequestDao helpRequestDao;
    private SyncSMCDao syncSMCDao;
    private SyncTimeTableDao syncTimeTableDao;
    private Application application;

    public Repository(Application application) {
        this.application = application;
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        employeeRoleDao = telaRoomDatabase.getEmployeeRoleDao();
        syncTeacherDao = telaRoomDatabase.getSyncTeachersDao();
        syncClockOutDao = telaRoomDatabase.getSyncClockOuts();
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
        syncAttendanceRecordDao = telaRoomDatabase.getSyncAttendanceRecordsDao();
        timeOnSiteAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnSiteAttendanceDao();
        timeOnTaskAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnTaskAttendancesDao();
        syncEmployeeMaterialRequestDao = telaRoomDatabase.getSyncEmployeeMaterialRequest();
        syncAttendanceRecordDao = telaRoomDatabase.getSyncAttendanceRecordsDao();
        syncEmployeeTimeOffRequestDMDao = telaRoomDatabase.getSyncEmployeeTimeOffRequestDMsDao();
        synTimeOnTaskDao = telaRoomDatabase.getSyncTimeOnTaskDao();
        helpRequestDao = telaRoomDatabase.getHelpRequestDao();
        syncSMCDao = telaRoomDatabase.getSyncSMCDao();
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();

    }


    // made it singleton
    public static Repository getInstance(final Application  application) {
        if (INSTANCE == null) {
            synchronized (Repository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new Repository(application);
                }
            }
        }
        return INSTANCE;
    }

    //Enroll new staff member
    public void enrollTeacher(final SyncTeacher syncTeacher){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncTeacherDao.enrollTeacher(syncTeacher);
            }
        });
    }

    //Insert all staff members
    public void insertAllTeachers(final SyncTeacher syncTeacher){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
               // syncTeacherDao.insertAllTeachers(syncTeacher);
            }
        });
    }

    //Fetch all enrolled staff members
    public LiveData<List<SyncTeacher>> getAllTeachers(){
        return syncTeacherDao.getAllTeachers();
    }

    public void addNew(final SyncClockOut syncClockOut){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncClockOutDao.addNew(syncClockOut);
            }
        });
    }

    // picking data from the cloud
    public  void populateSyncTeacherFromApi() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SyncTeacherWorker.class)
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance(application).enqueue(workRequest);

    }

    public SyncTeacherDao getSyncTeacherDao() {
        return syncTeacherDao;
    }

    public LiveData<List<SyncTeacher>> getAllSyncTeacher() {
        return syncTeacherDao.getAllTeachers();
    }

    public void synClockInTeacherWithID(SyncClockIn clockIn){
        syncClockInDao.syncClockInTeacherWithID(clockIn);
    }

}
