package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.viewReplies.viewTimeOffRequest;

public class MakeRequests extends AppCompatActivity {

    EditText staffName, staffId;
    Button schoolMaterials, timeOff, meetings, help, replies;

    TextView close;
    Button viewSchMaterials;
    Button viewTimeOff;
    Button viewMeeting;
    Button viewHelp;
    Dialog viewReplyDialog;

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
        replies = findViewById(R.id.viewReplies);

        Bundle bundle = getIntent().getExtras();
        id_extra = bundle.getString("id");
        name_extra = bundle.getString("name");

        staffName.append(name_extra);
        staffId.append(id_extra);

        viewReplyDialog = new Dialog(this);

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
               showReplyPopup();
            }
        });
    }

    public void showReplyPopup(){

        viewReplyDialog.setContentView(R.layout.view_replies_popup);

        close =(TextView) viewReplyDialog.findViewById(R.id.txclose);
        viewSchMaterials = (Button) viewReplyDialog.findViewById(R.id.viewSchMaterials);
        viewTimeOff = (Button) viewReplyDialog.findViewById(R.id.viewTimeOff);
        viewMeeting =(Button) viewReplyDialog.findViewById(R.id.viewMeeting);
        viewHelp = (Button) viewReplyDialog.findViewById(R.id.viewHelp);

        viewSchMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent i = new Intent(getApplicationContext(), viewSchoolMaterial.class);
//                i.putExtra("id", id_extra);
//                i.putExtra("school", school_extra);
//                startActivity(i);

            }
        });

        viewTimeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), viewTimeOffRequest.class);
                i.putExtra("id", id_extra);
                startActivity(i);
            }
        });

        viewMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), viewMeetingRequest.class);
//                i.putExtra("id", id_extra);
//                i.putExtra("school", school_extra);
//                startActivity(i);
            }
        });

        viewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), viewHelpRequest.class);
//                i.putExtra("id", id_extra);
//                i.putExtra("school", school_extra);
//                startActivity(i);
            }
        });

        close.setText("X");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewReplyDialog.dismiss();
            }
        });

        viewReplyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        viewReplyDialog.show();
        //Toast.makeText(getApplicationContext(),"clicked", Toast.LENGTH_SHORT).show();
    }
}
