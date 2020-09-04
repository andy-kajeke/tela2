package com.planetsystems.tela.data.attendance;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LearnerRepository {
    private SyncAttendanceRecordDao syncAttendanceRecordDao;
    private static volatile LearnerRepository INSTANCE;

    private LearnerRepository(TelaRoomDatabase telaRoomDatabase) {
        syncAttendanceRecordDao = telaRoomDatabase.getSyncAttendanceRecordsDao();
    }

    public static LearnerRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (LearnerRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new LearnerRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertLearnerAttendance(final SyncAttendanceRecord syncAttendanceRecord) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                syncAttendanceRecordDao.addNewLearnerAttendanceRecord(syncAttendanceRecord);
            }
        });
    }

    public LiveData<List<SyncAttendanceRecord>> getAllLearnerRecords() {
        return syncAttendanceRecordDao.getAllRecords();
    }

    public LiveData<List<SyncAttendanceRecord>> getAttendanceByClassAndDate(final String classUnit, final String date){
        return syncAttendanceRecordDao.getAttendanceByClassAndDate(classUnit, date);
    }

}
