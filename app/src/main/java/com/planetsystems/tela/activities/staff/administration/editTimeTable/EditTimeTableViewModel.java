package com.planetsystems.tela.activities.staff.administration.editTimeTable;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.SyncTeacherDao;
import com.planetsystems.tela.data.timeOnTask.TimeOnTaskRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.TimeTableRepository;

import java.util.List;

public class EditTimeTableViewModel extends AndroidViewModel {
    TimeOnTaskRepository timeOnTaskRepository;
    TimeTableRepository timeTableRepository;

    public EditTimeTableViewModel(@NonNull Application application) {
        super(application);

        timeOnTaskRepository = MainRepository.getInstance(application).getTimeOnTaskRepository();
        timeTableRepository = MainRepository.getInstance(application).getTimeTableRepository();
    }
    public LiveData<List<SyncTimeTable>> timetable(String day, String classUnit){
        return timeOnTaskRepository.getSyncTimeTableByEmployeeIDForClassUnit(day, classUnit);
    }

    public void updateTimeTable(String startTime, String endTime, String employeeNo, String employeeName, int primaryKey){
        timeTableRepository.updateTimeTable(startTime, endTime, employeeNo, employeeName, primaryKey);
    }
}
