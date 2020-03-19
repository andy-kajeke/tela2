package com.planetsystems.tela.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.planetsystems.tela.R;

import java.text.SimpleDateFormat;

public class ClockIn_ClockOut extends AppCompatActivity {

    TextView dateDisplay;
    TextView close_clockIn, close_clockOut;
    Button btnFingerprint_In, btnStaffId_In, btnFingerprint_Out, btnStaffId_Out;
    CardView checkin, checkout, datacenter;
    Dialog checkInDialog, checkOutDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin__clock_out);

        dateDisplay = findViewById(R.id.calendarView4);
        datacenter = findViewById(R.id.cardview2);
        checkin = findViewById(R.id.cardview3);
        checkout = findViewById(R.id.cardview4);

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
                Intent home = new Intent(ClockIn_ClockOut.this, EnrollmentActivity.class);
                startActivity(home);
                return true;
            case R.id.settings:
                //showHelp();
                return true;
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

            }
        });

        //Clock in with staff ID
        btnStaffId_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClockIn_ClockOut.this, Clockin_with_StaffId.class);
//                i.putExtra("time",clock_in_time.getText());
//                i.putExtra("date",dayString);
                startActivity(i);
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
}
