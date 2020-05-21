package com.planetsystems.tela.activities.staff.administration.editTimeTable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;

public class EditTimeTable extends AppCompatActivity {

    private EditTimeTableViewModel editTimeTableViewModel;
    SyncTimeTableDao syncTimeTableDao;
    EditText task_Name,staff_name, staff_code;
    EditText startTym,endTym;
    CardView submitChanges;

    String staffNumber_extra;
    String task_Name_extra;
    int row_id_extra;
    String startTime_extra;
    String endTime_extra;
    String staffName_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time_table);
        setTitle("Edit TimeTable");

        task_Name = findViewById(R.id.taskName);
        startTym = findViewById(R.id.startTime);
        endTym = findViewById(R.id.endTime);
        staff_name = findViewById(R.id.staffName);
        staff_code = findViewById(R.id.staffCode);
        submitChanges = findViewById(R.id.submit_changes);

        Bundle bundle = getIntent().getExtras();
        staffNumber_extra = bundle.getString("teacherNumber");
        row_id_extra = bundle.getInt("row_id");
        task_Name_extra = bundle.getString("taskName");
        startTime_extra = bundle.getString("startTime");
        endTime_extra = bundle.getString("endTime");
        staffName_extra = bundle.getString("teacherName");

        task_Name.append(task_Name_extra);
        startTym.append(startTime_extra);
        endTym.append(endTime_extra);
        staff_name.append(staffName_extra);
        staff_code.append(staffNumber_extra);

        submitChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(EditTimeTable.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to submit these changes?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                PostChangeMade();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });

        editTimeTableViewModel = new ViewModelProvider(this).get(EditTimeTableViewModel.class);
    }

    private void PostChangeMade() {

        String startTime = startTym.getText().toString();
        String endTime = endTym.getText().toString();
        String employeeNo = staff_code.getText().toString();
        String employeeName = staff_name.getText().toString();

        editTimeTableViewModel.updateTimeTable(startTime, endTime, employeeNo, employeeName, row_id_extra);
    }
}
