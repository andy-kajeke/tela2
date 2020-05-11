package com.planetsystems.tela.staff.regularStaff.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTaskDao;
import com.planetsystems.tela.data.timeOnTask.TimeOnTaskRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

import java.util.List;

public class TeacherHomeActivityViewModel extends AndroidViewModel {
    SyncTimeTableDao syncTimeTableDao;
    TimeOnTaskRepository timeOnTaskRepository;
    private List<SynTimeOnTask> tasksConfirmation;

    public TeacherHomeActivityViewModel(@NonNull Application application) {
        super(application);
        syncTimeTableDao = new MainRepository(application).getSyncTimeTableDao();
        timeOnTaskRepository = new MainRepository(application).getTimeOnTaskRepository();
    }

    LiveData<List<SyncTimeTable>> timetables(String employeeNumber, String dayOfTheWeek) {
        return syncTimeTableDao.getSyncTimeTableByEmployeeIDForDay(employeeNumber, dayOfTheWeek);
    }

//    public void timeOnTask(SynTimeOnTask synTimeOnTask){
//        timeOnTaskRepository.insertSyncTimeOneTask(synTimeOnTask);
//    }

    boolean timeOnTask(SynTimeOnTask synTimeOnTask) {
        if (!isConfirmed(tasksConfirmation, synTimeOnTask)) {
            timeOnTaskRepository.insertSyncTimeOneTask(synTimeOnTask);
            return true;
        }
        return false;
    }

    // checks whether teacher is already enrolled
    private boolean isConfirmed(List<SynTimeOnTask> confirmTasks, SynTimeOnTask tasks) {
        for (SynTimeOnTask confirmedTasks: confirmTasks) {
            if (confirmedTasks.getTransactionDate().equals(tasks.getTransactionDate()) && confirmedTasks.getEmployeeNumber().equals(tasks.getEmployeeNumber())) {
                // teacher already submitted
                return false;
            }
        }
        return true;
    }

    void setTasksConfirmation(List<SynTimeOnTask> tasks) {
        this.tasksConfirmation = tasks;
    }
}
