package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.clockOut.SyncClockOut;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        return null;
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

    public String getCurrentDate() {
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        return  dateFormat.format(date);
    }

    public String getCurrentTime() {
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
        return  time.format(date);
    }

    public SyncTeacher clockOutTeacherWithEmployeeID(String id, String comment){
        // example employee number 9876 for ojok
        try {
            List<SyncClockOut> syncClockOut = repository.getSyncClockOutByEmployeeID(id, getCurrentDate());
            Log.d(getClass().getSimpleName(), "==================================================");
            if (syncClockOut.size() > 0 ) {
                return findEmployeeNumberWithEmployeeNumber(id);
            } else {
                SyncTeacher teacher = findEmployeeNumberWithEmployeeNumber(id);
                if (teacher != null ) {
                    repository.insertSynClockOut(new SyncClockOut(
                            getCurrentDate(),
                            getCurrentTime(),
                            comment,
                            teacher.getEmployeeNumber(),
                            "7827365653345342",
                            "63636636225535",
                            "3/4/2019",
                            "3/4/2019",
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
            List<SyncClockIn> list = repository
                    .getClockInRepository()
                    .getSyncClockInByEmployeeIDAndDate(employeeNumber, getCurrentDate());
            if (list.size() > 0) {
                return findEmployeeNumberWithEmployeeNumber(employeeNumber);
            } else {
                SyncTeacher teacher = findEmployeeNumberWithEmployeeNumber(employeeNumber);
                if (teacher != null ) {
                    repository.getClockInRepository().synClockInTeacher(new SyncClockIn(
                            employeeNumber,
                            teacher.getEmployeeID(),
                            DynamicData.getLatitude(),
                            DynamicData.getLongitude(),
                            DynamicData.getClockInDate(),
                            DynamicData.getClockInDay(),
                            DynamicData.getClockInTime(),
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

    public static class DynamicData {
        static String getSchoolID() {
            //TODO: put codes here for finding school id
            return "909987776676677";
        }

        static String getClockInDate() {
            long date = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
            return  dateFormat.format(date);
        }

        static String getLatitude() {
            return "90998877887";
        }

        static String getLongitude() {
            return "77887766";
        }

        static String getClockInDay() {
            return "Monday";
        }

        static String getClockInTime() {
            long date = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
            SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
            return  time.format(date);
        }
    }

}
