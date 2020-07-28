package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.viewReplies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.helprequest.HelpRequest;

import java.util.ArrayList;
import java.util.List;

public class ViewHelpRequest extends AppCompatActivity {

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private List<HelpRequest> mHelpRequests;
    private ViewHelpAdapter adapter;
    private RecyclerView requestList;
    String RequestType = "Time Off/ Leave";
    String employeeNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_help_request);
        setTitle("Help Reply");
        requestList = findViewById(R.id.request_list);

        Bundle bundle = getIntent().getExtras();
        employeeNo = bundle.getString("id");

        mHelpRequests = new ArrayList<>();

        adapter = new ViewHelpAdapter(this, mHelpRequests);
        requestList.setAdapter(adapter);
        requestList.setLayoutManager(new LinearLayoutManager(this));

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getRequestsByEmployeeNo(employeeNo).observe(this, new Observer<List<HelpRequest>>() {
            @Override
            public void onChanged(List<HelpRequest> helpRequests) {
                adapter.setHelpRequestsList(helpRequests);
            }
        });
    }
}
