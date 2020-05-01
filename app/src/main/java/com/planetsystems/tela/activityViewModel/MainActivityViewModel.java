package com.planetsystems.tela.activityViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private SyncTeacherDao syncTeacherDao;
    private MainRepository mainRepository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mainRepository = MainRepository.getInstance(application);
    }

    public LiveData<List<SyncTeacher>> teachers(){
        return mainRepository.getAllTeachers();
    }

    public  void insertTeacher(SyncTeacher syncTeacher){
        mainRepository.enrollTeacher(syncTeacher);
    }
}
