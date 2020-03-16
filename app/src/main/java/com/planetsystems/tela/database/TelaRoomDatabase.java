package com.planetsystems.tela.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.planetsystems.tela.dao.EmployeeRoleDao;
import com.planetsystems.tela.dao.SyncAttendanceRecordsDao;
import com.planetsystems.tela.dao.SyncClockOutsDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.dao.SyncConfirmTimeOnTaskAttendanceDao;
import com.planetsystems.tela.dao.SyncTeachersDao;
import com.planetsystems.tela.enties.EmployeeRole;
import com.planetsystems.tela.dao.SyncClockInDao;
import com.planetsystems.tela.enties.SyncClockIn;
import com.planetsystems.tela.enties.SyncTeacher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.planetsystems.tela.constants.TelaDatabase.Tela_DB;

@Database(entities = {
        EmployeeRole.class,
        SyncTeacher.class,
        SyncClockIn.class,
    }, version = 1, exportSchema = false)
public abstract class TelaRoomDatabase extends RoomDatabase {

    public abstract EmployeeRoleDao getEmployeeRoleDao();
    public abstract SyncTeachersDao getSyncTeachersDao();
    public abstract SyncClockOutsDao getSyncClockOuts();
    public abstract SyncClockInDao getSyncClockInDao();
    public abstract SyncConfirmTimeOnTaskAttendanceDao getSyncConfirmTimeOnTaskAttendancesDao();
    public abstract SyncConfirmTimeOnSiteAttendanceDao getSyncConfirmTimeOnSiteAttendanceDao();
    public abstract SyncAttendanceRecordsDao getSyncAttendanceRecordsDao();


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
