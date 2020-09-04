package com.planetsystems.tela.activities.mainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.activities.school.SchoolConfirmation;
import com.planetsystems.tela.R;
import com.planetsystems.tela.utils.DynamicData;
import com.planetsystems.tela.workers.WorkManagerTrigger;

import static com.planetsystems.tela.activities.school.SchoolConfirmation.mSharedPreferences;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.schoolPreference;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.school_Device_Number;
import static com.planetsystems.tela.activities.school.SchoolConfirmation.school_Name;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    private static final int REQUEST_CODE = 101;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorkManagerTrigger.startFetchWorkers(getApplicationContext());
        WorkManagerTrigger.startUploadWorkers(getApplicationContext());

        mSharedPreferences = getSharedPreferences(schoolPreference, Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(mSharedPreferences.getString(school_Device_Number, "") == ""){
                    Intent home = new Intent(MainActivity.this, SchoolConfirmation.class);
                    startActivity(home);
                    finish();
                }else {
                    Intent home = new Intent(MainActivity.this, ClockInAndOutActivity.class);
                    startActivity(home);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);

    }

}
