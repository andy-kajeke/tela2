package com.planetsystems.tela.activities.staff.administration.editTimeTable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.editStaff.StaffListAdapter;
import com.planetsystems.tela.activities.staff.administration.taskAttendance.SupervisorObservations;
import com.planetsystems.tela.activities.staff.administration.taskAttendance.TaskAttendance;
import com.planetsystems.tela.data.timetable.SyncTimeTable;

import java.util.ArrayList;
import java.util.List;

public class TimeTable extends AppCompatActivity {

    RecyclerView taskList;
    String day_extra;
    String class_unit, class_id;
    int count = 0;
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
        class_unit = bundle.getString("class");
        class_id = bundle.getString("class_id");

        setTitle(day_extra + "'s" + " Timetable" + " for " + class_unit);

        mSyncTimeTables = new ArrayList<>();

        adapter = new TimeTableAdapter(this, mSyncTimeTables, day_extra);
        taskList.setAdapter(adapter);
        taskList.setLayoutManager(new LinearLayoutManager(this));

        editTimeTableViewModel = new ViewModelProvider(this).get(EditTimeTableViewModel.class);

        editTimeTableViewModel.timetable(day_extra, class_unit).observe(this, new Observer<List<SyncTimeTable>>() {
            @Override
            public void onChanged(List<SyncTimeTable> syncTimeTables) {
                if(syncTimeTables.size() != count ){
                    adapter.setTaskList(syncTimeTables);
                }else {
                    new AlertDialog.Builder(TimeTable.this)
                            .setTitle("Confirmation")
                            .setMessage("Dear Supervisor, based on the school timetable there is no task list for " + class_unit + " class on "+ day_extra + ".")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(TimeTable.this, SelectDay.class);
                                    intent.putExtra("class_id", class_id);
                                    intent.putExtra("class", class_unit);
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    TimeTable.this.finish();
                                }}).show();
                }
            }
        });
    }
}
