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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.planetsystems.tela.constants.Urls.CLOCK_IN_UPLOAD_URL;

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
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(CLOCK_IN_UPLOAD_URL).openConnection();
            connection.setReadTimeout(20000);
            connection.setConnectTimeout(20000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new B

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        * Bellow we are picking data from database and looping through it*/
        List<SyncClockIn> syncClockIns = syncClockInDao.getSyncClockInsForBackUp();
        for(SyncClockIn syncClockIn: syncClockIns) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncClockIn.toString());
            try {
                Gson gson = new Gson();
                String json = gson.toJson(syncClockIn);

                String url = CLOCK_IN_UPLOAD_URL;
                String resp = POST( url,  json);

                Toast.makeText(getApplicationContext(),":"+resp,Toast.LENGTH_LONG).show();

            } catch(Exception e) {}
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

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(SyncClockInTeacherUploadWorker.class)
                .setConstraints(constraints)
                // TODO: The line bellow must be uncommented during project, this was commented out for testing
                .setInitialDelay(timeDifference, TimeUnit.MILLISECONDS)
                .build();
        WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
        return Result.success();
    }

}
