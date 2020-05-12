package com.planetsystems.tela.staff.regularStaff.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.GenerateRandomString;
import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.activities.fingerprint.FingerPrintActivity;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.data.timetable.SyncTimeTableDao;
import com.planetsystems.tela.staff.regularStaff.serviceRequests.MakeRequests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TeacherHomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    public static final String TEACHER_FIRST_NAME = "com.planetsystems.tela.staff.regularStaff. TeacherHomeActivity.TEACHER_FIRST_NAME";
    public static final String TEACHER_LAST_NAME = "com.planetsystems.tela.staff.regularStaff. TeacherHomeActivity.TEACHER_LAST_NAME";
    public static final String EMPLOYEE_NUMBER = "com.planetsystems.tela.staff.regularStaff. TeacherHomeActivity.EMPLOYEE_ID";

    TeacherHomeActivityViewModel teacherHomeActivityViewModel;

    ProgressDialog dialog;
    int count =0;
    int count2 =0;
    RecyclerView tasks;
    TextView datetoday;
    TextView emp_Name;
    TextView emp_Id;
    TextView tvname;
    //TextView datetoday;
    Button submit, selfmenu;
    ArrayList<SyncTimeTable> mSyncTimeTables;
    ArrayList<SyncTimeTable> taskList;
    TasksAdapter adapter;
    //SynTimeOnTask synTimeOnTask;
    public String id_extra;
    String lat_extra, long_extra;
    String emp_id_extra, emp_name_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        tasks = findViewById(R.id.task_list);
        emp_Name = findViewById(R.id.namexx);
        emp_Id = findViewById(R.id.staffId);
        datetoday = findViewById(R.id.datetoday);
        submit = findViewById(R.id.submit);
        selfmenu = findViewById(R.id.menuBtn);

        Date currentTime = Calendar.getInstance().getTime();
        datetoday.setText(""+currentTime.toString());

        Bundle bundle = getIntent().getExtras();
        emp_id_extra = bundle.getString(EMPLOYEE_NUMBER);
        emp_name_extra = bundle.getString(TEACHER_FIRST_NAME);

        emp_Name.append(emp_name_extra);
        emp_Id.append(emp_id_extra);

        //Listing individual task in recyclerview
        mSyncTimeTables = new ArrayList<>();
        adapter = new TasksAdapter(this, mSyncTimeTables);
        tasks.setAdapter(adapter);
        tasks.setLayoutManager(new LinearLayoutManager(this));

        teacherHomeActivityViewModel = new ViewModelProvider(this).get(TeacherHomeActivityViewModel.class);

        teacherHomeActivityViewModel.timetables(emp_id_extra, "Thursday").observe(this, new Observer<List<SyncTimeTable>>() {
            @Override
            public void onChanged(List<SyncTimeTable> syncTimeTables) {
                for (int i = 0; i < syncTimeTables.size(); i++){
                    adapter.setTaskList(syncTimeTables);
                }
            }
        });

        teacherHomeActivityViewModel.taskRecords().observe(this, new Observer<List<SynTimeOnTask>>() {
            @Override
            public void onChanged(List<SynTimeOnTask> synTimeOnTasks) {
                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(synTimeOnTasks.size()), Toast.LENGTH_LONG).show();
            }
        });

        //submit attendance commitment
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(TeacherHomeActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to submit your attendance?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                PostToSyncTimeOnTask();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        //more options
        selfmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(TeacherHomeActivity.this, v);
                popup.setOnMenuItemClickListener(TeacherHomeActivity.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }

    // save teacher confirmation on tasks to syncTimeOneTasks table
    private void PostToSyncTimeOnTask(){

        for(SyncTimeTable Task:mSyncTimeTables){
            SynTimeOnTask synTimeOnTask = new SynTimeOnTask(
                    "",
                    "",
                    "",
                    "Present",
                    "",
                    emp_id_extra,
                    emp_id_extra,
                    Task.getTaskId(),
                    "",
                    "",
                    "",
                    "",
                    emp_name_extra,
                    emp_name_extra,
                    Task.getEndTime(),
                    Task.getStartTime(),
                    Task.getTaskName()
            );

            //Toast.makeText(getApplicationContext(), "Submitted successfully..", Toast.LENGTH_LONG).show();

            //TeacherHomeActivityViewModel teacherHomeActivityViewModel = new ViewModelProvider(this).get(TeacherHomeActivityViewModel.class);
            teacherHomeActivityViewModel.timeOnTask(synTimeOnTask);

        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.request) {

            Intent intent = new Intent(TeacherHomeActivity.this, MakeRequests.class);
            intent.putExtra("name", emp_name_extra);
            //intent.putExtra("school_id", school_extra);
            intent.putExtra("id", emp_id_extra);
            startActivity(intent);
            //return true;
        }
        else if (id == R.id.myStatus) {

//            Intent intent = new Intent(Emp_Home.this, MyStatus.class);
//            intent.putExtra("name", emp_name_extra);
//            intent.putExtra("id", emp_id_extra);
//            startActivity(intent);
            //return true;
        }
        else if (id == R.id.Logout) {
            Intent intent = new Intent(TeacherHomeActivity.this, ClockInAndOutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            TeacherHomeActivity.this.finish();
            //return true;
        }

        return super.onOptionsItemSelected(item);
        //return false;
    }
}
