package com.planetsystems.tela;

import android.app.Application;

import androidx.lifecycle.LiveData;

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

import java.util.List;

public class Repository {

    private EmployeeRoleDao employeeRoleDao;
    private SyncTeacherDao syncTeacherDao;
    private SyncClockOutDao syncClockOutDao;
    private SyncClockInDao syncClockInDao;
    private SyncConfirmTimeOnSiteAttendanceDao timeOnSiteAttendanceDao;
    private SyncConfirmTimeOnTaskAttendanceDao timeOnTaskAttendanceDao;
    private SyncEmployeeMaterialRequestDao syncEmployeeMaterialRequestDao;
    private SyncAttendanceRecordDao syncAttendanceRecordDao;
    private SyncEmployeeTimeOffRequestDMDao syncEmployeeTimeOffRequestDMDao;

    public Repository(Application application) {
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

    public void addNew(final SyncClockOut syncClockOut){
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncClockOutDao.addNew(syncClockOut);
            }
        });
    }
}