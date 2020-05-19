package com.planetsystems.tela.activities.staff.administration.taskAttendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.home.TasksAdapter;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivityViewModel;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timetable.SyncTimeTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SupervisorObservations extends AppCompatActivity {

    TeacherHomeActivityViewModel teacherHomeActivityViewModel;
    List<SynTimeOnTask> mSynTimeOnTask;
    TasksConfirmedAdapter adapter;
    RecyclerView tasks;
    TextView datetoday;
    TextView emp_Name;
    TextView emp_Id;
    TextView tvname;
    //TextView datetoday;
    Button submit, selfmenu;
    public String dateString, timeString;
    String emp_id_extra, emp_name_extra, admin_id_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_observations);
        setTitle("Supervisor Observations");

        tasks = findViewById(R.id.task_list);
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

        Bundle bundle = getIntent().getExtras();
        admin_id_extra = bundle.getString("admin");
        emp_id_extra = bundle.getString("employee_No");
        emp_name_extra = bundle.getString("employee_Name");

        emp_Name.append(emp_name_extra);
        emp_Id.append(emp_id_extra);

        //Listing individual task in recyclerview
        mSynTimeOnTask = new ArrayList<>();
        adapter = new TasksConfirmedAdapter(this, mSynTimeOnTask);
        tasks.setAdapter(adapter);
        tasks.setLayoutManager(new LinearLayoutManager(this));

        teacherHomeActivityViewModel = new ViewModelProvider(this).get(TeacherHomeActivityViewModel.class);

        teacherHomeActivityViewModel.tasksWithPresentActionStatus(emp_id_extra, dateString, "ACTIVE").observe(this, new Observer<List<SynTimeOnTask>>() {
            @Override
            public void onChanged(List<SynTimeOnTask> synTimeOnTasks) {
                //Toast.makeText(getApplicationContext(), "size is: " + String.valueOfteacher_id(synTimeOnTasks.size()), Toast.LENGTH_LONG).show();
                adapter.setTaskList(synTimeOnTasks);
            }
        });
    }
}
