package com.planetsystems.tela.staff.administration.timeAttendance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.Teacher.SyncTeacherModel;

import java.util.List;

public class TimeAttendanceListViewModel extends AndroidViewModel {
    private String TAG =this.getClass().getSimpleName();
    private SyncClockInDao syncClockInDao;
    private Repository repository;

    public TimeAttendanceListViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
    }
    public LiveData<List<SyncClockIn>> teachers(){
//        return repository.getAllClockedInStaff();
        return null;
    }
}
