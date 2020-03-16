package com.planetsystems.tela.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.planetsystems.tela.dao.EmployeeRoleDao;
import com.planetsystems.tela.dao.SyncTeachersDao;
import com.planetsystems.tela.enties.EmployeeRole;
import com.planetsystems.tela.enties.SyncTeachers;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.planetsystems.tela.constants.TelaDatabase.Tela_DB;

@Database(entities = {EmployeeRole.class, SyncTeachers.class}, version = 1, exportSchema = false)
public abstract class TelaRoomDatabase extends RoomDatabase {

    public abstract EmployeeRoleDao getEmployeeRoleDao();
    public abstract SyncTeachersDao getSyncTeachersDao();

    private  static volatile TelaRoomDatabase telaDB;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService db_executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TelaRoomDatabase getInstance(final Context context){
        if (telaDB ==null){
            synchronized (TelaRoomDatabase.class){
                if (telaDB == null){
                    telaDB = Room.databaseBuilder(context.getApplicationContext(), TelaRoomDatabase.class,
                            Tela_DB).build();
                }
            }
        }
        return telaDB;
    }
}
