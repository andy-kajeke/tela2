package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivity;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.viewReplies.ViewHelpRequest;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.viewReplies.ViewMaterialRequest;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.viewReplies.ViewMeetingRequest;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.viewReplies.ViewTimeOffRequest;

public class MakeRequests extends AppCompatActivity {

    EditText staffName, staffId;
    Button schoolMaterials, timeOff, meetings, help, replies;
    ImageView back;

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

        back = findViewById(R.id.back);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), TeacherHomeActivity.class);
                bk.putExtra("employee_No", id_extra);
                bk.putExtra("employee_Name", name_extra);
                startActivity(bk);
            }
        });

        staffName.append(name_extra);
        staffId.append(id_extra);

        viewReplyDialog = new Dialog(this);

        //On select specific request
        schoolMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sch = new Intent(MakeRequests.this, SchoolMaterialsList.class);
                sch.putExtra("id", id_extra);
                sch.putExtra("name", name_extra);
                startActivity(sch);
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
                Intent i = new Intent(getApplicationContext(), ViewMaterialRequest.class);
                i.putExtra("id", id_extra);
                i.putExtra("name", name_extra);
                startActivity(i);
            }
        });

        viewTimeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewTimeOffRequest.class);
                i.putExtra("id", id_extra);
                i.putExtra("name", name_extra);
                startActivity(i);
            }
        });

        viewMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewMeetingRequest.class);
                i.putExtra("id", id_extra);
                i.putExtra("name", name_extra);
                startActivity(i);
            }
        });

        viewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewHelpRequest.class);
                i.putExtra("id", id_extra);
                i.putExtra("name", name_extra);
                startActivity(i);
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
