package com.planetsystems.tela.data.Teacher;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;

import java.util.List;

public class SyncTeacherViewModel extends AndroidViewModel {
    private SyncTeacherDao syncTeacherDao;
    private Repository repository;
    public SyncTeacherViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<SyncTeacher>> teachers(){
        return repository.getAllTeachers();
    }

    public  void insertTeacher(SyncTeacher syncTeacher){
        repository.enrollTeacher(syncTeacher);
    }
}
