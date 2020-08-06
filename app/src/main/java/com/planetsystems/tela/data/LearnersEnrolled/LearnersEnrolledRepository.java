package com.planetsystems.tela.data.LearnersEnrolled;

import androidx.lifecycle.LiveData;

import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolled;
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolledDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.List;

public class LearnersEnrolledRepository {
    private LearnersEnrolledDao learnersEnrolledDao;
    private static volatile LearnersEnrolledRepository INSTANCE;

    private LearnersEnrolledRepository(TelaRoomDatabase telaRoomDatabase) {
        learnersEnrolledDao = telaRoomDatabase.getLearnersEnrolledDao();
    }

    public static LearnersEnrolledRepository getInstance(TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (LearnersEnrolledRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new LearnersEnrolledRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }

    public void insertLearnersEnrolled(final LearnersEnrolled learnersEnrolled) {
        TelaRoomDatabase.db_executor.execute(new Runnable() {
            @Override
            public void run() {
                learnersEnrolledDao.enrolledLearners(learnersEnrolled);
            }
        });
    }

    public LiveData<List<LearnersEnrolled>> getLearnersEnrolled() {
        return learnersEnrolledDao.getAllLearners();
    }
}
