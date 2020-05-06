package com.planetsystems.tela.workers.upload;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.TelaRoomDatabase;


import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.planetsystems.tela.constants.Urls.CLOCK_IN_UPLOAD_URL;

public class SyncClockInTeacherUploadWorker extends Worker {
    SyncClockInDao syncClockInDao;
    public final String TAG = getClass().getSimpleName();


    public SyncClockInTeacherUploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        syncClockInDao = TelaRoomDatabase.getInstance(getApplicationContext()).getSyncClockInDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        List<SyncClockIn> clockIns = syncClockInDao.getSyncClockInForBackUp(false);
        Log.d(TAG, "Getting Ready to Upload Clock Ins One By One");
        for (SyncClockIn clockIn: clockIns) {
            HttpURLConnection urlConnection = null;
            try {
                JsonObject postData = new JsonObject();
                postData.addProperty("employeeNo", clockIn.getEmployeeNo());
                postData.addProperty("employeeId", clockIn.getEmployeeId());
                postData.addProperty("latitude", clockIn.getLatitude());
                postData.addProperty("longitude", clockIn.getLongitude());
                postData.addProperty("clockInDate", clockIn.getClockInDate());
                postData.addProperty("day", clockIn.getClockInDate());
                postData.addProperty("clockInTime", clockIn.getClockInTime());
                postData.addProperty("schoolId", clockIn.getSchoolId());

                URL url = new URL(CLOCK_IN_UPLOAD_URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setChunkedStreamingMode(0);

                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(postData.toString());
                writer.flush();

//                if (urlConnection.getResponseCode() != 201) {
//                    throw new IOException("Invalid Response From Server: " + urlConnection.getResponseCode());
//                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null ) {
                    Log.i(TAG, line);
                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "There was error in opening connection");
            } finally {
                assert urlConnection != null;
                urlConnection.disconnect();
            }
        }
        return Result.success();
    }

    private void writeStream(OutputStream outputStream, SyncClockIn clockIn) {
        String clockInString = new Gson().toJson(clockIn);

        byte[] input = new byte[0];
        try {
            input = clockInString.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readStream(InputStream inputStream) {

    }
}
