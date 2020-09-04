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
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request.SchoolMaterialsListAdapter;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequests;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;

import java.util.ArrayList;
import java.util.List;

public class PendingMaterialRequest extends AppCompatActivity {

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private List<SchoolMaterialRequests> mSchoolMaterials;
    private MaterialAdapter adapter;
    private RecyclerView materialList;
    int count = 0;
    String supervisorID;
    final String Pending = "Pending";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_material_request);
        setTitle("Pending Material Request");

        materialList = findViewById(R.id.request_list);

        Bundle bundle = getIntent().getExtras();
        supervisorID = bundle.getString("supervisor");

        mSchoolMaterials = new ArrayList<>();

        adapter = new MaterialAdapter(this, mSchoolMaterials, supervisorID);
        materialList.setAdapter(adapter);
        materialList.setLayoutManager(new LinearLayoutManager(this));

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getMaterialRequestByApprovalStatus(Pending).observe(this, new Observer<List<SchoolMaterialRequests>>() {
            @Override
            public void onChanged(List<SchoolMaterialRequests> schoolMaterialRequests) {
                if(schoolMaterialRequests.size() != count ){
                    adapter.setSchoolMaterialsList(schoolMaterialRequests);
                }else {
                    new AlertDialog.Builder(PendingMaterialRequest.this)
                            .setTitle("School Material Request")
                            .setMessage("No pending request to show")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(PendingMaterialRequest.this, RequestsMade.class);
                                    intent.putExtra("supervisor", supervisorID);
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    PendingMaterialRequest.this.finish();
                                }}).show();
                }
            }
        });

    }
}
