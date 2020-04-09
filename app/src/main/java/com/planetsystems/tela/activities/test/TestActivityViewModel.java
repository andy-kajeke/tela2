package com.planetsystems.tela.activities.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;

import java.util.List;

public class TestActivityViewModel extends AndroidViewModel {
    SyncTeacherDao syncTeacherDao;
    public TestActivityViewModel(@NonNull Application application) {
        super(application);
        syncTeacherDao = new Repository(application).getSyncTeacherDao();
    }

    LiveData<List<SyncTeacher>> getAllTeachers() {
        return syncTeacherDao.getAllTeachers();
    }
}
