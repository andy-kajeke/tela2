package com.planetsystems.tela.activities.staff.administration.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.helprequest.HelpRequest;

import java.util.List;

public class RequestsMade extends AppCompatActivity {

    TextView smApproved, smPending;
    TextView leaveApproved, leavePending;
    TextView meetingApproved, meetingPending;
    TextView helpApproved, helpPending;

    CardView schoolMaterials;
    CardView timeOff;
    CardView meetings;
    CardView help;
    final String Pending = "Pending";
    final String Approved = "Approved";
    final String TimeOff = "Time Off";
    final String Meeting = "Meeting";
    String supervisor;

    private ServiceRequestsViewModel serviceRequestsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_made);
        setTitle("Service Requests");

        smApproved = (TextView) findViewById(R.id.sm_approved);
        smPending = (TextView) findViewById(R.id.sm_pending);
        leaveApproved = (TextView) findViewById(R.id.leave_approved);
        leavePending = (TextView) findViewById(R.id.leave_pending);
        meetingApproved = (TextView) findViewById(R.id.meeting_approved);
        meetingPending = (TextView) findViewById(R.id.meeting_pending);
        helpApproved = (TextView) findViewById(R.id.help_approved);
        helpPending = (TextView) findViewById(R.id.help_pending);
        schoolMaterials =(CardView) findViewById(R.id.cardSM);
        timeOff = (CardView) findViewById(R.id.cardTimeOff);
        meetings = (CardView) findViewById(R.id.cardMeetings);
        help = (CardView) findViewById(R.id.cardHelp);

        Bundle bundle = getIntent().getExtras();
        supervisor = bundle.getString("employee_No");

        //Time off/leave Requests
        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getAllTimeOffs(TimeOff, Pending).observe(this, new Observer<List<SyncEmployeeTimeOffRequestDM>>() {
            @Override
            public void onChanged(List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS) {
                //Toast.makeText(getApplicationContext(),"size : "+String.valueOf(syncEmployeeTimeOffRequestDMS.size()) ,Toast.LENGTH_SHORT).show();
                leavePending.setText(String.valueOf(syncEmployeeTimeOffRequestDMS.size()));
            }
        });

        serviceRequestsViewModel.getAllTimeOffs(TimeOff,Approved).observe(this, new Observer<List<SyncEmployeeTimeOffRequestDM>>() {
            @Override
            public void onChanged(List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS) {

                leaveApproved.setText(String.valueOf(syncEmployeeTimeOffRequestDMS.size()));
            }
        });

        //Meeting Requests
        serviceRequestsViewModel.getAllMeetings(Meeting, Pending).observe(this, new Observer<List<SyncEmployeeTimeOffRequestDM>>() {
            @Override
            public void onChanged(List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS) {

                meetingPending.setText(String.valueOf(syncEmployeeTimeOffRequestDMS.size()));
            }
        });

        serviceRequestsViewModel.getAllMeetings(Meeting,Approved).observe(this, new Observer<List<SyncEmployeeTimeOffRequestDM>>() {
            @Override
            public void onChanged(List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS) {

                meetingApproved.setText(String.valueOf(syncEmployeeTimeOffRequestDMS.size()));
            }
        });

        //Help request
        serviceRequestsViewModel.getAllHelpRequests(Pending).observe(this, new Observer<List<HelpRequest>>() {
            @Override
            public void onChanged(List<HelpRequest> helpRequests) {
                helpPending.setText(String.valueOf(helpRequests.size()));
            }
        });

        serviceRequestsViewModel.getAllHelpRequests(Approved).observe(this, new Observer<List<HelpRequest>>() {
            @Override
            public void onChanged(List<HelpRequest> helpRequests) {
                helpApproved.setText(String.valueOf(helpRequests.size()));
            }
        });

        schoolMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent i = new Intent(RequestMade.this, PendingMaterials.class);
//                i.putExtra("school", school_id_extra);
//                startActivity(i);

            }
        });

        timeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RequestsMade.this, PendingLeaveRequest.class);
                i.putExtra("request", TimeOff);
                i.putExtra("supervisor", supervisor);
                startActivity(i);

            }
        });

        meetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RequestsMade.this, PendingLeaveRequest.class);
                i.putExtra("request", Meeting);
                i.putExtra("supervisor", supervisor);
                startActivity(i);

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RequestsMade.this, PendingHelpRequest.class);
                i.putExtra("supervisor", supervisor);
                startActivity(i);
            }
        });

    }
}
