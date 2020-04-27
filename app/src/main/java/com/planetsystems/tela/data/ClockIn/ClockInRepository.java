package com.planetsystems.tela.data.ClockIn;

import android.app.Application;

import com.planetsystems.tela.data.TelaRoomDatabase;

public class ClockInRepository {
    private SyncClockInDao syncClockInDao;
    public ClockInRepository(TelaRoomDatabase telaRoomDatabase) {
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
    }
}
