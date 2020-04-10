package com.planetsystems.tela.activities.enrollActivity;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

public class EnrollmentActivityViewModel extends AndroidViewModel {
    private Repository repository;
    private String firstName;
    private String lastName;
    private String initials;
    private String phoneNumber;
    private String emailAddress;
    private String nationalID;
    private String gender;
    public EnrollmentActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

    }

    public void enrollTeacher(SyncTeacher syncTeacher) {
        repository.enrollTeacher(syncTeacher);
    }

    public void saveState(Bundle bundle){
        firstName = bundle.getString(EnrollmentActivity.FIRST_NAME);
        lastName = bundle.getString(EnrollmentActivity.LAST_NAME);
        initials = bundle.getString(EnrollmentActivity.INITIALS);
        phoneNumber = bundle.getString(EnrollmentActivity.PHONE_NUMBER);
        emailAddress = bundle.getString(EnrollmentActivity.EMAIL_ADDRESS);
        nationalID = bundle.getString(EnrollmentActivity.NATIONAL_ID);
        gender = bundle.getString(EnrollmentActivity.GENDER);

    }

    public void setState(Bundle bundle) {
        bundle.putString(EnrollmentActivity.FIRST_NAME, firstName);
        bundle.putString(EnrollmentActivity.LAST_NAME, lastName);
        bundle.putString(EnrollmentActivity.INITIALS, initials);
        bundle.putString(EnrollmentActivity.PHONE_NUMBER, phoneNumber);
        bundle.putString(EnrollmentActivity.EMAIL_ADDRESS, emailAddress);
        bundle.putString(EnrollmentActivity.NATIONAL_ID, nationalID);
        bundle.putString(EnrollmentActivity.GENDER, gender);
    }
}
