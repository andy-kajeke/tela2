package com.planetsystems.tela.activities.mainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.activities.dialogs.PhoneNumberDialogActivity;
import com.planetsystems.tela.activities.school.SchoolConfirmation;
import com.planetsystems.tela.R;
import com.planetsystems.tela.utils.DynamicData;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    EditText firstName, secondName;
    Button submit;
    private static int SPLASH_TIME_OUT = 5000;
    private static final int REQUEST_CODE = 101;
    public static String SchoolDeviceIMEINumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        WorkManagerTrigger.startFetchWorkers(getApplicationContext());
//        WorkManagerTrigger.startUploadWorkers(getApplicationContext());

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

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
    }

}
