package com.planetsystems.tela;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.dao.SyncTeachersDao;
import com.planetsystems.tela.enties.SyncTeacher;
import com.planetsystems.tela.repository.Repository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private SyncTeachersDao syncTeachersDao;
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
