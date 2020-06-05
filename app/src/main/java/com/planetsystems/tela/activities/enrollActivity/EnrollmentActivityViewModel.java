package com.planetsystems.tela.activities.enrollActivity;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.TeacherRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EnrollmentActivityViewModel extends AndroidViewModel {
    private MainRepository mainRepository;
    private String firstName;
    private String lastName;
    private String initials;
    private String phoneNumber;
    private String emailAddress;
    private String nationalID;
    private String gender;
    private List<SyncTeacher> teachers;
    private TeacherRepository teacherRepository;
    public EnrollmentActivityViewModel(@NonNull Application application) {
        super(application);
        mainRepository =MainRepository.getInstance(application);
        teacherRepository = MainRepository.getInstance(application).getTeachersRepository();

    }

    boolean enrollTeacher(SyncTeacher syncTeacher, List<SyncTeacher> teachers) {
        boolean found = false;
        for(SyncTeacher syncTeacher1: teachers) {
            if (Arrays.equals(syncTeacher.getFingerPrint(), syncTeacher1.getFingerPrint())) {
                found = true;
            }
        }
        if (!found) {
            teacherRepository.insertSyncTeacher(syncTeacher);
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
        return mainRepository.getAllSyncTeacher();
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

    LiveData<List<SyncTeacher>> getAllTeachers() {
        return  teacherRepository.getAllTeachers();
    }
}
