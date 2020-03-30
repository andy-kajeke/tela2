package com.planetsystems.tela.staff.regularStaff.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.planetsystems.tela.R;

public class MakeRequests extends AppCompatActivity {

    EditText staffName, staffId;
    Button schoolMaterials, timeOff, meetings, help, replies;

    TextView close;
    Button viewSchMaterials;
    Button viewTimeOff;
    Button viewMeeting;
    Button viewHelp;
    Dialog updateDialog;

    String name_extra;
    String id_extra;
    String school_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_requests);

        staffName = findViewById(R.id.employeeName);
        staffId = findViewById(R.id.employeeID);
        schoolMaterials = findViewById(R.id.SchoolMaterials);
        timeOff = findViewById(R.id.TimeOff);
        meetings = findViewById(R.id.Meeting);
        help = findViewById(R.id.Help);
        replies = findViewById(R.id.replies);

        Bundle bundle = getIntent().getExtras();
        id_extra = bundle.getString("id");
        name_extra = bundle.getString("name");

        staffName.append(name_extra);
        staffId.append(id_extra);
    }
}
