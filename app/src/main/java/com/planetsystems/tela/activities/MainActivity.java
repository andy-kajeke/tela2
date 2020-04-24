package com.planetsystems.tela.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.Repository;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.activityViewModel.MainActivityViewModel;
import com.planetsystems.tela.R;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    EditText firstName, secondName;
    Button submit;
    private static int SPLASH_TIME_OUT = 5000;
    private static final int REQUEST_CODE = 101;
    String IMEINumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository repository = new Repository(getApplication());
        repository.populateSyncTeacherFromApi();
        repository.populateSyncTimeTableFromApi();
        repository.startSyncClockInTeacherUploadWorker();
        Log.d("main", "looded syn teacher");

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent home = new Intent(MainActivity.this, SchoolConfirmation.class);
                //home.putExtra("device_imei", IMEINumber);
                home.putExtra("device_imei", "354633111523205");
                startActivity(home);
                finish();
            }
        }, SPLASH_TIME_OUT);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
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
        IMEINumber = telephonyManager.getDeviceId();
        Toast.makeText(MainActivity.this, "IMEI_NO is: "+ IMEINumber, Toast.LENGTH_LONG).show();
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
