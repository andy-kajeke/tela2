package com.planetsystems.tela.activities.enrollActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.fingerprint.FingerPrintActivity;

public class EnrollmentActivity extends AppCompatActivity {

    EditText edit_fName, edit_lName;
    EditText edit_initials, edit_phone_No;
    EditText edit_email, edit_nationalID, edit_gender;
    CardView addUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);
        setTitle(R.string.enroll);

        edit_fName = (EditText) findViewById(R.id.fName);
        edit_lName = (EditText) findViewById(R.id.lName);
        edit_initials = (EditText) findViewById(R.id.initials);
        edit_email = (EditText) findViewById(R.id.email);
        edit_phone_No = (EditText) findViewById(R.id.phoneNo);
        edit_nationalID = (EditText) findViewById(R.id.nationalId);
        edit_gender = (EditText) findViewById(R.id.gender);
        addUser = (CardView) findViewById(R.id.adduser);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollmentActivity.this, FingerPrintActivity.class);
                intent.setAction(FingerPrintActivity.ACTION_ENROLL);
                intent.putExtra(FingerPrintActivity.FIRST_NAME, edit_fName.getText().toString());
                intent.putExtra(FingerPrintActivity.LAST_NAME, edit_lName.getText().toString());
                intent.putExtra(FingerPrintActivity.INITIALS, edit_initials.getText().toString());
                intent.putExtra(FingerPrintActivity.EMAIL_ADDRESS, edit_email.getText().toString());
                intent.putExtra(FingerPrintActivity.PHONE_NUMBER, edit_phone_No.getText().toString());
                intent.putExtra(FingerPrintActivity.NATIONAL_ID, edit_nationalID.getText().toString());
                intent.putExtra(FingerPrintActivity.GENDER, edit_gender.getText().toString());
                startActivity(intent);
            }
        });
    }
}
