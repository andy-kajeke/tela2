package com.planetsystems.tela.workers.fetch;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.planetsystems.tela.constants.Urls.SYNC_TIME_TABLE_URL;

public class SynTimeTableWorker extends Worker {
    private SyncTimeTableDao syncTimeTableDao;
    private String TAG = getClass().getSimpleName();

    public SynTimeTableWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
    }
    @NonNull
    @Override
    public Result doWork() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(SYNC_TIME_TABLE_URL).openConnection();
            try {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                SyncTeacherWorker.SyncTeachers timeTables = new Gson().fromJson(reader, SyncTeacherWorker.SyncTeachers.class);
                Log.d(TAG, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                Log.d(TAG, String.valueOf(timeTables.teachers.size()));
                Log.d(TAG, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                reader.close();

            } catch (Exception e) {
                Log.e(TAG, "Exception parsing JSON", e);
            } finally {
                connection.disconnect();
            }

        } catch (Exception e) {
            Log.e(TAG, "Exception creating connection", e);
        }
        return null;
    }

    public class SyncTimeTables {
        List<SyncTimeTable> timeTables;
    }
}
