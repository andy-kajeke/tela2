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
                Intent i = new Intent(EnrollmentActivity.this, FingerPrintActivity.class);
//                i.putExtra("time",clock_in_time.getText());
//                i.putExtra("date",dayString);
                startActivity(i);
            }
        });
    }
}
