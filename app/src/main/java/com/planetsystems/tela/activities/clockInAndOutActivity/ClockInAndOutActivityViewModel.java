package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.ClockIn.ClockInRepository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.clockOut.ClockOutRepository;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.utils.DynamicData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClockInAndOutActivityViewModel extends AndroidViewModel {
    private ClockOutRepository clockOutRepository;
    private ClockInRepository clockInRepository;
    private TeacherRepository teacherRepository;
    //private String schoolID;

    public ClockInAndOutActivityViewModel(@NonNull Application application) {
        super(application);
        MainRepository mainRepository = MainRepository.getInstance(application);
        clockOutRepository = mainRepository.getClockOutRepository();
        clockInRepository = mainRepository.getClockInRepository();
        teacherRepository = mainRepository.getTeachersRepository();

    }

    public SyncTeacher clockOutTeacherWithEmployeeID(String staffID, String staffComment) {
        try {
            SyncClockOut clockOut = clockOutRepository.getSyncClockOutByEmployeeNumberAndDate(staffID, DynamicData.getDate());
            SyncTeacher teacher = teacherRepository.getTeacherWithEmployeeNumber(staffID);
            if ((clockOut == null) && (teacher != null )) {
                clockOutRepository.insertSynClockOut(
                        new SyncClockOut(
                                DynamicData.getDate(),
                                DynamicData.getDay(),
                                DynamicData.getTime(),
                                staffComment,
                                teacher.getEmployeeNumber(),
                                teacher.getEmployeeNumber(),
                                DynamicData.getLatitude(),
                                DynamicData.getLongitude(),
                                DynamicData.getSchoolID(),
                                DynamicData.getSchoolName(),
                                teacher.getFirstName(),
                                teacher.getLastName(),
                                teacher.getFingerPrint()
                        )
                );
                return  teacher;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
