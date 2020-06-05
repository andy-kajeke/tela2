package com.planetsystems.tela.data.confirmTimeOnTaskAttendance;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.SyncConfirmTimeOnSiteAttendanceDao;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTaskDao;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ConfirmTimeOnTaskRepository {
    private SyncConfirmTimeOnTaskAttendanceDao syncConfirmTimeOnTaskAttendanceDao;
    private static volatile ConfirmTimeOnTaskRepository INSTANCE;

    private ConfirmTimeOnTaskRepository(TelaRoomDatabase telaRoomDatabase) {
        syncConfirmTimeOnTaskAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnTaskAttendancesDao();
    }

    public static ConfirmTimeOnTaskRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (ConfirmTimeOnTaskRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new ConfirmTimeOnTaskRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertConfirmTimeOneTask(final SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncConfirmTimeOnTaskAttendanceDao.addNewConfirmation(syncConfirmTimeOnTaskAttendance);
            }
        });
    }

    public LiveData<List<SyncConfirmTimeOnTaskAttendance>> getAllTimeOnTask() {
        return syncConfirmTimeOnTaskAttendanceDao.getAllRecords();
    }

}
