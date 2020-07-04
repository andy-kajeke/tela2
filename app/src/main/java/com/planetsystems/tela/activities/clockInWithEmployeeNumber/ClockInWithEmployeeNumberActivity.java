package com.planetsystems.tela.activities.clockInWithEmployeeNumber;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class ClockInWithEmployeeNumberActivity extends Activity {
    public static final String EMPLOYEE_NUMBER = "com.planetsystems.tela.activities.clockwithstaffid.EMPLOYEE_NUMBER";

    ProgressDialog dialog;
    CardView btnFollow;
    EditText staffid;
    RadioButton staff,admin,Smc;

    LocationManager locationManager;
    double longitude;
    double latitude;

    int Employee_ID;
    String Name;
    String Role;

    ClockInWithEmployeeNumberActivityViewModel viewModel;
    List<SyncTeacher> allTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin_with__staff_id);

        btnFollow = findViewById(R.id.google);
        staffid = findViewById(R.id.staffID);



        //Button action
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String employeeNumber = staffid.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(EMPLOYEE_NUMBER, employeeNumber);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        // getLocation();
    }

    ///Check Internet connection
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}

