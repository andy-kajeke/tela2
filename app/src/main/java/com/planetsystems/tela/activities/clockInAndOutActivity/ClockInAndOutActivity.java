package com.planetsystems.tela.activities.clockInAndOutActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInWithEmployeeNumber.ClockInWithEmployeeNumberActivity;
import com.planetsystems.tela.activities.enrollActivity.EnrollmentActivity;
import com.planetsystems.tela.activities.fingerprint.FingerPrintActivity;
import com.planetsystems.tela.activities.logs.LogActivity;
import com.planetsystems.tela.activities.mainActivity.MainActivity;
import com.planetsystems.tela.activities.staff.smc.SmcActivity;
import com.planetsystems.tela.activities.test.TestActivity;
import com.planetsystems.tela.constants.Role;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.activities.staff.administration.AdminSideActivity;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivity;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.utils.DynamicData;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.mSharedPreferences;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.schoolPreference;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.school_Device_Number;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.school_Name;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.school_id;

public class ClockInAndOutActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private final int START_CLOCK_IN_WITH_STAFF_ID_ACTIVITY_FOR_RESULT = 123;
    public static final int CLOCK_IN_FINGER_PRINT_ACTIVITY_REQUEST_CODE = 645;
    public static final int CLOCK_OUT_FINGER_PRINT_ACTIVITY_REQUEST_CODE = 445;

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    public static double currentLatitude;
    public static double currentLongitude;

    public static String School_ID;
    public static String SchoolDeviceIMEINumber;
    TextView dateDisplay, schoolName;
    TextView close_clockIn, close_clockOut;
    Button btnFingerprint_In, btnStaffId_In, btnFingerprint_Out, btnStaffId_Out;
    CardView checkin, checkout, datacenter;
    Dialog checkInDialog, checkOutDialog, checkOutPopup;

    ClockInAndOutActivityViewModel viewModel;
    String deviceIMEI_extra, schoolName_extra, schoolID_extra;
    String dateString, timeString;

    ////checkout//
    TextView close;
    Button btnClockOut;
    EditText staff_Id, staff_comment;
    CheckBox norm,oth;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin__clock_out);
        viewModel = new ViewModelProvider(this).get(ClockInAndOutActivityViewModel.class);

        dateDisplay = findViewById(R.id.calendarView4);
        schoolName = findViewById(R.id.schoolName);
        datacenter = findViewById(R.id.cardview2);
        checkin = findViewById(R.id.cardview3);
        checkout = findViewById(R.id.cardview4);

        mSharedPreferences = getSharedPreferences(schoolPreference, Context.MODE_PRIVATE);

        School_ID = mSharedPreferences.getString(school_id, "");
        SchoolDeviceIMEINumber = mSharedPreferences.getString(school_Device_Number, "");

        DynamicData.getSchoolID(SchoolDeviceIMEINumber);

        schoolName.setText(mSharedPreferences.getString(school_Name, ""));

        //SCHOOL_ID = deviceIMEI_extra;
<<<<<<< HEAD
        //Toast.makeText(this, SchoolDeviceIMEINumber+ " sch_id "+ School_ID, Toast.LENGTH_LONG).show();
=======
        Toast.makeText(this, lat+"=="+lng +": "+ DynamicData.getSchoolID(getApplicationContext()), Toast.LENGTH_LONG).show();
>>>>>>> 9ac0c9ad84d4e407977b7fc7c2bda54c0bfb3572

        checkInDialog = new Dialog(this);
        checkOutDialog = new Dialog(this);
        checkOutPopup = new Dialog(this);

        //Display the date to ui
        long date = System.currentTimeMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd /MM/ yyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
        dateString = dateFormat.format(date);
        timeString = time.format(date);
        dateDisplay.setText(dateString);

        //Clock-in action
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClockIn();
            }
        });

        //Clock-out action
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClockOut();
            }
        });

        /////////////////////////////////////////////GPS/////////////////////////////////////////////////////////
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
//            case R.id.enroll:
//                Intent home = new Intent(ClockInAndOutActivity.this, EnrollmentActivity.class);
//                startActivity(home);
//                return true;
//            case R.id.settings:
//                startActivity(new Intent(this, TestActivity.class));
//                return true;

            case R.id.logout:
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.remove(school_Device_Number);
                editor.commit();
                startActivity(new Intent(this, MainActivity.class));
//
//            case R.id.checkLogs:
//                startActivity(new Intent(this, LogActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ClockIn(){
        checkInDialog.setContentView(R.layout.check_in_popup);
        close_clockIn = checkInDialog.findViewById(R.id.txclose);
        btnFingerprint_In = checkInDialog.findViewById(R.id.finger_in);
        btnStaffId_In = checkInDialog.findViewById(R.id.staffId_in);

        //Clock in with fingerprint 406413
        btnFingerprint_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClockInAndOutActivity.this, FingerPrintActivity.class);
                intent.setAction(FingerPrintActivity.ACTION_CLOCK_IN);
                startActivityForResult(intent, CLOCK_IN_FINGER_PRINT_ACTIVITY_REQUEST_CODE);
                checkInDialog.dismiss();

            }
        });

        //Clock in with staff ID
        btnStaffId_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClockInAndOutActivity.this, ClockInWithEmployeeNumberActivity.class);
                startActivityForResult(i, START_CLOCK_IN_WITH_STAFF_ID_ACTIVITY_FOR_RESULT);
                checkInDialog.dismiss();
            }
        });

        //Close clock in dialog
        close_clockIn.setText("X");
        close_clockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInDialog.dismiss();
            }
        });

        checkInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        checkInDialog.show();
    }

    public void ClockOut(){
        checkOutDialog.setContentView(R.layout.check_out_popup);
        close_clockOut = checkOutDialog.findViewById(R.id.txclose);
        btnFingerprint_Out = checkOutDialog.findViewById(R.id.finger_out);
        btnStaffId_Out = checkOutDialog.findViewById(R.id.staffId_out);

        //Clock out with fingerprint
        btnFingerprint_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clock Out", "clocking out");
                Intent intent = new Intent(ClockInAndOutActivity.this, FingerPrintActivity.class);
                intent.setAction(FingerPrintActivity.ACTION_CLOCK_OUT);
                startActivityForResult(intent, CLOCK_OUT_FINGER_PRINT_ACTIVITY_REQUEST_CODE);
                checkOutDialog.dismiss();
            }
        });

        //Clock out with staff ID
        btnStaffId_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCheckOutPopup();
            }
        });

        //Close clock out dialog
        close_clockOut.setText("X");
        close_clockOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkOutDialog.dismiss();
            }
        });

        checkOutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        checkOutDialog.show();
    }

    public void ShowCheckOutPopup() {

        checkOutPopup.setContentView(R.layout.checkoutpopup);

        close = checkOutPopup.findViewById(R.id.txclose);
        btnClockOut = checkOutPopup.findViewById(R.id.out);
        staff_Id = checkOutPopup.findViewById(R.id.staff_id);
        staff_comment = checkOutPopup.findViewById(R.id.comment);
        norm = checkOutPopup.findViewById(R.id.normal) ;
        oth = checkOutPopup.findViewById(R.id.other);

        norm.setChecked(true);
        staff_comment.setText("Normal end of day");
        staff_comment.setFocusableInTouchMode(false);

        oth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    staff_comment.setText("Specify reason");
                    staff_comment.setFocusableInTouchMode(true);
                    norm.setChecked(false);
                }

            }
        });
        norm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    staff_comment.setText("Normal end of day");
                    staff_comment.setFocusableInTouchMode(false);
                    oth.setChecked(false);
                }

            }
        });
        btnClockOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(staff_Id.getText().toString().equalsIgnoreCase("")){
                    staff_Id.setError("Id Missing!");
                }else{
                    new AlertDialog.Builder(ClockInAndOutActivity.this)
                            .setTitle("Confirmation")
                            .setMessage("Do you really want to clock out?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    clockOutWithStaffID();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
            }
        });

        close.setText("X");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkOutPopup.dismiss();
            }
        });

        checkOutPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        checkOutPopup.show();

    }

    //clock out functionality
    private void clockOutWithStaffID() {
        String staffID = staff_Id.getText().toString();
        String staffComment = staff_comment.getText().toString();
        ClockInAndOutActivityViewModel.TeacherWrapper teacherWrapper = viewModel.clockOutTeacherWithEmployeeID(staffID, staffComment);
        //loadTeacherHomePage(teacher);
        if(teacherWrapper.getTeacher() == null ){
            new AlertDialog.Builder(ClockInAndOutActivity.this)
                    .setTitle("Confirmation")
                    .setMessage(teacherWrapper.getMsg())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            checkOutPopup.dismiss();
                        }})
                    .setNegativeButton("", null).show();
        }else {
            SyncTeacher teacher = teacherWrapper.getTeacher();
            new AlertDialog.Builder(ClockInAndOutActivity.this)
                    .setTitle("Successfully clocked out")
                    .setMessage("=============================\n"+ "Name : " + teacher.getFirstName() + " " + teacher.getLastName()
                            + "\n\n" + "Date Out : " + dateString + "\n\n" + "Time Out : " + timeString)
                    .setIcon(R.drawable.success)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            checkOutPopup.dismiss();
                        }}).show();
                    //.setNegativeButton(android.R.string.no, null).show();
        }
    }

    private void loadTeacherHomePage(SyncTeacher syncTeacher) {
        if (syncTeacher != null ) {
            /*
            * The teacher have successfully signed in, new we take him to the
            * teachers home page basing on the role*/
            if (syncTeacher.getRole().equals(Role.TEACHER_ROLE)) {
                Intent teacherHome = new Intent(this, TeacherHomeActivity.class);
                teacherHome.putExtra("employee_No", syncTeacher.getEmployeeNumber());
                teacherHome.putExtra("employee_Name", syncTeacher.getFirstName() + " " + syncTeacher.getLastName());
                startActivity(teacherHome);

            } else if (syncTeacher.getRole().equals(Role.HEAD_TEACHER_ROLE)) {
                Intent headTeacherHome = new Intent(this, AdminSideActivity.class);
                headTeacherHome.putExtra("employee_No", syncTeacher.getEmployeeNumber());
                headTeacherHome.putExtra("employee_Name",syncTeacher.getFirstName() + " " + syncTeacher.getLastName());
                // TODO: SCHOOL NUMBER MUST BE CHANGED below
                //headTeacherHome.putExtra(AdminSideActivity.SCHOOL_NUMBER, "354633111523205");
                startActivity(headTeacherHome);

            }else if (syncTeacher.getRole().equals(Role.SMC)) {
                Intent smcHome = new Intent(this, SmcActivity.class);
                smcHome.putExtra("employee_No", syncTeacher.getEmployeeNumber());
                smcHome.putExtra("employee_Name",syncTeacher.getFirstName() + " " + syncTeacher.getLastName());
                // TODO: SCHOOL NUMBER MUST BE CHANGED below

                startActivity(smcHome);
            }
        } else {
            Toast.makeText(this, "Invalid Employee Number or Finger Print", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CLOCK_IN_FINGER_PRINT_ACTIVITY_REQUEST_CODE:
                    try {
                        assert data != null;
                        SyncTeacher syncTeacher = TeacherRepository.getInstance(TelaRoomDatabase.getInstance(getApplicationContext()))
                                .getTeacherWithEmployeeNumber(data.getStringExtra(FingerPrintActivity.EMPLOYEEE_NUMBER));
                        if (syncTeacher != null) {
                            loadTeacherHomePage(syncTeacher);
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }

                case CLOCK_OUT_FINGER_PRINT_ACTIVITY_REQUEST_CODE:
                    try {
                        assert data != null;
                        SyncTeacher syncTeacher = TeacherRepository.getInstance(TelaRoomDatabase.getInstance(getApplicationContext()))
                                .getTeacherWithEmployeeNumber(data.getStringExtra(FingerPrintActivity.EMPLOYEEE_NUMBER));
                        if (syncTeacher != null) {
                            Snackbar.make(close_clockOut, "Clocked Out Successfully: " + syncTeacher.getEmployeeNumber() , Snackbar.LENGTH_LONG).show();
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                case START_CLOCK_IN_WITH_STAFF_ID_ACTIVITY_FOR_RESULT:
                    assert data != null;
                    ClockInAndOutActivityViewModel.TeacherWrapper teacherWrapper = viewModel.clockInTeacherWithEmployeeNumber(data.getStringExtra(ClockInWithEmployeeNumberActivity.EMPLOYEE_NUMBER));
                    if (teacherWrapper.getTeacher() != null ) {
                        Toast.makeText(this, teacherWrapper.getMsg(), Toast.LENGTH_SHORT).show();
                        loadTeacherHomePage(teacherWrapper.getTeacher());
                    }
                default:
//                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "No Information Provided", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Now lets connect to the API
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }


    }

    /**
     * If connected get lat and long
     *
     */
    @Override
    public void onConnected(Bundle bundle) {
        Location location = FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();

            //Toast.makeText(this, "Lat: "+DynamicData.getLatitude(currentLatitude) + " Long: "+ DynamicData.getLongitude(currentLongitude), Toast.LENGTH_LONG).show();
            DynamicData.getLatitude(currentLatitude);
            DynamicData.getLongitude(currentLongitude);
        }
    }


    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.e("Error", "++++Location services connection failed with code+++++ " + connectionResult.getErrorCode());
        }
    }

    /**
     * If locationChanges change lat and long
     *
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        //Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }
}
