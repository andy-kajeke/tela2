package com.planetsystems.tela.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.planetsystems.tela.activityViewModel.MainActivityViewModel;
import com.planetsystems.tela.R;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    EditText firstName, secondName;
    Button submit;
    private static int SPLASH_TIME_OUT = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent home = new Intent(MainActivity.this, ClockIn_ClockOut.class);
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
    }
}
