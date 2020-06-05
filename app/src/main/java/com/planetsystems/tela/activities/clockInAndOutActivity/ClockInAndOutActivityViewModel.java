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


    SyncTeacher clockOutTeacherWithEmployeeID(String employeeNumber, String comment){
        // example employee number 9876 for ojok
        try {
            //List<SyncClockOut> syncClockOut = clockOutRepository.getSyncClockOutByEmployeeNumberAndDate(id, DynamicData.getDate());
            List<SyncClockOut> syncClockOut = clockOutRepository.getSyncClockOutByEmployeeNumberAndDate(employeeNumber, DynamicData.getDate());
            Log.d(getClass().getSimpleName(), "==================================================");
            if (syncClockOut.size() > 0 ) {
               return teacherRepository.getTeacherWithEmployeeNumber(employeeNumber);
            } else {
                SyncTeacher teacher = teacherRepository.getTeacherWithEmployeeNumber(employeeNumber);
                if (teacher != null ) {
                    clockOutRepository.insertSynClockOut(new SyncClockOut(
                            DynamicData.getDate(),
                            DynamicData.getDay(),
                            DynamicData.getTime(),
                            comment,
                            teacher.getEmployeeNumber(),
                            teacher.getEmployeeNumber(),
                            DynamicData.getLatitude(),
                            DynamicData.getLongitude(),
                            DynamicData.getSchoolID(),
                            DynamicData.getSchoolName(),
                            teacher.getFirstName(),
                            teacher.getLastName()
                    ));
                    return teacher;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<SyncClockOut>> allClockOuts(){
        return clockOutRepository.allClockOuts();
    }

    SyncTeacher clockInTeacherEmployeeNumber(String employeeNumber) {
        // example employee number 9876 for ojok
        /*
         * This method clocks in a teacher and returns the results
         * */
        try {
            List<SyncClockIn> list = clockInRepository.getSyncClockInByEmployeeIDAndDate(employeeNumber, DynamicData.getDate());
            if (list.size() > 0) {
                return  teacherRepository.getTeacherWithEmployeeNumber(employeeNumber);
            } else {
                SyncTeacher teacher =  teacherRepository.getTeacherWithEmployeeNumber(employeeNumber); // findEmployeeNumberWithEmployeeNumber(employeeNumber);
                if (teacher != null ) {
                    clockInRepository.synClockInTeacher(new SyncClockIn(
                            teacher.getEmployeeNumber(),
                            teacher.getEmployeeNumber(),
                            teacher.getFirstName(),
                            teacher.getLastName(),
                            DynamicData.getLatitude(),
                            DynamicData.getLongitude(),
                            DynamicData.getDate(),
                            DynamicData.getDay(),
                            DynamicData.getTime(),
                            DynamicData.getSchoolID()
                    ));
                    return teacher;
                }

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    SyncTeacher clockOutTeacherWithFingerPrint(String stringEncodedFingerPrint, String comment) {
        // example employee number 9876 for ojok
        try {
            SyncTeacher teacher = teacherRepository.getTeacherEmployeeNumber(stringEncodedFingerPrint);
            if ( teacher != null ) {
                return clockOutTeacherWithEmployeeID(teacher.getEmployeeNumber(), comment);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return  null;
    }

    SyncTeacher clockInTeacherWithFingerPrint(String stringEncodedFingerPrint) {
        try {
             SyncTeacher teacher = teacherRepository.getTeacherEmployeeNumber(stringEncodedFingerPrint);
             if (teacher != null ) {
                 return clockInTeacherEmployeeNumber(teacher.getEmployeeNumber());
             }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
