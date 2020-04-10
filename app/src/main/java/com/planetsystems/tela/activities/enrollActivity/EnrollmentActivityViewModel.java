package com.planetsystems.tela.activities.enrollActivity;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class EnrollmentActivityViewModel extends AndroidViewModel {
    private Repository repository;
    private String firstName;
    private String lastName;
    private String initials;
    private String phoneNumber;
    private String emailAddress;
    private String nationalID;
    private String gender;
    private List<SyncTeacher> teachers;
    public EnrollmentActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

    }

    boolean enrollTeacher(SyncTeacher syncTeacher) {
        if (!isEnrolled(teachers, syncTeacher)) {
            repository.enrollTeacher(syncTeacher);
            return true;
        }
        return false;
    }

    void saveState(Bundle bundle, List<SyncTeacher> enrolledTeachers){
        firstName = bundle.getString(EnrollmentActivity.FIRST_NAME);
        lastName = bundle.getString(EnrollmentActivity.LAST_NAME);
        initials = bundle.getString(EnrollmentActivity.INITIALS);
        phoneNumber = bundle.getString(EnrollmentActivity.PHONE_NUMBER);
        emailAddress = bundle.getString(EnrollmentActivity.EMAIL_ADDRESS);
        nationalID = bundle.getString(EnrollmentActivity.NATIONAL_ID);
        gender = bundle.getString(EnrollmentActivity.GENDER);
        teachers = enrolledTeachers;

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

    List<SyncTeacher> getEnrolledTeacher() {
        return teachers;
    }

    void setEnrolledTeachers(List<SyncTeacher> teachers) {
        this.teachers = teachers;
    }

    LiveData<List<SyncTeacher>> getSynTeachers() {
        return repository.getAllSyncTeacher();
    }

    // checks whether teacher is already enrolled
    private boolean isEnrolled(List<SyncTeacher> enrolledTeachers, SyncTeacher teacher) {
        for (SyncTeacher enrolledTeacher: enrolledTeachers) {
            if (enrolledTeacher.getNationalId().equals(teacher.getNationalId())) {
                // teacher already enrolled
                return false;
            }
        }
        return true;
    }
}
