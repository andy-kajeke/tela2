package com.planetsystems.tela.activities.staff.administration.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    String requestType;
    final String Pending = "Pending";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);

        requestList = findViewById(R.id.request_list);

        Bundle bundle = getIntent().getExtras();
       requestType = bundle.getString("request");

        setTitle("Pending"+ " "+ requestType);

        mSyncEmployeeTimeOffRequestDMS = new ArrayList<>();

        adapter = new LeaveAdapter(this, mSyncEmployeeTimeOffRequestDMS);
        requestList.setAdapter(adapter);
        requestList.setLayoutManager(new LinearLayoutManager(this));

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getAllTimeOffs(requestType, Pending).observe(this, new Observer<List<SyncEmployeeTimeOffRequestDM>>() {
            @Override
            public void onChanged(List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS) {
                //Toast.makeText(getApplicationContext(),"size : "+String.valueOf(syncEmployeeTimeOffRequestDMS.size()) ,Toast.LENGTH_SHORT).show();
                adapter.setTaskList(syncEmployeeTimeOffRequestDMS);
            }
        });
    }
}
