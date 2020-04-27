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

public class SyncTimeTableWorker extends Worker {
    private SyncTimeTableDao syncTimeTableDao;
    private String TAG = getClass().getSimpleName();

    public SyncTimeTableWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
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
                SyncTimeTables timeTables = new Gson().fromJson(reader, SyncTimeTables.class);
                for (SyncTimeTable timeTable : timeTables.timetables) {
                    syncTimeTableDao.insertSyncTimeTable(timeTable);
                    Log.d(TAG, "INSERTING: " + timeTable.toString());
                }
                reader.close();
                return Result.success();

            } catch (Exception e) {
                Log.e(TAG, "Exception parsing JSON", e);
                return Result.failure();
            } finally {
                connection.disconnect();
            }

        } catch (Exception e) {
            Log.e(TAG, "Exception creating connection", e);
            return Result.failure();
        }
    }

    public class SyncTimeTables {
        List<SyncTimeTable> timetables;
    }
}
