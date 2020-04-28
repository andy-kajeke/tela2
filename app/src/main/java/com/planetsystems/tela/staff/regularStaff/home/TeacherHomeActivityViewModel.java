package com.planetsystems.tela.staff.regularStaff.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;

public class TeacherHomeActivityViewModel extends AndroidViewModel {
    SyncTimeTableDao syncTimeTableDao;
    public TeacherHomeActivityViewModel(@NonNull Application application) {
        super(application);
        syncTimeTableDao = new MainRepository(application).getSyncTimeTableDao();
    }

    LiveData<List<SyncTimeTable>> timetables(String employeeNumber, String dayOfTheWeek) {
        return syncTimeTableDao.getSyncTimeTableByEmployeeIDForDay(employeeNumber, dayOfTheWeek);
    }
}
