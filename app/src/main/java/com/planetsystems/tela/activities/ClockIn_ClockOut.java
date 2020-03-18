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
import android.widget.TextView;

import com.planetsystems.tela.R;

import java.text.SimpleDateFormat;

public class ClockIn_ClockOut extends AppCompatActivity {

    TextView dateDisplay;
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
        checkInDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        checkInDialog.show();
    }

    public void ClockOut(){
        checkOutDialog.setContentView(R.layout.check_out_popup);
        checkOutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        checkOutDialog.show();
    }
}
