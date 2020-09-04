package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.viewReplies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request.MakeRequests;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.helprequest.HelpRequest;

import java.util.ArrayList;
import java.util.List;

public class ViewHelpRequest extends AppCompatActivity {

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private List<HelpRequest> mHelpRequests;
    private ViewHelpAdapter adapter;
    private RecyclerView requestList;
    ImageView back;
    String employeeNo, employeeName;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_help_request);

        back = findViewById(R.id.back);
        requestList = findViewById(R.id.request_list);

        Bundle bundle = getIntent().getExtras();
        employeeNo = bundle.getString("id");
        employeeName = bundle.getString("name");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), MakeRequests.class);
                bk.putExtra("id", employeeNo);
                bk.putExtra("name", employeeName);
                startActivity(bk);
            }
        });

        mHelpRequests = new ArrayList<>();

        adapter = new ViewHelpAdapter(this, mHelpRequests);
        requestList.setAdapter(adapter);
        requestList.setLayoutManager(new LinearLayoutManager(this));

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getRequestsByEmployeeNo(employeeNo).observe(this, new Observer<List<HelpRequest>>() {
            @Override
            public void onChanged(List<HelpRequest> helpRequests) {
                if(helpRequests.size() != count ){
                    adapter.setHelpRequestsList(helpRequests);;
                }else {
                    new AlertDialog.Builder(ViewHelpRequest.this)
                            .setTitle("Help Request")
                            .setMessage("No request to show")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(ViewHelpRequest.this, MakeRequests.class);
                                    intent.putExtra("id", employeeNo);
                                    intent.putExtra("name", employeeName);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    ViewHelpRequest.this.finish();
                                }}).show();
                }
            }
        });
    }
}
