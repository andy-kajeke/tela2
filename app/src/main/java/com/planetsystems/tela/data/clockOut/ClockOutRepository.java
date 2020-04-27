package com.planetsystems.tela.data.clockOut;

import android.app.Application;

public class ClockOutRepository {
    private ClockOutRepository INSTANCE;
    private SyncClockOutDao syncClockOutDao;
    private ClockOutRepository(Application application) {

    }
}
