package com.planetsystems.tela.activities.staff.administration.learnerAttendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.AdminSideActivity;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;

import java.util.ArrayList;
import java.util.List;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;

public class LearnerClasses extends AppCompatActivity {

    RecyclerView schoolClasses;
    ImageView back;
    private List<SyncSchoolClasses> mSyncSchoolClasses;
    private SchoolClassesListAdapter adapter;
    private LearnerAttendanceViewModel learnerAttendanceViewModel;
    String admin, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_classes);
        setTitle("Learner Attendance");

        back = findViewById(R.id.back);
        schoolClasses = findViewById(R.id.class_list);

        Bundle bundle = getIntent().getExtras();
        admin = bundle.getString("admin");
        name = bundle.getString("name");

        mSyncSchoolClasses = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), AdminSideActivity.class);
                bk.putExtra("admin", admin);
                bk.putExtra("name", name);
                startActivity(bk);
            }
        });

        adapter = new SchoolClassesListAdapter(this, mSyncSchoolClasses, admin, SchoolDeviceIMEINumber);
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
