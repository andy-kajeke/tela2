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

<<<<<<< HEAD
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
=======
import com.planetsystems.tela.activities.dialogs.PhoneNumberDialogActivity;
>>>>>>> 9ac0c9ad84d4e407977b7fc7c2bda54c0bfb3572
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
<<<<<<< HEAD
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

=======

                Intent home = new Intent(MainActivity.this, SchoolConfirmation.class);
                //home.putExtra("device_imei", SchoolDeviceIMEINumber);
                home.putExtra("device_imei", "354633111523205");
                startActivity(home);
                finish();
            }
        }, SPLASH_TIME_OUT);

//        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
//
//        mainActivityViewModel.teachers().observe(this, new Observer<List<SyncTeacher>>() {
//            @Override
//            public void onChanged(List<SyncTeacher> syncTeachers) {
//                Toast.makeText(MainActivity.this, "size is: " + String.valueOf(syncTeachers.size()), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SyncTeacher syncTeacher = new SyncTeacher("dhlijjsdcdaiooo", null, "23/1/1990",
//                        null, null, "andrew", "kajeke", "M",
//                "ak",1, "wweer", "12344","cdsgs4y5e43");
//                mainActivityViewModel.insertTeacher(syncTeacher);
//            }
//        });

        //IMEI Number
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }
//       SchoolDeviceIMEINumber = telephonyManager.getDeviceId();
        //SchoolDeviceIMEINumber = "354633111523205";
//        Toast.makeText(MainActivity.this, "IMEI_NO is: "+ SchoolDeviceIMEINumber, Toast.LENGTH_LONG).show();
        SchoolDeviceIMEINumber = "354633111523205";

        ///////////////////SIM Card reader//////////////////////////////////////////////////////
//        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        @SuppressLint("MissingPermission")
//        String getSimSerialNumber = telephonyManager.getSimSerialNumber();
//        String getSimNumber = telephonyManager.getLine1Number();
//
//        Toast.makeText(MainActivity.this, "IMEI_NO is: "+ getSimSerialNumber, Toast.LENGTH_LONG).show();
//        if (getSimNumber != null)
//            Toast.makeText(this, "Phone number: " + getSimNumber,
//                    Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
>>>>>>> 9ac0c9ad84d4e407977b7fc7c2bda54c0bfb3572
    }

}
