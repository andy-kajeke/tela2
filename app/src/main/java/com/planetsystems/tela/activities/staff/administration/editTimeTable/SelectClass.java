package com.planetsystems.tela.activities.staff.administration.editTimeTable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.learnerAttendance.LearnerAttendanceViewModel;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;

import java.util.ArrayList;
import java.util.List;

import static com.planetsystems.tela.activities.mainActivity.MainActivity.SchoolDeviceIMEINumber;

public class SelectClass extends AppCompatActivity {

    RecyclerView schoolClasses;
    private List<SyncSchoolClasses> mSyncSchoolClasses;
    private SelectClassesListAdapter adapter;
    private LearnerAttendanceViewModel learnerAttendanceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);
        setTitle("Edit Timetable");

        schoolClasses = findViewById(R.id.class_list);

        mSyncSchoolClasses = new ArrayList<>();

        adapter = new SelectClassesListAdapter(this, mSyncSchoolClasses, "3992", SchoolDeviceIMEINumber);
        schoolClasses.setAdapter(adapter);
        schoolClasses.setLayoutManager(new LinearLayoutManager(this));

        learnerAttendanceViewModel = new ViewModelProvider(this).get(LearnerAttendanceViewModel.class);

        learnerAttendanceViewModel.schoolClasses().observe(this, new Observer<List<SyncSchoolClasses>>() {
            @Override
            public void onChanged(List<SyncSchoolClasses> syncSchoolClasses) {
                for (int i = 0; i < syncSchoolClasses.size(); i++){
                    adapter.setSchoolClassesDetails(syncSchoolClasses);
                }
            }
        });
    }
}
