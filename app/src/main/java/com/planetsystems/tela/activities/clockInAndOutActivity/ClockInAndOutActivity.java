package com.planetsystems.tela.activities.clockInAndOutActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInWithEmployeeNumber.ClockInWithEmployeeNumberActivity;
import com.planetsystems.tela.activities.enrollActivity.EnrollmentActivity;
import com.planetsystems.tela.activities.fingerprint.FingerPrintActivity;
import com.planetsystems.tela.activities.test.TestActivity;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.staff.regularStaff.TeacherHome;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class ClockInAndOutActivity extends AppCompatActivity {
    private final int START_CLOCK_IN_WITH_STAFF_ID_ACTIVITY_FOR_RESULT = 123;
    private List<SyncTeacher> teacherList;

    TextClock clock_in_time;
    TextView dateDisplay, schoolName;
    TextView close_clockIn, close_clockOut;
    Button btnFingerprint_In, btnStaffId_In, btnFingerprint_Out, btnStaffId_Out;
    CardView checkin, checkout, datacenter;
    Dialog checkInDialog, checkOutDialog;
    public static final int CLOCK_IN_ACTIVITY_REQUEST_CODE = 2345;
    public static final int CLOCK_OUT_ACTIVITY_REQUEST_CODE = 2345;
    public static  String clockInTime = "";
    ClockInAndOutActivityViewModel viewModel;
    String deviceIMEI_extra, schoolName_extra;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin__clock_out);
        viewModel = new ViewModelProvider(this).get(ClockInAndOutActivityViewModel.class);
        viewModel.getAllSyncTeacher().observe(this, new Observer<List<SyncTeacher>>() {
            @Override
            public void onChanged(List<SyncTeacher> syncTeachers) {
                teacherList = syncTeachers;
            }
        });

        clock_in_time = findViewById(R.id.textClock3);
        dateDisplay = findViewById(R.id.calendarView4);
        schoolName = findViewById(R.id.schoolName);
        datacenter = findViewById(R.id.cardview2);
        checkin = findViewById(R.id.cardview3);
        checkout = findViewById(R.id.cardview4);

        Bundle bundle = getIntent().getExtras();
        deviceIMEI_extra = bundle.getString("device_imei");
        schoolName_extra = bundle.getString("schoolName");

        schoolName.setText(schoolName_extra);


        checkInDialog = new Dialog(this);
        checkOutDialog = new Dialog(this);

        //Display the date to ui
        long date = System.currentTimeMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd /MM/ yyy");
        String dateString = dateFormat.format(date);
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
            case R.id.enroll:
                Intent home = new Intent(ClockInAndOutActivity.this, EnrollmentActivity.class);
                startActivity(home);
                return true;
            case R.id.settings:
                //showHelp();
                return true;

            case R.id.testing:
                startActivity(new Intent(this, TestActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ClockIn(){
        checkInDialog.setContentView(R.layout.check_in_popup);
        close_clockIn = checkInDialog.findViewById(R.id.txclose);
        btnFingerprint_In = checkInDialog.findViewById(R.id.finger_in);
        btnStaffId_In = checkInDialog.findViewById(R.id.staffId_in);

        //Clock in with fingerprint
        btnFingerprint_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClockInAndOutActivity.this, FingerPrintActivity.class);
                intent.setAction(FingerPrintActivity.ACTION_CLOCK_IN);
                startActivityForResult(intent, CLOCK_IN_ACTIVITY_REQUEST_CODE);
                checkOutDialog.dismiss();

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
                intent.setAction(FingerPrintActivity.ACTION_CLOCK_IN);
                startActivityForResult(intent, CLOCK_OUT_ACTIVITY_REQUEST_CODE);


            }
        });

        //Clock out with staff ID
        btnStaffId_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null ) {
            if (requestCode == START_CLOCK_IN_WITH_STAFF_ID_ACTIVITY_FOR_RESULT ) {
                // we have the code
                if (resultCode == RESULT_OK ) {
                    String employeeNumber = data.getStringExtra(ClockInWithEmployeeNumberActivity.EMPLOYEE_NUMBER);
                    Log.d("code the code", "========================================================");
                    Log.d("code the code", Objects.requireNonNull(data.getStringExtra(ClockInWithEmployeeNumberActivity.EMPLOYEE_NUMBER)));
                    boolean isClockedIn = viewModel.clockInTeacherEmployeeNumber(teacherList, Objects.requireNonNull(data.getStringExtra(ClockInWithEmployeeNumberActivity.EMPLOYEE_NUMBER)));
                    if (isClockedIn) {
                        if (Objects.equals(data.getStringExtra(ClockInWithEmployeeNumberActivity.EMPLOYEE_NUMBER), "9876")){

                            Intent teacherHome = new Intent(this, TeacherHome.class);
                            teacherHome.putExtra("id", employeeNumber);
                            teacherHome.putExtra("name","Andrew Kajeke");
                            startActivity(teacherHome);


                        }

                        // TODO: the remaining codes with be added here
                    }
                }
            }
        }
    }
}
