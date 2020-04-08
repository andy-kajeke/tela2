package com.planetsystems.tela.services.syncteachers;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.planetsystems.tela.constants.Urls.SYNC_TEACHER_URL;

public class LoadSyncTeacherThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(SYNC_TEACHER_URL).openConnection();
            try {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                SyncTeachers teachers = new Gson().fromJson(reader, SyncTeachers.class);
                reader.close();
                for (int i = 0; i < 20; i++) {
                    Log.d("LeadThread", teachers.teachers.get(i).getFirstName());
                }
            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
            } finally {
                connection.disconnect();
            }
        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), "Exception parsing JSON", e);
        }
    }
}
