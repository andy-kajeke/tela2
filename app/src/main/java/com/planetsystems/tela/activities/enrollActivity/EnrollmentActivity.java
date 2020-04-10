package com.planetsystems.tela.activities.enrollActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.fingerprint.FingerPrintActivity;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

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
                startActivityForResult(intent, CAPTURE_FINGER_PRINT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_FINGER_PRINT_REQUEST && resultCode == RESULT_OK) {

        } else if (requestCode == CAPTURE_FINGER_PRINT_REQUEST && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Finger print Capture Canceled", Toast.LENGTH_LONG).show();
        }
    }
}
