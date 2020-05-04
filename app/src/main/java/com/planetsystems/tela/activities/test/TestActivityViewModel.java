package com.planetsystems.tela.activities.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClassesDao;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;

public class TestActivityViewModel extends AndroidViewModel {
    SyncTeacherDao syncTeacherDao;
    SyncTimeTableDao syncTimeTableDao;
    SyncSchoolClassesDao syncSchoolClassesDao;
    public TestActivityViewModel(@NonNull Application application) {
        super(application);
//        syncTeacherDao = new MainRepository(application).getSyncTeacherDao();
//        syncTimeTableDao = new MainRepository(application).getSyncTimeTableDao();
        syncSchoolClassesDao = new MainRepository(application).getSyncSchoolClassesDao();
    }

    LiveData<List<SyncSchoolClasses>> schoolClasses() {
        return syncSchoolClassesDao.getAllSchoolClasses();
    }
}
