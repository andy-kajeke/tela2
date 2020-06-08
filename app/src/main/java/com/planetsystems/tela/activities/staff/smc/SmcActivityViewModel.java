package com.planetsystems.tela.activities.staff.smc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.smc.SmcRepository;
import com.planetsystems.tela.data.smc.SyncSMC;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timeOnTask.TimeOnTaskRepository;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.utils.DynamicData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SmcActivityViewModel extends AndroidViewModel {
    SmcRepository smcRepository;

    public SmcActivityViewModel(@NonNull Application application) {
        super(application);
        smcRepository = MainRepository.getInstance(application).getSmcRepository();
    }

    public void insertSmcObservations(SyncSMC syncSMC){
        smcRepository.insertSmcObservation(syncSMC);
    }

    LiveData<List<SyncSMC>> getSmcRecords() {
        return smcRepository.getAllSmcRecords();
    }

}
