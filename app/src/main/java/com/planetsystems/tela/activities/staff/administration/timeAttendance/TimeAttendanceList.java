package com.planetsystems.tela.activities.staff.administration.timeAttendance;

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
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.activities.staff.administration.AdminSideActivity;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.workers.WorkManagerTrigger;

import java.text.SimpleDateFormat;
import java.util.List;

public class TimeAttendanceList extends AppCompatActivity {

    RecyclerView staffs;
    ClockInListAdapter adapter;
    String supervisor, name;
    ImageView back;

    private TimeAttendanceListViewModel timeAttendanceListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_attendance_list);
        setTitle("Time Attendance");

        back = findViewById(R.id.back);
        staffs = findViewById(R.id.staff_list);

        Bundle bundle = getIntent().getExtras();
        supervisor = bundle.getString("admin");
        name = bundle.getString("name");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), AdminSideActivity.class);
                bk.putExtra("admin", supervisor);
                bk.putExtra("name", name);
                startActivity(bk);
            }
        });

        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        String dateOfDay = dateFormat.format(date);

        /////////////////////////////Sync data/////////////////////////////////////////////////////////
        WorkManagerTrigger.startFetchWorkers(getApplicationContext());
        WorkManagerTrigger.startUploadWorkers(getApplicationContext());

        adapter = new ClockInListAdapter(this, supervisor);
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
