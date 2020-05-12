package com.planetsystems.tela.activities.staff.administration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.editStaff.EditStaffList;
import com.planetsystems.tela.activities.staff.administration.learnerAttendance.LearnerClasses;
import com.planetsystems.tela.activities.staff.administration.timeAttendance.TimeAttendanceList;

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
    Button update_staff;
    Button edit_time_table;
    Button edit_staff_list;
    TextView headName,headRole;
    String HT_Id;
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
        HT_Id = bundle.getString("admin_id");
//        role_extra = bundle.getString("role");
//        name_extra = bundle.getString("admin_name");
//        checkIn_date = bundle.getString("date");
        checkIn_schoolId = bundle.getString(SCHOOL_NUMBER);
//
        headRole.setText("[ " + "Head Teacher" + " ]");
        headName.setText(name_extra);

        //List all clocked in staff members by date of week
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TimeAttendanceList.class);
//                i.putExtra("id", HT_Id);
//                i.putExtra("date", checkIn_date);
//                i.putExtra("school", checkIn_schoolId);
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
        edit_staff_list = (Button) updateDialog.findViewById(R.id.staff_list);
        edit_time_table = (Button) updateDialog.findViewById(R.id.time_table);
        //update_staff =(Button) updateDialog.findViewById(R.id.out);

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
//                Intent i = new Intent(getApplicationContext(),SelectClass.class);
//                i.putExtra("school", checkIn_schoolId);
//                startActivity(i);
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
}
