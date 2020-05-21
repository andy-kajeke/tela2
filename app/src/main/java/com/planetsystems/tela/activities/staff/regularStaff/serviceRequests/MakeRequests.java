package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        setTitle("Service Requests");

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

        //On select specific request
        schoolMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent sch = new Intent(MakeRequests.this, ListSchoolMaterial.class);
//                sch.putExtra("id", id_extra);
//                sch.putExtra("name", name_extra);
//                sch.putExtra("school_id", school_extra);
//                startActivity(sch);

            }
        });
        timeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sch = new Intent(MakeRequests.this, RequestTimeOff.class);
                sch.putExtra("id", id_extra);
                sch.putExtra("name", name_extra);
                startActivity(sch);

            }
        });
        meetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sch = new Intent(MakeRequests.this, RequestMeeting.class);
                sch.putExtra("id", id_extra);
                sch.putExtra("name", name_extra);
                startActivity(sch);

            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sch = new Intent(MakeRequests.this, RequestHelp.class);
                sch.putExtra("id", id_extra);
                sch.putExtra("name", name_extra);
                startActivity(sch);

            }
        });

        replies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // showUpdatePopup();

            }
        });
    }
}
