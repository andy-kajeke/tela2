package com.planetsystems.tela.activities.fingerprint;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/*
* The activity class can be a Lifecycle of observer, i was supposed to use viewmodel
* but i can instantiate it in the activity extends activity not appcompatactivity
* am here by trying to emulate view model but a fake one
* */
public class FingerPrintActivityViewModel {
    public FingerPrintActivityViewModel(@NonNull Application application) {
    }
}
