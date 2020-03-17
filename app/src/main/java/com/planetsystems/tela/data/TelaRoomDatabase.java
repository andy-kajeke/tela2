package com.planetsystems.tela.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;
import com.planetsystems.tela.dao.SyncClockOutsDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnTaskAttendanceDao;
import com.planetsystems.tela.dao.SyncEmployeeMaterialRequestDao;
import com.planetsystems.tela.dao.SyncEmployeeTimeOffRequestDMsDao;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.employeeRole.EmployeeRole;
import com.planetsystems.tela.data.employeeRole.EmployeeRoleDao;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.enties.SyncClockOuts;
import com.planetsystems.tela.enties.SyncConfirmTimeOnSiteAttendance;
import com.planetsystems.tela.enties.SyncConfirmTimeOnTaskAttendance;
import com.planetsystems.tela.enties.SyncEmployeeMaterialRequest;
import com.planetsystems.tela.enties.SyncEmployeeTimeOffRequestDMs;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.planetsystems.tela.constants.TelaDatabase.Tela_DB;

@Database(entities = {
        EmployeeRole.class,
        SyncTeacher.class,
        SyncClockIn.class,
        SyncClockOuts.class,
        SyncEmployeeTimeOffRequestDMs.class,
        SyncAttendanceRecord.class,
        SyncConfirmTimeOnSiteAttendance.class,
        SyncConfirmTimeOnTaskAttendance.class,
        SyncEmployeeMaterialRequest.class,
    }, version = 1, exportSchema = false)
public abstract class TelaRoomDatabase extends RoomDatabase {

    public abstract EmployeeRoleDao getEmployeeRoleDao();
    public abstract SyncTeacherDao getSyncTeachersDao();
    public abstract SyncClockOutsDao getSyncClockOuts();
    public abstract SyncClockInDao getSyncClockInDao();
    public abstract SyncConfirmTimeOnTaskAttendanceDao getSyncConfirmTimeOnTaskAttendancesDao();
    public abstract SyncConfirmTimeOnSiteAttendanceDao getSyncConfirmTimeOnSiteAttendanceDao();
    public abstract SyncAttendanceRecordDao getSyncAttendanceRecordsDao();
    public abstract SyncEmployeeMaterialRequestDao getSyncEmployeeMaterialRequest();
    public abstract SyncEmployeeTimeOffRequestDMsDao getSyncEmployeeTimeOffRequestDMsDao();


    private  static volatile TelaRoomDatabase tela_DB;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService db_executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TelaRoomDatabase getInstance(final Context context){
        if (tela_DB ==null){
            synchronized (TelaRoomDatabase.class){
                if (tela_DB == null){
                    tela_DB = Room.databaseBuilder(context.getApplicationContext(), TelaRoomDatabase.class,
                            Tela_DB).build();
                }
            }
        }
        return tela_DB;
    }
}
