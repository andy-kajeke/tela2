package com.planetsystems.tela.workers.upload;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.TelaRoomDatabase;


import java.util.List;


@SuppressWarnings("ALL")
public class SyncClockInTeacherUploadWorker extends Worker {
    SyncClockInDao syncClockInDao;
    public SyncClockInTeacherUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncClockInDao = telaRoomDatabase.getSyncClockInDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SyncClockIn> syncClockIns = syncClockInDao.getSyncClockInForBackUp(false);
        for(SyncClockIn syncClockIn: syncClockIns) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncClockIn.toString());
            // TODO: andrew will add codes here to upload each individual syncclock in to the backend
            try {
                String resp = Urls.POST(Urls.CLOCK_IN_UPLOAD_URL, new Gson().toJson(syncClockIn));
                if (resp == Urls.DID_WORK) {
                    syncClockIn.setUploaded(true);
                    syncClockInDao.updateSyncClockIn(syncClockIn);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
