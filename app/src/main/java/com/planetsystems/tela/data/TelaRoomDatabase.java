package com.planetsystems.tela.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.planetsystems.tela.data.LearnersEnrolled.LearnersEnrolled;
import com.planetsystems.tela.data.LearnersEnrolled.LearnersEnrolledDao;
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolled;
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolledDao;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecordDao;
import com.planetsystems.tela.data.clockOut.SyncClockOutDao;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.SyncConfirmTimeOnTaskAttendanceDao;
import com.planetsystems.tela.data.employeeMaterialRequest.SyncEmployeeMaterialRequestDao;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDMDao;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.employeeRole.EmployeeRole;
import com.planetsystems.tela.data.employeeRole.EmployeeRoleDao;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.SyncConfirmTimeOnSiteAttendance;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.SyncConfirmTimeOnTaskAttendance;
import com.planetsystems.tela.data.employeeMaterialRequest.SyncEmployeeMaterialRequest;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.helprequest.HelpRequest;
import com.planetsystems.tela.data.helprequest.HelpRequestDao;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesDao;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequests;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequestsDao;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterialsDao;
import com.planetsystems.tela.data.smc.SyncSMC;
import com.planetsystems.tela.data.smc.SyncSMCDao;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTaskDao;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;
import com.planetsystems.tela.data.logs.ExecutionLog;
import com.planetsystems.tela.data.logs.ExecutionLogDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.planetsystems.tela.constants.TelaDatabase.Tela_DB;

@Database(entities = {
        EmployeeRole.class,
        SyncTeacher.class,
        SyncClockIn.class,
        SyncClockOut.class,
        SyncSchoolClasses.class,
        SyncEmployeeTimeOffRequestDM.class,
        SyncAttendanceRecord.class,
        SyncConfirmTimeOnSiteAttendance.class,
        SyncConfirmTimeOnTaskAttendance.class,
        SyncEmployeeMaterialRequest.class,
        SynTimeOnTask.class,
        HelpRequest.class,
        SyncSMC.class,
        SyncTimeTable.class,
        TeachersEnrolled.class,
        LearnersEnrolled.class,
        SchoolMaterials.class,
        SchoolMaterialRequests.class,
        ExecutionLog.class
    }, version = 1, exportSchema = false)
public abstract class TelaRoomDatabase extends RoomDatabase {

    public abstract EmployeeRoleDao getEmployeeRoleDao();
    public abstract SyncTeacherDao getSyncTeachersDao();
    public abstract SyncClockOutDao getSyncClockOutDao();
    public abstract SyncClockInDao getSyncClockInDao();
    public abstract SyncSchoolClassesDao getSyncSchoolClassesDao();
    public abstract SyncConfirmTimeOnTaskAttendanceDao getSyncConfirmTimeOnTaskAttendancesDao();
    public abstract SyncConfirmTimeOnSiteAttendanceDao getSyncConfirmTimeOnSiteAttendanceDao();
    public abstract SyncAttendanceRecordDao getSyncAttendanceRecordsDao();
    public abstract SyncEmployeeMaterialRequestDao getSyncEmployeeMaterialRequest();
    public abstract SyncEmployeeTimeOffRequestDMDao getSyncEmployeeTimeOffRequestDMsDao();
    public abstract SynTimeOnTaskDao getSyncTimeOnTaskDao();
    public abstract HelpRequestDao getHelpRequestDao();
    public abstract SyncSMCDao getSyncSMCDao();
    public abstract SyncTimeTableDao getSyncTimeTableDao();
    public abstract TeachersEnrolledDao getTeachersEnrolledDao();
    public abstract LearnersEnrolledDao getLearnersEnrolledDao();
    public abstract SchoolMaterialsDao getSchoolMaterialsDao();
    public abstract SchoolMaterialRequestsDao getSchoolMaterialRequestsDao();
    public abstract ExecutionLogDao getExecutionLogDao();


    private  static volatile TelaRoomDatabase tela_DB;
    public static final int NUMBER_OF_THREADS = 5;
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
