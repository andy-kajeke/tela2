package com.planetsystems.tela.activities.staff.administration.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;

import java.util.ArrayList;
import java.util.List;

public class PendingLeaveRequest extends AppCompatActivity {

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private List<SyncEmployeeTimeOffRequestDM> mSyncEmployeeTimeOffRequestDMS;
    private LeaveAdapter adapter;
    private RecyclerView requestList;
    String requestType, supervisor;
    final String Pending = "Pending";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        requestList = findViewById(R.id.request_list);

        Bundle bundle = getIntent().getExtras();
        requestType = bundle.getString("request");
        supervisor = bundle.getString("supervisor");

        setTitle("Pending"+ " "+ requestType);

        mSyncEmployeeTimeOffRequestDMS = new ArrayList<>();

        adapter = new LeaveAdapter(this, mSyncEmployeeTimeOffRequestDMS, supervisor);
        requestList.setAdapter(adapter);
        requestList.setLayoutManager(new LinearLayoutManager(this));

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getAllTimeOffs(requestType, Pending).observe(this, new Observer<List<SyncEmployeeTimeOffRequestDM>>() {
            @Override
            public void onChanged(List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS) {
                //Toast.makeText(getApplicationContext(),"size : "+String.valueOf(syncEmployeeTimeOffRequestDMS.size()) ,Toast.LENGTH_SHORT).show();
                if(syncEmployeeTimeOffRequestDMS.size() != count ){
                    adapter.setTaskList(syncEmployeeTimeOffRequestDMS);
                }else {
                    new AlertDialog.Builder(PendingLeaveRequest.this)
                            .setTitle(requestType)
                            .setMessage("No pending request yet")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(PendingLeaveRequest.this, RequestsMade.class);
                                    intent.putExtra("request", requestType);
                                    intent.putExtra("supervisor", supervisor);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    PendingLeaveRequest.this.finish();
                                }}).show();
                }
            }
        });
    }
}
