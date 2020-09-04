package com.planetsystems.tela.data.confirmTimeOnTaskAttendance;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;

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

    public LiveData<List<SyncConfirmTimeOnTaskAttendance>> getEmployeeNoAndDate(final String employeeNumber, final String date){
        return syncConfirmTimeOnTaskAttendanceDao.getEmployeeIDAndDate(employeeNumber, date);
    }

}
