package com.planetsystems.tela.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.activityViewModel.MainActivityViewModel;
import com.planetsystems.tela.R;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    EditText firstName, secondName;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.lastName);
        submit = findViewById(R.id.submit);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.teachers().observe(this, new Observer<List<SyncTeacher>>() {
            @Override
            public void onChanged(List<SyncTeacher> syncTeachers) {
                Toast.makeText(MainActivity.this, "size is: " + String.valueOf(syncTeachers.size()), Toast.LENGTH_LONG).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SyncTeacher syncTeacher = new SyncTeacher("dhlijjsdcdaiooo", null, "23/1/1990",
                        null, null, "andrew", "kajeke", "M",
                "ak",1, "wweer", "12344","cdsgs4y5e43");
                mainActivityViewModel.insertTeacher(syncTeacher);
            }
        });
    }
}
