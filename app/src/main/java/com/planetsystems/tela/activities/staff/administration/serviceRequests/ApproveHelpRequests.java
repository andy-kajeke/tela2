package com.planetsystems.tela.activities.staff.administration.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request.MakeRequests;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;

import java.text.SimpleDateFormat;

public class ApproveHelpRequests extends AppCompatActivity {

    EditText helpType, requestedOn;
    EditText requestedBy, reason, priority;
    Button approve, cancel;

    String dayString;
    int db_id_extra;
    String help_extra, priority_extra;
    String name_extra, supervisorID, start_extra, end_extra;
    String requestedOn_extra, reason_extra;
    private ServiceRequestsViewModel serviceRequestsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_help_requests);
        setTitle("Help Request");

        helpType = (EditText) findViewById(R.id.leave);
        priority = (EditText) findViewById(R.id.priorityRate);
        requestedOn = (EditText) findViewById(R.id.requestedOn);
        requestedBy = (EditText) findViewById(R.id.staff_name);
        reason = (EditText) findViewById(R.id.reason);
        approve = (Button) findViewById(R.id.approveRequest);
        cancel = (Button) findViewById(R.id.cancelRequest);

        Bundle bundle = getIntent().getExtras();
        db_id_extra = bundle.getInt("db_id");
        name_extra = bundle.getString("employee_Name");
        help_extra = bundle.getString("help");
        priority_extra = bundle.getString("priority");
        requestedOn_extra = bundle.getString("requestDate");
        reason_extra = bundle.getString("reason");
        supervisorID = bundle.getString("supervisor");

        helpType.setText(help_extra);
        priority.setText(priority_extra);
        requestedOn.setText(requestedOn_extra);
        requestedBy.setText(name_extra);
        reason.setText(reason_extra);

        long date = System.currentTimeMillis();
        SimpleDateFormat day = new SimpleDateFormat("dd /MM/ yyy");
        dayString = day.format(date);

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ApproveHelpRequests.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to approve this request?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                ApproveRequest();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ApproveHelpRequests.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to reject this request?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                 RejectRequest();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
    }

    private void ApproveRequest() {
        String approvalStatus = "Approved";
        int row_id = db_id_extra;

        serviceRequestsViewModel.updateHelpApprovalStatus(approvalStatus, dayString, supervisorID, row_id);

        requestedOn.setText("");
        requestedBy.setText("");
        reason.setText("");

        Toast.makeText(getApplicationContext(),"Submitted Successfully " ,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ApproveHelpRequests.this, MakeRequests.class);
        intent.putExtra("supervisor", supervisorID);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ApproveHelpRequests.this.finish();
    }

    private void RejectRequest() {
        String approvalStatus = "Rejected";
        int row_id = db_id_extra;

        serviceRequestsViewModel.updateHelpApprovalStatus(approvalStatus, dayString, supervisorID, row_id);

        requestedOn.setText("");
        requestedBy.setText("");
        reason.setText("");

        Toast.makeText(getApplicationContext(),"Submitted Successfully" ,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ApproveHelpRequests.this, PendingHelpRequest.class);
        intent.putExtra("supervisor", supervisorID);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ApproveHelpRequests.this.finish();
    }
}
