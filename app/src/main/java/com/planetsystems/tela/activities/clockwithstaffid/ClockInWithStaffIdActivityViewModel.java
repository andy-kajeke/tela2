package com.planetsystems.tela.activities.clockwithstaffid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.planetsystems.tela.Repository;

public class ClockInWithStaffIdActivityViewModel extends AndroidViewModel {
    Repository repository;
    public ClockInWithStaffIdActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }
}
