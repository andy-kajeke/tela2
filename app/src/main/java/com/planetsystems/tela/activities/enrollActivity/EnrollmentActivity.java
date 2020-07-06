package com.planetsystems.tela.activities.enrollActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.fingerprint.FingerPrintActivity;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;
import java.util.Objects;

public class EnrollmentActivity extends AppCompatActivity {
    public static final String FIRST_NAME = "com.planetsystems.tela.activities.enrollActivity.FIRST_NAME";
    public static final String LAST_NAME = "com.planetsystems.tela.activities.enrollActivity.LAST_NAME";
    public static final String INITIALS = "com.planetsystems.tela.activities.enrollActivity.INITIALS";
    public static final String PHONE_NUMBER = "com.planetsystems.tela.activities.enrollActivity.PHONE_NUMBER";
    public static final String EMAIL_ADDRESS = "com.planetsystems.tela.activities.enrollActivity.EMAIL_ADDRESS";
    public static final String NATIONAL_ID = "com.planetsystems.tela.activities.enrollActivity.NATIONAL_ID";
    public static final String GENDER = "com.planetsystems.tela.activities.enrollActivity.GENDER";
    private EnrollmentActivityViewModel activityViewModel;

    public static final int CAPTURE_FINGER_PRINT_REQUEST = 343;
    
    EditText edit_fName, edit_lName;
    EditText edit_initials, edit_phone_No;
    EditText edit_email, edit_nationalID, edit_gender;
    CardView addUser;
    List<SyncTeacher> syncTeachers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);
        setTitle(R.string.enroll);

        edit_fName = findViewById(R.id.fName);
        edit_lName = findViewById(R.id.lName);
        edit_initials = findViewById(R.id.initials);
        edit_email = findViewById(R.id.email);
        edit_phone_No = findViewById(R.id.phoneNo);
        edit_nationalID = findViewById(R.id.nationalId);
        edit_gender = findViewById(R.id.gender);
        addUser = findViewById(R.id.adduser);

        activityViewModel = new ViewModelProvider(this).get(EnrollmentActivityViewModel.class);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollmentActivity.this, FingerPrintActivity.class);
                intent.setAction(FingerPrintActivity.ACTION_ENROLL);
                intent.putExtra(FingerPrintActivity.TEACHER_FIRST_NAME, edit_fName.getText().toString());
                intent.putExtra(FingerPrintActivity.TEACHER_LAST_NAME, edit_lName.getText().toString());
                intent.putExtra(FingerPrintActivity.TEACHER_EMAIL, edit_email.getText().toString());
                intent.putExtra(FingerPrintActivity.TEACHER_NATIONAL_ID, edit_nationalID.getText().toString());
                intent.putExtra(FingerPrintActivity.TEACHER_PHONE_NUMBER, edit_phone_No.getText().toString());
                intent.putExtra(FingerPrintActivity.TEACHER_LICENSED, false);
                intent.putExtra(FingerPrintActivity.TEACHER_GENDER, edit_gender.getText().toString());
                intent.putExtra(FingerPrintActivity.TEACHER_INITIALS, edit_initials.getText().toString());
                startActivityForResult(intent, CAPTURE_FINGER_PRINT_REQUEST);
            }
        });

        activityViewModel.getSynTeachers().observe(this, new Observer<List<SyncTeacher>>() {
            @Override
            public void onChanged(List<SyncTeacher> syncTeachers) {
                activityViewModel.setEnrolledTeachers(syncTeachers);
            }
        });

        activityViewModel.getAllTeachers().observe(this, new Observer<List<SyncTeacher>>() {
            @Override
            public void onChanged(List<SyncTeacher> teachers) {
                syncTeachers = teachers;
            }
        });
    }
}
