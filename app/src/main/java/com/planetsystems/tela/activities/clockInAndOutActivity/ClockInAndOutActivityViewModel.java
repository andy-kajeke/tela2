package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.GenerateRandomString;
import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.clockOut.SyncClockOut;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClockInAndOutActivityViewModel extends AndroidViewModel {
    LocationManager locationManager;
    double lng;
    double lat;

    String dateString;
    String dayOfTheWeek;
    String checkIn_time;

    private static final int REQUEST_CODE = 101;
    String IMEINumber;
    private Context context;

    private ClockInAndOutActivity clockInAndOutActivity;
    private LiveData<List<SyncTeacher>> syncTeachersLiveData;
    private List<SyncTeacher> teachers;
    private LiveData<List<SyncClockOut>> synClockOutLiveData;
    private List<SyncClockOut> synClockOutTeachers;
    private List<SyncClockIn> syncClockIns;
    private LiveData<List<SyncClockIn>> syncClockInsLiveData;
    private Repository repository;

    public ClockInAndOutActivityViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
        syncTeachersLiveData = repository.getAllTeachers();
        synClockOutLiveData = repository.getAlreadyClockOutTeachers();
        syncClockInsLiveData = repository.getAllClockedInStaff();

        //Day of the week
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);

        //Date of the day
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
        dateString = dateFormat.format(date);
        checkIn_time = time.format(date);

    }

    LiveData<List<SyncTeacher>> getAllSyncTeacher() {
        /*
        * Supplies the activity with the live data that is oberseved by activity and converted in list
        * then a methed is called to set that list back in this view model
        * */
        return syncTeachersLiveData;
    }

    SyncTeacher clockInTeacherEmployeeNumber(String employeeNumber) {
        // example employee number 9876 for ojok
        /*
        * This method clocks in a teacher and returns the results
        * */
       SyncTeacher syncTeacher = findEmployeeNumberWithEmployeeNumber(employeeNumber);
       if (syncTeacher == null) return null;
       try {
           if (hasAlreadyClockedIn(syncTeacher)) {
               return syncTeacher;
           }
       } catch (ParseException e) {
           e.printStackTrace();
       }
        SyncClockIn syncClockIn = copySynTeacherToSyncClockIn(syncTeacher);
       repository.synClockInTeacher(syncClockIn);
        return syncTeacher ;
    }

    private SyncTeacher findEmployeeNumberWithEmployeeNumber(String staffID) {
        /*
        * Searches the database and returns matching sync teacher
        * */
        for (SyncTeacher teacher: teachers ) {
            if (teacher.getEmployeeNumber().equals(staffID)) {
                return teacher;
            }
        }
        return null;
    }


    SyncTeacher clockOutTeacherWithFingerPrint(String stringEncodedFingerPrint, String base64EncodedBitmapImage) {
//        SyncTeacher syncTeacher = findTeacherWithFingerPrint(stringEncodedFingerPrint.getBytes());
//        if (syncTeacher == null) return null;
//        SyncClockIn syncClockIn = copySynTeacherToSyncClockIn(syncTeacher);
//        repository.synClockOutTeacher(syncClockIn);
        return null;
    }

    SyncTeacher clockInTeacherWithFingerPrint(String stringEncodedFingerPrint, String base64EncodedBitmapImage) {
        /*
        * A method that clocks in a teacher using finger print and returns that clocked in user,
        * see clockOutTeacherWithFingerPrint(String stringEncodedFingerPrint, String base64EncodedBitmapImage)
        * and other similar methods
        * */
        SyncTeacher syncTeacher = findTeacherWithFingerPrint(stringEncodedFingerPrint.getBytes());
        if (syncTeacher == null ) return null;
        SyncClockIn syncClockIn = copySynTeacherToSyncClockIn(syncTeacher);
        repository.synClockInTeacher(syncClockIn);
        return syncTeacher;
    }

    void setTeachers(List<SyncTeacher> teachers) {
        /*
        * The method called from the activity to set the list of teachers
        * to be used to find already clocked in and clocked out teachers
        * */
        this.teachers = teachers;
    }

    private SyncTeacher findTeacherWithFingerPrint(byte[] fingerPrint) {
        /*
        * Given a fingerprint bytes array, this method searches through the database
        * and finds that teacher and returns it
        * */
        if (teachers == null) return null;
        for (SyncTeacher teacher: teachers) {
            if (teacher.getFingerPrint().getBytes() == fingerPrint) {
                return teacher;
            }
        }
        return null;
    }

    private SyncClockIn copySynTeacherToSyncClockIn(SyncTeacher teacher) {
        /*
        * Given sync teacher will holds all the vital in about teacher,
        * this method was created for code reusability, it maps out sysnc teacher into
        * sync clock in => check copySynTeacherToSyncClockOut(SyncTeacher teacher)
        * also
        * */
        // TODO: please fix the time below, this time will be time the app was lunched change it
        return new SyncClockIn(
                GenerateRandomString.randomString(17),
                null,
                null,
                null,
                dateString,
                checkIn_time,
                dayOfTheWeek,
                teacher.getEmployeeNumber(),
                teacher.getEmployeeNumber(),
                null,
                null,
                null,
                null,
                teacher.getFirstName(),
                teacher.getLastName(),
                null,
                null

        );
    }

    LiveData<List<SyncClockOut>> getSynClockOutLiveData() {
        /*
        * A method that sends live data to the activity to be observed and converted in to a list
        * that is used to check whether teach already clocked in
        * */
        return synClockOutLiveData;
    }

    void setSynClockOutTeachers(List<SyncClockOut> synClockOutTeachers) {
        /*
        * This method is called from the activity to set value of Sync Clock Out teacher
        * The List is then used to check whether a teacher has already clocked in
        * */
        this.synClockOutTeachers = synClockOutTeachers;
    }

    private boolean hasAlreadyClockedOutWithFingerPrintOrEmployeeNumber() {
        // TODO: This will be implemented
        /*
        * This method checks whether employee has already clock out to prevent double chock out and
        * thus coursing errors.
        * */
        return true;
    }

    private boolean hasAlreadyClockedIn(SyncTeacher teacher) throws ParseException {
        /*
        * This method check whether employee has already clocked in with employee number
        * It returns synteacher if so and otherwise null
        * */
        // TODO: we should check within today only please address help her
        //Day of the week
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);

        //Date of the day
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
        String dateString = dateFormat.format(date);
        String checkIn_time = time.format(date);
        Date now = dateFormat.parse(dateString);

        for (SyncClockIn syncClockIn: syncClockIns) {
            Date then = dateFormat.parse(syncClockIn.getClockInDate());
            assert then != null;
            if (syncClockIn.getEmployeeId().equals(teacher.getEmployeeNumber()) && (then.compareTo(now) == 0)) {
                Log.d(getClass().getSimpleName(), "Teacher already clocked in");
                //Toast.makeText(context, "Already clocked in", Toast.LENGTH_LONG).show();
                Log.d(getClass().getSimpleName(), syncClockIn.getClockInDate() + " : " + syncClockIn.getClockInTime());
                return true;
            }
        }
        return false;
    }

    LiveData<List<SyncClockIn>> getClockInLiveData() {
        return syncClockInsLiveData;
    }

    void setSyncClockInList(List<SyncClockIn> syncClockInList) {
        syncClockIns = syncClockInList;
    }
}
