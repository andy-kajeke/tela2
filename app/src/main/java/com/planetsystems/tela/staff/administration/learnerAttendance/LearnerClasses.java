package com.planetsystems.tela.staff.administration.learnerAttendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;

import java.util.ArrayList;
import java.util.List;

import static com.planetsystems.tela.activities.mainactivity.MainActivity.SchoolDeviceIMEINumber;

public class LearnerClasses extends AppCompatActivity {

    RecyclerView schoolClasses;
    private List<SyncSchoolClasses> mSyncSchoolClasses;
    private SchoolClassesListAdapter adapter;
    private LearnerAttendanceViewModel learnerAttendanceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_classes);
        setTitle("Learner Attendance");

        schoolClasses = findViewById(R.id.class_list);

        mSyncSchoolClasses = new ArrayList<>();

        adapter = new SchoolClassesListAdapter(this, mSyncSchoolClasses, "3992", SchoolDeviceIMEINumber);
        schoolClasses.setAdapter(adapter);
        schoolClasses.setLayoutManager(new LinearLayoutManager(this));

        learnerAttendanceViewModel = new ViewModelProvider(this).get(LearnerAttendanceViewModel.class);

        learnerAttendanceViewModel.schoolClasses().observe(this, new Observer<List<SyncSchoolClasses>>() {
            @Override
            public void onChanged(List<SyncSchoolClasses> syncSchoolClasses) {
                adapter.setSchoolClassesDetails(syncSchoolClasses);
            }
        });

    }
}
