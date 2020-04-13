package com.planetsystems.tela.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.TelaRoomDatabase;

import java.util.Calendar;
import java.util.List;

public class SyncClockInTeacherUploadWorker extends Worker {
    SyncClockInDao syncClockInDao;
    /*
    * This worker will run periodically usually 3 times a day to upload
    * Clock ins for a give school,
    * It queries all clock in data and pushes them to the backend
    * */
    public SyncClockInTeacherUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
    }

    @NonNull
    @Override
    public Result doWork() {

        /*
        * Bellow we are picking data from database and looping through it*/
        List<SyncClockIn> syncClockIns = syncClockInDao.getSyncClockInsForBackUp();
        for(SyncClockIn syncClockIn: syncClockIns) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncClockIn.toString());
        }

        /*
         * Since this worker should be syncing data everyday, we need to set it
         * for that purpose, here it get the current date and i set to to
         * upload data at midnight when there is internet connection*/
        Calendar currentTimeAndDate = Calendar.getInstance();
        Calendar nextTimeAndDate = Calendar.getInstance();
        // Set the next execution around 05:00:00 but andrew can change it to any preferred time
        nextTimeAndDate.set(Calendar.HOUR_OF_DAY, 5);
        nextTimeAndDate.set(Calendar.MINUTE, 0);
        nextTimeAndDate.set(Calendar.SECOND, 0);
        if (nextTimeAndDate.before(currentTimeAndDate)) {
            nextTimeAndDate.add(Calendar.HOUR_OF_DAY, 24);
        }

        // this is the next time we shall upload data to the backend
        long timeDifference = nextTimeAndDate.getTimeInMillis() -
                nextTimeAndDate.getTimeInMillis();
        return null;
    }
}
