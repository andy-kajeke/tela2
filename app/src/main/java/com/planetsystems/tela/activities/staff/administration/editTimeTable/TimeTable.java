package com.planetsystems.tela.activities.staff.administration.editTimeTable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.editStaff.StaffListAdapter;
import com.planetsystems.tela.data.timetable.SyncTimeTable;

import java.util.ArrayList;
import java.util.List;

public class TimeTable extends AppCompatActivity {

    RecyclerView taskList;
    String day_extra;
    String school_extra;
    String class_unit;
    private EditTimeTableViewModel editTimeTableViewModel;
    private List<SyncTimeTable> mSyncTimeTables;
    private TimeTableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        taskList = findViewById(R.id.task_list);

        Bundle bundle = getIntent().getExtras();
        day_extra = bundle.getString("day");
        //school_extra = bundle.getString("schoolId");
        class_unit = bundle.getString("class");

        setTitle(day_extra + "'s" + " Timetable" + " for " + class_unit);

        mSyncTimeTables = new ArrayList<>();

        adapter = new TimeTableAdapter(this, mSyncTimeTables);
        taskList.setAdapter(adapter);
        taskList.setLayoutManager(new LinearLayoutManager(this));

        editTimeTableViewModel = new ViewModelProvider(this).get(EditTimeTableViewModel.class);

        editTimeTableViewModel.timetable(day_extra, class_unit).observe(this, new Observer<List<SyncTimeTable>>() {
            @Override
            public void onChanged(List<SyncTimeTable> syncTimeTables) {
                adapter.setTaskList(syncTimeTables);
            }
        });
    }
}
