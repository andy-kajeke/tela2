package com.planetsystems.tela.staff.administration.timeAttendance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;

import java.util.List;

public class TimeAttendanceListViewModel extends AndroidViewModel {
    private String TAG =this.getClass().getSimpleName();
    private SyncClockInDao syncClockInDao;
    private MainRepository mainRepository;

    public TimeAttendanceListViewModel(@NonNull Application application) {
        super(application);

       mainRepository = new MainRepository(application);
       // syncClockInDao = new MainRepository(application).getSyncClockInDao();
    }
    public LiveData<List<SyncClockIn>> teachers(String dateOfDay){
        return mainRepository.getClockedInByDateOfDay(dateOfDay);
    }
}
