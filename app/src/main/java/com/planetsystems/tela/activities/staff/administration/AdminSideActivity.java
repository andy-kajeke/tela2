package com.planetsystems.tela.activities.staff.administration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.enrollActivity.EnrollmentActivity;
import com.planetsystems.tela.activities.staff.administration.editStaff.EditStaffList;
import com.planetsystems.tela.activities.staff.administration.editTimeTable.SelectClass;
import com.planetsystems.tela.activities.staff.administration.enrollments.EnrolledTeachers;
import com.planetsystems.tela.activities.staff.administration.enrollments.SelectLearnerClass;
import com.planetsystems.tela.activities.staff.administration.learnerAttendance.LearnerClasses;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.RequestsMade;
import com.planetsystems.tela.activities.staff.administration.taskAttendance.TaskAttendance;
import com.planetsystems.tela.activities.staff.administration.timeAttendance.TimeAttendanceList;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivity;
import com.planetsystems.tela.workers.WorkManagerTrigger;

public class AdminSideActivity extends AppCompatActivity {
    public static final String TEACHER_FIRST_NAME = "com.planetsystems.tela.activities.staff.regularStaff. TeacherHomeActivity.TEACHER_FIRST_NAME";
    public static final String TEACHER_LAST_NAME = "com.planetsystems.tela.activities.staff.regularStaff. TeacherHomeActivity.TEACHER_LAST_NAME";
    public static final String EMPLOYEE_NUMBER = "com.planetsystems.tela.activities.staff.regularStaff. TeacherHomeActivity.EMPLOYEE_ID";
    public static final String SCHOOL_NUMBER = "com.planetsystems.tela.activities.staff.regularStaff. TeacherHomeActivity.SCHOOL_NUMBER";


    CardView myLessons;
    CardView attendClass;
    CardView learner;
    CardView staff;
    CardView requests;
    CardView update;
    CardView sync;
    Dialog updateDialog;
    Dialog selectDayDialog;
    EditText _date, staff_comment;

    TextView close;
    Button enroll_new_staff, teachers_enrolled, learners_enrolled;
    Button edit_time_table;
    Button edit_staff_list;

    TextView headName,headRole;
    String admin_id_extra;
    String role_extra;
    String name_extra;
    String checkIn_date;
    String checkIn_schoolId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_side);

        headName = findViewById(R.id.headTeacher);
        headRole = findViewById(R.id.adminPosition);
        myLessons = findViewById(R.id.cardview1);
        attendClass = findViewById(R.id.attendClass);
        learner = findViewById(R.id.cardview4);
        staff = findViewById(R.id.attendWork);
        requests = findViewById(R.id.cardview5);
        update = findViewById(R.id.cardview6);
        sync = findViewById(R.id.cardview7);

        updateDialog = new Dialog(this);

        Bundle bundle = getIntent().getExtras();
        admin_id_extra = bundle.getString("employee_No");
        name_extra = bundle.getString("employee_Name");
        checkIn_schoolId = bundle.getString(SCHOOL_NUMBER);
//
        headRole.setText("[ " + "Head Teacher" + " ]");
        headName.setText(name_extra);

        if (!isConnected()) {
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }else {
            //Synchronize the school data to phone and to the sever
            WorkManagerTrigger.startFetchWorkers(getApplicationContext());
            WorkManagerTrigger.startUploadWorkers(getApplicationContext());
        }

        myLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TeacherHomeActivity.class);
                i.putExtra("employee_No", admin_id_extra);
                i.putExtra("employee_Name", name_extra);
                startActivity(i);
            }
        });

        //List all clocked in staff members by date of week
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TimeAttendanceList.class);
//                i.putExtra("employee_No", HT_Id);
                startActivity(i);
            }
        });

        attendClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TaskAttendance.class);
//                i.putExtra("id", HT_Id);
                startActivity(i);
            }
        });

        learner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LearnerClasses.class);
                startActivity(i);
            }
        });

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RequestsMade.class);
                i.putExtra("employee_No", admin_id_extra);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUpdatePopup();
            }
        });
    }

    public void showUpdatePopup() {

        updateDialog.setContentView(R.layout.schoolupdatepopup);

        close =(TextView) updateDialog.findViewById(R.id.txclose);
        enroll_new_staff = (Button) updateDialog.findViewById(R.id.enrollNewStaff);
        teachers_enrolled = (Button) updateDialog.findViewById(R.id.enrolledStaff);
        learners_enrolled = (Button) updateDialog.findViewById(R.id.enrolledLearners);
        edit_staff_list = (Button) updateDialog.findViewById(R.id.staff_list);
        edit_time_table = (Button) updateDialog.findViewById(R.id.time_table);
        //update_staff =(Button) updateDialog.findViewById(R.id.out);

        enroll_new_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EnrollmentActivity.class);
                //i.putExtra("school", checkIn_schoolId);
                startActivity(i);
            }
        });

        teachers_enrolled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EnrolledTeachers.class);
                //i.putExtra("school", checkIn_schoolId);
                startActivity(i);
            }
        });

        learners_enrolled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SelectLearnerClass.class);
                i.putExtra("employeeNo", admin_id_extra);
                startActivity(i);
            }
        });

        edit_staff_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), EditStaffList.class);
                //i.putExtra("school", checkIn_schoolId);
                startActivity(i);

            }
        });

        edit_time_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SelectClass.class);
                i.putExtra("employeeNo", admin_id_extra);
                startActivity(i);
            }
        });

//        update_staff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        close.setText("X");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDialog.dismiss();
            }
        });

        updateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        updateDialog.show();

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
