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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.planetsystems.tela.constants.Urls.TIMETABLE;

public class SyncTimeTableWorker extends Worker {
    private SyncTimeTableDao syncTimeTableDao;
    public SyncTimeTableWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        TelaRoomDatabase telaRoomDatabase = TelaRoomDatabase.getInstance(context);
        syncTimeTableDao = telaRoomDatabase.getSyncTimeTableDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("Worker", "Beginning work =================================================");
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(TIMETABLE).openConnection();
            try {

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                SyncTimeTables syncTimeTables = new Gson().fromJson(reader, SyncTimeTables.class);
                reader.close();
                for(int i = 0; i < syncTimeTables.timeTable.size(); i++) {
                    syncTimeTableDao.insertSyncTimeTable(syncTimeTables.timeTable.get(i));
                    Log.d(getClass().getSimpleName(), "Save timetable" + syncTimeTables.timeTable.get(i).toString());
                    System.out.println("====================================");
                    System.out.println(syncTimeTables.timeTable.get(i).toString());
                    System.out.println("====================================");
                }
                return Result.success();
            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
            } finally {
                connection.disconnect();
            }
        }  catch (IOException e) {
            Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
        }
        return Result.retry();
    }

    public static class SyncTimeTables{
        List<SyncTimeTable> timeTable;
    }
}
