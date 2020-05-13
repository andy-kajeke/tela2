package com.planetsystems.tela.activities.staff.regularStaff.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.TimeOnTaskRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;
import com.planetsystems.tela.utils.DynamicData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TeacherHomeActivityViewModel extends AndroidViewModel {
    TimeOnTaskRepository timeOnTaskRepository;
    private List<SynTimeOnTask> tasksConfirmation;

    public TeacherHomeActivityViewModel(@NonNull Application application) {
        super(application);
        timeOnTaskRepository = MainRepository.getInstance(application).getTimeOnTaskRepository();
    }

    LiveData<List<SyncTimeTable>> getSyncTimeTableByEmployeeIDForDay(String employeeNumber, String dayOfTheWeek) {
        return timeOnTaskRepository.getSyncTimeTableByEmployeeIDForDay(employeeNumber, dayOfTheWeek);
    }

    public LiveData<List<SynTimeOnTask>> getTimeOnTasks(){
        return timeOnTaskRepository.getAllTimeOnTask();
    }

    public void postToSyncTimeOnTask(SynTimeOnTask synTimeOnTask) {

        try {
            SynTimeOnTask timeOnTask = timeOnTaskRepository.getSynTimeOnTaskWithEmployeeNumberAndDate(
                    synTimeOnTask.getEmployeeNumber(),
                    DynamicData.getDate(),
                    synTimeOnTask.getTaskId()
            );
            if (timeOnTask == null ) timeOnTaskRepository.insertSyncTimeOneTask(synTimeOnTask);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<SynTimeOnTask>> tasksWithPresentActionStatus(String employeeNumber, String transactionDate, String actionStatus){
        return timeOnTaskRepository.getTaskWiThActionStatusPresent(employeeNumber, transactionDate, actionStatus);
    }
}
