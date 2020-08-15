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
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.data.clockOut.SyncClockOutDao;

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
public class SyncClockOutTeacherUploadWorker extends Worker {
    SyncClockOutDao syncClockOutDao;
    public SyncClockOutTeacherUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncClockOutDao = telaRoomDatabase.getSyncClockOutDao();
    }

    @SuppressLint("WrongThread")
    @NonNull
    @Override
    public Result doWork() {
        List<SyncClockOut> syncClockOutForBackUp = syncClockOutDao.getSyncClockOutForBackUp(false);
        for(SyncClockOut syncClockOut: syncClockOutForBackUp) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncClockOut.toString());
            // TODO: upload each individual syncclock out into the backend
            try {
                syncClockOut.getEmployeeNo();
                syncClockOut.getEmployeeId();
                syncClockOut.getLatitude();
                syncClockOut.getLongitude();
                syncClockOut.getClockOutDate();
                syncClockOut.getDay();
                syncClockOut.getClockOutTime();
                syncClockOut.getSchoolId();

                String resp = Urls.POST( Urls.CLOCK_OUT_UPLOAD_URL,  new Gson().toJson(syncClockOut));

                if (resp == Urls.DID_WORK) {
                    syncClockOut.setUploaded(true);
                    syncClockOutDao.update(syncClockOut);
                }

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
        }
        return Result.success();
    }
}
