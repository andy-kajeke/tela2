package com.planetsystems.tela.activities.staff.administration.learnerAttendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.attendance.SyncAttendanceRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LearnerAttendance extends AppCompatActivity {

    TextView className,day,totalPresent,totalAbsent;
    EditText numBoysPresent, numBoysAbsent;
    EditText numGalsPresent, numGalsAbsent;
    CheckBox cPresent, cAbsent;
    EditText comment;
    Button postInfo;

    String id_extra;
    String class_extra;
    String class_id_extra;
    String admin_extra;
    String dateString;
    String dayOfTheWeek;
    int boys_present, boys_absent;
    int girls_present, girls_absent;
    int sum_present;
    int sum_absent;

    LearnerAttendanceViewModel learnerAttendanceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner_attendance);
        setTitle("Learner Attendance");

        day = (TextView) findViewById(R.id.date_of_day);
        className = (TextView) findViewById(R.id.class_name);
        totalPresent = (TextView) findViewById(R.id.totalPresent);
        totalAbsent = (TextView) findViewById(R.id.totalAbsent);
        numBoysPresent = (EditText) findViewById(R.id.inputBoysPresent);
        numBoysAbsent = (EditText) findViewById(R.id.inputBoysAbsent);
        numGalsPresent = (EditText) findViewById(R.id.inputGirlsPresent);
        numGalsAbsent = (EditText) findViewById(R.id.inputGirlsAbsent);
        cPresent = (CheckBox) findViewById(R.id.checkPresent);
        cAbsent = (CheckBox) findViewById(R.id.checkAbsent);
        comment = (EditText) findViewById(R.id.edit_text);
        postInfo = (Button) findViewById(R.id.submit);

        Bundle bundle = getIntent().getExtras();
//        id_extra = bundle.getString("id");
        class_extra = bundle.getString("class");
        class_id_extra = bundle.getString("class_id");
        admin_extra = bundle.getString("admin");

        className.setText("Class:  " + "Primary " + class_extra);

        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        dateString = dateFormat.format(date);
        day.setText(dateString);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);

        cPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    boys_present = Integer.parseInt(numBoysPresent.getText().toString());
                    girls_present = Integer.parseInt(numGalsPresent.getText().toString());
                    sum_present = boys_present + girls_present;
                    totalPresent.append(String.valueOf(sum_present));
                }

            }
        });

        cAbsent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    boys_absent = Integer.parseInt(numBoysAbsent.getText().toString());
                    girls_absent = Integer.parseInt(numGalsAbsent.getText().toString());
                    sum_absent = boys_absent + girls_absent;
                    totalAbsent.append(String.valueOf(sum_absent));
                }

            }
        });

        postInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(LearnerAttendance.this)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure you want to submit?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                PostLearnerAttendance();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        learnerAttendanceViewModel = new ViewModelProvider(this).get(LearnerAttendanceViewModel.class);
        learnerAttendanceViewModel.learnerRecords().observe(this, new Observer<List<SyncAttendanceRecord>>() {
            @Override
            public void onChanged(List<SyncAttendanceRecord> syncAttendanceRecords) {
                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(syncAttendanceRecords.size()), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void PostLearnerAttendance() {
        SyncAttendanceRecord syncAttendanceRecord = new SyncAttendanceRecord(
                "",
                "",
                1,
                comment.getText().toString(),
                class_extra,
                class_id_extra,
                numGalsAbsent.getText().toString(),
                numGalsPresent.getText().toString(),
                numBoysAbsent.getText().toString(),
                numBoysPresent.getText().toString(),
                dateString,
                admin_extra,
                dayOfTheWeek
        );

        learnerAttendanceViewModel.learnerAttendance(syncAttendanceRecord);
    }
}
