package com.planetsystems.tela.staff.administration.timeAttendance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.ClockIn.ClockInRepository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.ClockIn.SyncClockInDao;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.SyncConfirmTimeOnSiteAttendance;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.TimeOnSiteAttendanceRepository;
import com.planetsystems.tela.data.clockOut.ClockOutRepository;

import java.util.List;

public class TimeAttendanceListViewModel extends AndroidViewModel {
    private String TAG =this.getClass().getSimpleName();
    private ClockInRepository clockInRepository;
    private TimeOnSiteAttendanceRepository timeOnSiteAttendanceRepository;

    public TimeAttendanceListViewModel(@NonNull Application application) {
        super(application);

        clockInRepository = new MainRepository(application).getClockInRepository();
        timeOnSiteAttendanceRepository = new MainRepository(application).getTimeOnSiteAttendanceRepository();
    }
    public LiveData<List<SyncClockIn>> teachers(String dateOfDay){
        return clockInRepository.getClockedInTeachersByDate(dateOfDay);
    }

    public LiveData<List<SyncClockIn>> onlyClockedIn(){
        return clockInRepository.getAllClockedIn();
    }

    public LiveData<List<SyncConfirmTimeOnSiteAttendance>> onSiteAttendance(){
        return timeOnSiteAttendanceRepository.getAllTimeOnSiteAttendance();
    }

    public void insertTimeOnSiteAttendance(SyncConfirmTimeOnSiteAttendance syncConfirmTimeOnSiteAttendance){
        timeOnSiteAttendanceRepository.insertTimeOnSiteAttendance(syncConfirmTimeOnSiteAttendance);
    }
}
