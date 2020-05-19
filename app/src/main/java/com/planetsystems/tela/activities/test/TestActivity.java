package com.planetsystems.tela.activities.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivityViewModel;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivityViewModel;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timetable.SyncTimeTable;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.textView);

//        TestActivityViewModel testActivityViewModel = new ViewModelProvider(this).get(TestActivityViewModel.class);
//        testActivityViewModel.schoolClasses().observe(this, new Observer<List<SyncSchoolClasses>>() {
//            @Override
//            public void onChanged(List<SyncSchoolClasses> schoolClasses) {
//                String teacherName = "";
//                for (int i = 0; i < schoolClasses.size(); i++) {
//                    teacherName = teacherName + " \n " + schoolClasses.get(i).getClassName();
//                }
//                textView.setText(teacherName);
//
//            }
//        });

//        ClockInAndOutActivityViewModel clockInAndOutActivityViewModel = new ViewModelProvider(this).get(ClockInAndOutActivityViewModel.class);
//        clockInAndOutActivityViewModel.allClockOuts().observe(this, new Observer<List<SyncClockOut>>() {
//            @Override
//            public void onChanged(List<SyncClockOut> syncClockOuts) {
//                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(syncClockOuts.size()), Toast.LENGTH_LONG).show();
//                String teacherName = "";
//                for (int i = 0; i < syncClockOuts.size(); i++) {
//                    teacherName = teacherName + " \n " + syncClockOuts.get(i).getFirstName() + " | "+ syncClockOuts.get(i).getComment() + " | "+ syncClockOuts.get(i).getTime()
//                    + " | " + syncClockOuts.get(i).getDay();
//                }
//                textView.setText(teacherName);
//            }
//        });

        TeacherHomeActivityViewModel teacherHomeActivityViewModel = new ViewModelProvider(this).get(TeacherHomeActivityViewModel.class);
        teacherHomeActivityViewModel.getTimeOnTasks().observe(this, new Observer<List<SynTimeOnTask>>() {
            @Override
            public void onChanged(List<SynTimeOnTask> synTimeOnTasks) {
                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(synTimeOnTasks.size()), Toast.LENGTH_LONG).show();
                String teacherName = "";
                for (int i = 0; i < synTimeOnTasks.size(); i++) {
                    teacherName = teacherName + " \n " + synTimeOnTasks.get(i).getEmployeeFirstName() + " | " + synTimeOnTasks.get(i).getTaskName() + " | " + synTimeOnTasks.get(i).getActionStatus();
                }
                textView.setText(teacherName);
            }
        });
    }
}
