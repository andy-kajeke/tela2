package com.planetsystems.tela;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.ClockIn.ClockInRepository;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;
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
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MainRepository {
    private static MainRepository INSTANCE;

    private EmployeeRoleDao employeeRoleDao;
    private SyncTeacherDao syncTeacherDao;
    private SyncClockOutDao syncClockOutDao;
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
    private ExecutorService executorService;

    private ClockInRepository clockInRepository;

    public MainRepository(Application application) {
        this.application = application;
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(application);
        employeeRoleDao = telaRoomDatabase.getEmployeeRoleDao();
        syncTeacherDao = telaRoomDatabase.getSyncTeachersDao();
        syncClockOutDao = telaRoomDatabase.getSyncClockOutDao();
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
        executorService = TelaRoomDatabase.db_executor;

        clockInRepository = ClockInRepository.getInstance(telaRoomDatabase);

    }


    // made it singleton
    public static MainRepository getInstance(final Application  application) {
        if (INSTANCE == null) {
            synchronized (MainRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new MainRepository(application);
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

    //Fetch all enrolled staff members
    public LiveData<List<SyncTeacher>> getAllTeachers(){
        return syncTeacherDao.getAllTeachers();
    }

    //Get all timetables
    public LiveData<List<SyncTimeTable>> getAllSyncTimeTable(){
        return syncTimeTableDao.getSyncTimeTables();
    }

    public SyncTimeTableDao getSyncTimeTableDao() {
        return syncTimeTableDao;
    }

    public SyncTeacherDao getSyncTeacherDao() {
        return syncTeacherDao;
    }

    public LiveData<List<SyncTeacher>> getAllSyncTeacher() {
        return syncTeacherDao.getAllTeachers();
    }


    public LiveData<List<SyncClockOut>> getAlreadyClockOutTeachers(){
        return syncClockOutDao.getClockOutTeachers();
    }

    public ClockInRepository getClockInRepository() {
        return clockInRepository;
    }
}
