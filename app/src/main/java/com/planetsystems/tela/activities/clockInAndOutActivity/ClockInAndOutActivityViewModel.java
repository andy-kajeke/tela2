package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.planetsystems.tela.activities.MainActivity.SchoolDeviceIMEINumber;

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


    SyncTeacher clockOutTeacherWithEmployeeID(String id, String comment){
        // example employee number 9876 for ojok
        try {
            List<SyncClockOut> syncClockOut = clockOutRepository.getSyncClockOutByEmployeeNumberAndDate(id, DynamicData.getDate());
            Log.d(getClass().getSimpleName(), "==================================================");
            if (syncClockOut.size() > 0 ) {
//                return findEmployeeNumberWithEmployeeNumber(id);
            } else {
                SyncTeacher teacher =  null; //findEmployeeNumberWithEmployeeNumber(id);
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
                            "3/4/2019",
                            DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                            "3/4/2019",
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
                            DynamicData.getSchoolID(SchoolDeviceIMEINumber)
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

    SyncTeacher clockOutTeacherWithFingerPrint(String stringEncodedFingerPrint) {
        return null;
    }

    SyncTeacher clockInTeacherWithFingerPrint(String stringEncodedFingerPrint) {
        return null;
    }

    /*
    * This class Dynamic data that are generated during run time
    * I create this class to simplify the data management*/

    public static class DynamicData {

        public static String getSchoolID(String schoolID) {
            //TODO: put codes here for finding school id
            return schoolID;
        }

        static String getLatitude() {
            return SchoolDeviceIMEINumber;
        }

        static String getLongitude() {
            return "77887766";
        }

        static String getDay() {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            Date d = new Date();
            return sdf.format(d);
        }

        static String getTime() {
            long date = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
            SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
            return  time.format(date);
        }

        static String getDate() {
            long date = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
            return  dateFormat.format(date);
        }

    }

}
