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

public class ApproveLeaveRequest extends AppCompatActivity {

    EditText leaveType, requestedOn;
    EditText requestedBy, reason, startDate, endDate;
    Button approve, cancel;

    String dayString;
    int db_id_extra;
    String leave_extra, itemID_extra;
    String name_extra, school_extra, start_extra, end_extra;
    String requestedOn_extra, reason_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_leave_request);
        setTitle("Time Off Request");

        leaveType = (EditText) findViewById(R.id.leave);
        startDate = (EditText) findViewById(R.id.startDate);
        endDate = (EditText) findViewById(R.id.endDate);
        requestedOn = (EditText) findViewById(R.id.requestedOn);
        requestedBy = (EditText) findViewById(R.id.staff_name);
        reason = (EditText) findViewById(R.id.reason);
        approve = (Button) findViewById(R.id.approveRequest);
        cancel = (Button) findViewById(R.id.cancelRequest);

        Bundle bundle = getIntent().getExtras();
        db_id_extra = bundle.getInt("db_id");
        name_extra = bundle.getString("employee_Name");
        leave_extra = bundle.getString("leave");
        start_extra = bundle.getString("startDate");
        end_extra = bundle.getString("endDate");
        requestedOn_extra = bundle.getString("requestDate");
        reason_extra = bundle.getString("reason");
        //school_extra = bundle.getString("school");

        leaveType.setText(leave_extra);
        startDate.setText(start_extra);
        endDate.setText(end_extra);
        requestedOn.setText(requestedOn_extra);
        requestedBy.setText(name_extra);
        reason.setText(reason_extra);

        long date = System.currentTimeMillis();
        SimpleDateFormat day = new SimpleDateFormat("dd /MM/ yyy");
        dayString = day.format(date);

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ApproveLeaveRequest.this)
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

                new AlertDialog.Builder(ApproveLeaveRequest.this)
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

