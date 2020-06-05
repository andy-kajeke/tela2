package com.planetsystems.tela.activities.staff.administration.taskAttendance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.ConfirmTimeOnTaskRepository;
import com.planetsystems.tela.data.confirmTimeOnTaskAttendance.SyncConfirmTimeOnTaskAttendance;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.TimeOnTaskRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.utils.DynamicData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SupervisorObservationsViewModel extends AndroidViewModel {
    ConfirmTimeOnTaskRepository confirmTimeOnTaskRepository;

    public SupervisorObservationsViewModel(@NonNull Application application) {
        super(application);
        confirmTimeOnTaskRepository = MainRepository.getInstance(application).getConfirmTimeOnTaskRepository();
    }

    public void insertNewConfirmations(SyncConfirmTimeOnTaskAttendance syncConfirmTimeOnTaskAttendance){
        confirmTimeOnTaskRepository.insertConfirmTimeOneTask(syncConfirmTimeOnTaskAttendance);
    }

    public LiveData<List<SyncConfirmTimeOnTaskAttendance>> getAllTimeOnTask() {
        return confirmTimeOnTaskRepository.getAllTimeOnTask();
    }
}
