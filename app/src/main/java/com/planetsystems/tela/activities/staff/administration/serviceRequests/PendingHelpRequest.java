package com.planetsystems.tela.activities.staff.administration.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.data.helprequest.HelpRequest;

import java.util.ArrayList;
import java.util.List;

public class PendingHelpRequest extends AppCompatActivity {

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private List<HelpRequest> mHelpRequests;
    private HelpAdapter adapter;
    RecyclerView requestList;
    String supervisor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_help_request);
        setTitle("Pending Help Requests");

        requestList = findViewById(R.id.request_list);

        Bundle bundle = getIntent().getExtras();
        supervisor = bundle.getString("supervisor");

        mHelpRequests = new ArrayList<>();

        adapter = new HelpAdapter(this, mHelpRequests, supervisor);
        requestList.setAdapter(adapter);
        requestList.setLayoutManager(new LinearLayoutManager(this));

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getAllHelpRequests("Pending").observe(this, new Observer<List<HelpRequest>>() {
            @Override
            public void onChanged(List<HelpRequest> helpRequests) {
                adapter.setHelpRequestsList(helpRequests);
            }
        });
    }
}
