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
        List<SyncClockIn> syncClockIns = syncClockInDao.getSyncClockInsForBackUp();
        for(SyncClockIn syncClockIn: syncClockIns) {
            Log.d(getClass().getSimpleName(), "Uploading: " + syncClockIn.toString());
            // TODO: andrew will add codes here to upload each individual syncclock in to the backend
            try {
                syncClockIn.getEmployeeNo();
                syncClockIn.getEmployeeId();
                syncClockIn.getLatitude();
                syncClockIn.getLongitude();
                syncClockIn.getClockInDate();
                syncClockIn.getDay();
                syncClockIn.getClockInTime();
                syncClockIn.getSchoolId();

                Gson gson = new Gson();
                String json = gson.toJson(syncClockIn);

                String url = Urls.CLOCK_IN_UPLOAD_URL;
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

    public static String POST(String url, String jsontasks){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);


            // 5. set json to StringEntity
            StringEntity se = new StringEntity(jsontasks);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                //result = convertInputStreamToString(inputStream);
                result = "Did work!";
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

}
