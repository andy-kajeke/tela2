package com.planetsystems.tela.data.timetable;

import com.planetsystems.tela.data.TelaRoomDatabase;

public class TimeTableRepository {
    private static TimeTableRepository INSTANCE;
    private SyncTimeTableDao syncTimeTableDao;

    private TimeTableRepository(TelaRoomDatabase telaRoomDatabase) {
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
    }

    public static TimeTableRepository getInstance(final TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null ) {
            synchronized (TimeTableRepository.class) {
                if (INSTANCE == null ) {
                    INSTANCE = new TimeTableRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }
}
