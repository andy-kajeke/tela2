package com.planetsystems.tela.activities.staff.administration.taskAttendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.timeAttendance.ClockInListAdapter;
import com.planetsystems.tela.activities.staff.administration.timeAttendance.TimeAttendanceListViewModel;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;

import java.text.SimpleDateFormat;
import java.util.List;

public class TaskAttendance extends AppCompatActivity {

    RecyclerView staffs;
    TaskAttendanceAdapter adapter;
    private TimeAttendanceListViewModel timeAttendanceListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_attendance);
        setTitle("Task Attendance");

        staffs = findViewById(R.id.staff_list);

        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        String dateOfDay = dateFormat.format(date);

        adapter = new TaskAttendanceAdapter(this);
        staffs.setAdapter(adapter);
        staffs.setLayoutManager(new LinearLayoutManager(this));

        timeAttendanceListViewModel = new ViewModelProvider(this).get(TimeAttendanceListViewModel.class);

        timeAttendanceListViewModel.teachers(dateOfDay).observe(this, new Observer<List<SyncClockIn>>() {
            @Override
            public void onChanged(List<SyncClockIn> syncClockIns) {
                for (int i = 0; i < syncClockIns.size(); i++){
                    adapter.setTeacherDetails(syncClockIns);
                }
            }
        });
    }
}
