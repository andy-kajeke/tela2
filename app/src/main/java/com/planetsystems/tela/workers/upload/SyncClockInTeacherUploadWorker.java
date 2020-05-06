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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
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

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {

        /*
         * Bellow we are picking data from database and looping through it*/
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
