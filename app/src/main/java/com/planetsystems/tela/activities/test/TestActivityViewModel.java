package com.planetsystems.tela.activities.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;

public class TestActivityViewModel extends AndroidViewModel {
    SyncTeacherDao syncTeacherDao;
    SyncTimeTableDao syncTimeTableDao;
    public TestActivityViewModel(@NonNull Application application) {
        super(application);
        syncTeacherDao = new Repository(application).getSyncTeacherDao();
        syncTimeTableDao = new Repository(application).getSyncTimeTableDao();
    }

    LiveData<List<SyncTimeTable>> timetable() {
        return syncTimeTableDao.getSyncTimeTables();
    }
}
