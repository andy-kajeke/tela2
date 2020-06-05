package com.planetsystems.tela.activities.staff.administration.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.planetsystems.tela.R;

import java.text.SimpleDateFormat;

public class ApproveHelpRequests extends AppCompatActivity {

    EditText helpType, requestedOn;
    EditText requestedBy, reason, priority;
    Button approve, cancel;

    String dayString;
    int db_id_extra;
    String help_extra, priority_extra;
    String name_extra, school_extra, start_extra, end_extra;
    String requestedOn_extra, reason_extra;

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

        helpType.setText(help_extra);
        priority.setText(priority_extra);
        requestedOn.setText(requestedOn_extra);
        requestedBy.setText(name_extra);
        reason.setText(reason_extra);

        long date = System.currentTimeMillis();
        SimpleDateFormat day = new SimpleDateFormat("dd /MM/ yyy");
        dayString = day.format(date);

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ApproveHelpRequests.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to approve this request?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //ApproveRequest();
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
                                // RejectRequest();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
    }
}
