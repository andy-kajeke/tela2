package com.planetsystems.tela.staff.administration.editStaff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.EditText;

import com.planetsystems.tela.R;

public class UpdateStaffRecord extends AppCompatActivity {

    EditText edit_fName, edit_lName;
    EditText edit_initials, edit_phone_No;
    EditText edit_email, edit_nationalID, edit_gender;
    CardView submitChanges;

    String id_extra, firstName_extra, lastName_extra;
    String employeeNumber_extra, emailAddress_extra;
    String initials_extra;
    boolean licensed_extra;
    String nationalId_extra;
    String phoneNumber_extra, deploymentSiteId_extra, employmentRoleId_extra, role_extra;
    String specialityId_extra, status_extra, employeeSkillCategoryId_extra;
    String dob_extra, gender_extra, registrationType_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_staff_record);
        setTitle("Edit Staff Record");

        edit_fName = (EditText) findViewById(R.id.fName);
        edit_lName = (EditText) findViewById(R.id.lName);
        edit_initials = (EditText) findViewById(R.id.initials);
        edit_email = (EditText) findViewById(R.id.email);
        edit_phone_No = (EditText) findViewById(R.id.phoneNo);
        edit_nationalID = (EditText) findViewById(R.id.nationalId);
        edit_gender = (EditText) findViewById(R.id.gender);
        submitChanges = (CardView) findViewById(R.id.submit_changes);

        Bundle bundle = getIntent().getExtras();
        id_extra = bundle.getString("id");
        employeeNumber_extra = bundle.getString("emp_No");
        firstName_extra = bundle.getString("emp_firstName");
        lastName_extra = bundle.getString("emp_lastName");
        emailAddress_extra = bundle.getString("emp_emailAddress");
        initials_extra = bundle.getString("emp_initials");
        licensed_extra = bundle.getBoolean("emp_licensed");
        nationalId_extra = bundle.getString("emp_nationalId");
        phoneNumber_extra = bundle.getString("emp_phoneNumber");
        deploymentSiteId_extra = bundle.getString("emp_deploymentSiteId");
        employmentRoleId_extra = bundle.getString("emp_employmentRoleId");
        role_extra = bundle.getString("emp_role");;
        status_extra = bundle.getString("emp_status");
        dob_extra = bundle.getString("emp_dob");
        gender_extra = bundle.getString("emp_gender");

        edit_fName.append(firstName_extra);
        edit_lName.append(lastName_extra);
        edit_initials.append(initials_extra);
        edit_phone_No.append(phoneNumber_extra);
        edit_email.append(emailAddress_extra);
        edit_nationalID.append(nationalId_extra);
        edit_gender.append(gender_extra);
    }
}
