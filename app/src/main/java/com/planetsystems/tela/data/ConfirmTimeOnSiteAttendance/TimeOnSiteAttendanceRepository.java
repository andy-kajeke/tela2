package com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.SyncConfirmTimeOnTaskAttendance;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTaskDao;

import java.util.List;

public class TimeOnSiteAttendanceRepository {
    private SyncConfirmTimeOnSiteAttendanceDao syncConfirmTimeOnSiteAttendanceDao;
    private static volatile TimeOnSiteAttendanceRepository INSTANCE;

    private TimeOnSiteAttendanceRepository(TelaRoomDatabase telaRoomDatabase) {
        syncConfirmTimeOnSiteAttendanceDao = telaRoomDatabase.getSyncConfirmTimeOnSiteAttendanceDao();
    }

    public static TimeOnSiteAttendanceRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (TimeOnSiteAttendanceRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new TimeOnSiteAttendanceRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertTimeOnSiteAttendance(final SyncConfirmTimeOnSiteAttendance syncConfirmTimeOnSiteAttendance) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncConfirmTimeOnSiteAttendanceDao.insertSyncConfirmTimeOnSiteAttendance(syncConfirmTimeOnSiteAttendance);
            }
        });
    }

    public LiveData<List<SyncConfirmTimeOnSiteAttendance>> getAllTimeOnSiteAttendance() {
        return syncConfirmTimeOnSiteAttendanceDao.getSyncConfirmTimeOnSiteAttendance();
    }

    public LiveData<List<SyncConfirmTimeOnSiteAttendance>> getEmployeeIDAndDate(final String employeeNumber, final String date){
        return syncConfirmTimeOnSiteAttendanceDao.getEmployeeIDAndDate(employeeNumber, date);
    }
}
