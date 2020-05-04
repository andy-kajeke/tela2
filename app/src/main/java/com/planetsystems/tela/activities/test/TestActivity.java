package com.planetsystems.tela.activities.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.timetable.SyncTimeTable;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.textView);

        TestActivityViewModel testActivityViewModel = new ViewModelProvider(this).get(TestActivityViewModel.class);
        testActivityViewModel.schoolClasses().observe(this, new Observer<List<SyncSchoolClasses>>() {
            @Override
            public void onChanged(List<SyncSchoolClasses> schoolClasses) {
                String teacherName = "";
                for (int i = 0; i < schoolClasses.size(); i++) {
                    teacherName = teacherName + " \n " + schoolClasses.get(i).getClassName();
                }
                textView.setText(teacherName);

            }
        });
    }
}
