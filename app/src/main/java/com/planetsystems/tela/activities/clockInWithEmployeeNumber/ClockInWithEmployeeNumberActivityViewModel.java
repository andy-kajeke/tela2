package com.planetsystems.tela.activities.clockInWithEmployeeNumber;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class ClockInWithEmployeeNumberActivityViewModel extends AndroidViewModel {
    private LiveData<List<SyncTeacher>> syncTeachers;
    private MainRepository mainRepository;
    public ClockInWithEmployeeNumberActivityViewModel(@NonNull Application application) {
        super(application);
        mainRepository = MainRepository.getInstance(application);
        syncTeachers = mainRepository.getAllTeachers();
    }

    public LiveData<List<SyncTeacher>> getAllSyncTeacher() {
        return syncTeachers;
    }
}
