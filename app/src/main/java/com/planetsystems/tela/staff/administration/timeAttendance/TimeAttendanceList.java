package com.planetsystems.tela.staff.administration.timeAttendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.staff.administration.editStaff.EditStaffListViewModel;
import com.planetsystems.tela.staff.administration.editStaff.StaffListAdapter;

import java.util.List;

public class TimeAttendanceList extends AppCompatActivity {

    RecyclerView staffs;
    ClockInListAdapter adapter;
    private TimeAttendanceListViewModel timeAttendanceListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_attendance_list);
        setTitle("Time Attendance");

        staffs = findViewById(R.id.staff_list);

//        Bundle bundle = getIntent().getExtras();
//        school_extra = bundle.getString("school");

        adapter = new ClockInListAdapter(this);
        staffs.setAdapter(adapter);
        staffs.setLayoutManager(new LinearLayoutManager(this));

        timeAttendanceListViewModel = new ViewModelProvider(this).get(TimeAttendanceListViewModel.class);

        timeAttendanceListViewModel.teachers().observe(this, new Observer<List<SyncClockIn>>() {
            @Override
            public void onChanged(List<SyncClockIn> syncClockIns) {
                adapter.setTeacherDetails(syncClockIns);
            }
        });
    }
}
