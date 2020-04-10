package com.planetsystems.tela.activities.clockInAndOutActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockwithstaffid.ClockInWithStaffIdActivity;
import com.planetsystems.tela.activities.enrollActivity.EnrollmentActivity;
import com.planetsystems.tela.activities.fingerprint.FingerPrintActivity;
import com.planetsystems.tela.activities.test.TestActivity;

import java.text.SimpleDateFormat;

public class ClockInAndOutActivity extends AppCompatActivity {
    private final int START_CLOCK_IN_WITH_STAFF_ID_ACTIVITY_FOR_RESULT = 123;

    TextView dateDisplay, schoolName;
    TextView close_clockIn, close_clockOut;
    Button btnFingerprint_In, btnStaffId_In, btnFingerprint_Out, btnStaffId_Out;
    CardView checkin, checkout, datacenter;
    Dialog checkInDialog, checkOutDialog;
    public static final int CLOCK_IN_ACTIVITY_REQUEST_CODE = 2345;
    public static final int CLOCK_OUT_ACTIVITY_REQUEST_CODE = 2345;
    String deviceIMEI_extra, schoolName_extra;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin__clock_out);

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
                Intent i = new Intent(ClockInAndOutActivity.this, ClockInWithStaffIdActivity.class);
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
                if (requestCode == RESULT_OK ) {
                    Log.d("code the code", data.getStringExtra(ClockInWithStaffIdActivity.STAFF_ID));
                }
            }
        }
    }
}
