package com.planetsystems.tela.activities.clockwithstaffid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class ClockInWithStaffIdActivityViewModel extends AndroidViewModel {
    Repository repository;
    public ClockInWithStaffIdActivityViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<List<SyncTeacher>> getAllSyncTeacher() {
        return repository.getAllSyncTeacher();
    }
}
