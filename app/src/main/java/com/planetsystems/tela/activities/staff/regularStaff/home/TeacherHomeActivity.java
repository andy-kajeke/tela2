package com.planetsystems.tela.activities.staff.regularStaff.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.activities.staff.administration.editTimeTable.TimeTable;
import com.planetsystems.tela.activities.staff.administration.taskAttendance.TaskAttendance;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timetable.SyncTimeTable;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request.MakeRequests;
import com.planetsystems.tela.utils.DynamicData;
import com.planetsystems.tela.workers.WorkManagerTrigger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TeacherHomeActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TeacherHomeActivityViewModel teacherHomeActivityViewModel;
    RecyclerView tasks;
    TextView datetoday;
    TextView emp_Name;
    TextView emp_Id;
    ImageView back;
    int count = 0;
    Button submit, selfmenu;
    public List<Tasks> mSyncTimeTables;
    TasksAdapter adapter;
    public String dateString, timeString;
    String emp_id_extra, emp_name_extra, school_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        back = findViewById(R.id.back);
        tasks = findViewById(R.id.task_list);
        emp_Name = findViewById(R.id.namexx);
        emp_Id = findViewById(R.id.staffId);
        datetoday = findViewById(R.id.datetoday);
        submit = findViewById(R.id.submit);
        selfmenu = findViewById(R.id.menuBtn);

        Date currentTime = Calendar.getInstance().getTime();
        datetoday.setText(""+currentTime.toString());

        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
        dateString = dateFormat.format(date);
        timeString = time.format(date);

        /////////////////////////////Sync data/////////////////////////////////////////////////////////
        WorkManagerTrigger.startFetchWorkers(getApplicationContext());
        WorkManagerTrigger.startUploadWorkers(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        emp_id_extra = bundle.getString("employee_No");
        emp_name_extra = bundle.getString("employee_Name");

        emp_Name.append(emp_name_extra);
        emp_Id.append(emp_id_extra);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), ClockInAndOutActivity.class);
                startActivity(bk);
            }
        });

        //Listing individual task in recyclerview
        mSyncTimeTables = new ArrayList<>();
        adapter = new TasksAdapter(this, mSyncTimeTables);
        tasks.setAdapter(adapter);
        tasks.setLayoutManager(new LinearLayoutManager(this));

        teacherHomeActivityViewModel = new ViewModelProvider(this).get(TeacherHomeActivityViewModel.class);

        teacherHomeActivityViewModel.getSyncTimeTableByEmployeeIDForDay(emp_id_extra, DynamicData.getDay()).observe(this, new Observer<List<SyncTimeTable>>() {
            @Override
            public void onChanged(List<SyncTimeTable> syncTimeTables ) {
                if(syncTimeTables.size() != count ){
                    for (int i = 0; i < syncTimeTables.size(); i++){
                        Tasks taskList = new Tasks();
                        taskList.setSubject(syncTimeTables.get(i).getSubject());
                        taskList.setTaskId(syncTimeTables.get(i).getTaskId());
                        taskList.setTaskName(syncTimeTables.get(i).getTaskName());
                        taskList.setStartTime(syncTimeTables.get(i).getStartTime());
                        taskList.setEndTime(syncTimeTables.get(i).getEndTime());
                        taskList.setStatus("Present");
                        mSyncTimeTables.add(taskList);
                    }

                    adapter.setTaskList(mSyncTimeTables);
                }else {
                    new AlertDialog.Builder(TeacherHomeActivity.this)
                            .setTitle("Confirmation")
                            .setMessage("Dear " + emp_name_extra + ", based on the school timetable there is no task list for you on "+ DynamicData.getDay() + ". " +
                                    "\n Thank you..")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
//                                    Intent intent = new Intent(TeacherHomeActivity.this, TaskAttendance.class);
//                                    intent.putExtra("class_id", class_id);
//                                    intent.putExtra("class", class_unit);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    startActivity(intent);
//                                    TeacherHomeActivity.this.finish();
                                }}).show();
                }
            }
        });

        teacherHomeActivityViewModel.getTimeOnTasks().observe(this, new Observer<List<SynTimeOnTask>>() {
            @Override
            public void onChanged(List<SynTimeOnTask> synTimeOnTasks) {
//                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(synTimeOnTasks.size()), Toast.LENGTH_LONG).show();
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
                                postToSyncTimeOnTask();
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
    private void postToSyncTimeOnTask(){
        teacherHomeActivityViewModel.getSyncTimeTableByEmployeeIDForDay(emp_id_extra, DynamicData.getDay()).observe(this, new Observer<List<SyncTimeTable>>() {
            @Override
            public void onChanged(List<SyncTimeTable> syncTimeTables) {
                if(syncTimeTables.size() != count ){
                    for(Tasks Task : mSyncTimeTables){
                        SynTimeOnTask synTimeOnTask = new SynTimeOnTask(
                                "",
                                "",
                                Task.getStatus(),
                                Task.getStatus(),
                                Task.getStatus(),
                                emp_id_extra,
                                emp_id_extra,
                                Task.getTaskId(),
                                timeString,
                                dateString,
                                "",
                                "",
                                "",
                                emp_name_extra,
                                emp_name_extra,
                                Task.getEndTime(),
                                Task.getStartTime(),
                                Task.getTaskName(),
                                false,
                                false
                        );

                        teacherHomeActivityViewModel.postToSyncTimeOnTask(synTimeOnTask);
                        Toast.makeText(getApplicationContext(), "Submitted Successfully", Toast.LENGTH_LONG).show();
                    }
                }else {
                    new AlertDialog.Builder(TeacherHomeActivity.this)
                            .setTitle("Confirmation")
                            .setMessage("Dear " + emp_name_extra + ", based on the school timetable there is no task list for you on "+ DynamicData.getDay() + ". " +
                                    "\n You can't submit an empty task list.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                }}).show();
                }
            }
        });
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
            intent.putExtra("id", emp_id_extra);
            startActivity(intent);
            //return true;
        }
//        else if (id == R.id.myStatus) {
//
//            Intent intent = new Intent(Emp_Home.this, MyStatus.class);
//            intent.putExtra("name", emp_name_extra);
//            intent.putExtra("id", emp_id_extra);
//            startActivity(intent);
//            //return true;
//        }
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

    public List taskList(){
        return mSyncTimeTables;
    }
}
