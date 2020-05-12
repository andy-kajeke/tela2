package com.planetsystems.tela.data.timeOnTask;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;

public class TimeOnTaskRepository {
    private SynTimeOnTaskDao synTimeOnTaskDao;
    private static volatile TimeOnTaskRepository INSTANCE;
    private SyncTimeTableDao syncTimeTableDao;

    private TimeOnTaskRepository(TelaRoomDatabase telaRoomDatabase) {
        synTimeOnTaskDao = telaRoomDatabase.getSyncTimeOnTaskDao();
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
    }

    public static TimeOnTaskRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (TimeOnTaskRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new TimeOnTaskRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertSyncTimeOneTask(final SynTimeOnTask synTimeOnTask) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                synTimeOnTaskDao.insertSynTimeOnTask(synTimeOnTask);
            }
        });
    }

    public LiveData<List<SynTimeOnTask>> getAllTimeOnTask() {
        return synTimeOnTaskDao.getSynTimeOnTasks();
    }


    public LiveData<List<SyncTimeTable>> getSyncTimeTableByEmployeeIDForDay(String employeeNumber, String dayOfTheWeek) {
        return syncTimeTableDao.getSyncTimeTableByEmployeeIDForDay(employeeNumber, dayOfTheWeek);
    }
}
