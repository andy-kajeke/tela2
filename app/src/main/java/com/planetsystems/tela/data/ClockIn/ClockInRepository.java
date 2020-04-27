package com.planetsystems.tela.data.ClockIn;

import android.app.Application;

import com.planetsystems.tela.data.TelaRoomDatabase;

public class ClockInRepository {
    private static ClockInRepository INSTANCE;
    private SyncClockInDao syncClockInDao;

    private ClockInRepository(TelaRoomDatabase telaRoomDatabase) {
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
    }

    public static ClockInRepository getInstance(final TelaRoomDatabase telaRoomDatabase) {
        if (INSTANCE == null) {
            synchronized (ClockInRepository.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new ClockInRepository(telaRoomDatabase);
                }
            }
        }
        return INSTANCE;
    }
}
