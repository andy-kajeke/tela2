package com.planetsystems.tela.activities.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.textView);

        TestActivityViewModel testActivityViewModel = new ViewModelProvider(this).get(TestActivityViewModel.class);
        testActivityViewModel.getAllTeachers().observe(this, new Observer<List<SyncTeacher>>() {
            @Override
            public void onChanged(List<SyncTeacher> syncTeachers) {
                String teacherName = "";
                for (int i = 0; i < syncTeachers.size(); i++) {
                    teacherName = teacherName + " \n " + syncTeachers.get(i).getFirstName();
                }
                textView.setText(teacherName);

            }
        });
    }
}
