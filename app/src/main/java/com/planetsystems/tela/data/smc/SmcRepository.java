package com.planetsystems.tela.data.smc;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTaskDao;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SmcRepository {
    private SyncSMCDao syncSMCDao;
    private static volatile SmcRepository INSTANCE;

    private SmcRepository(TelaRoomDatabase telaRoomDatabase) {
        syncSMCDao = telaRoomDatabase.getSyncSMCDao();
    }

    public static SmcRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (SmcRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new SmcRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertSmcObservation(final SyncSMC syncSMC) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncSMCDao.inertSmcObservations(syncSMC);
            }
        });
    }

    public LiveData<List<SyncSMC>> getAllSmcRecords() {
        return syncSMCDao.getSmcRecords();
    }

}
