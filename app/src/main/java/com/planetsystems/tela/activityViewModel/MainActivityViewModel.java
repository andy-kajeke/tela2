package com.planetsystems.tela.activityViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private SyncTeacherDao syncTeacherDao;
    private Repository repository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<SyncTeacher>> teachers(){
        return repository.getAllTeachers();
    }

    public  void insertTeacher(SyncTeacher syncTeacher){
        repository.addNewStaff(syncTeacher);
    }
}
