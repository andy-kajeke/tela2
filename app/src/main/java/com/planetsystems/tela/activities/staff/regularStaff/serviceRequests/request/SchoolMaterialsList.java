package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request;

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
import com.planetsystems.tela.activities.staff.administration.serviceRequests.LeaveAdapter;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.PendingLeaveRequest;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.RequestsMade;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;

import java.util.ArrayList;
import java.util.List;

public class SchoolMaterialsList extends AppCompatActivity {

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private List<SchoolMaterials> mSchoolMaterials;
    private SchoolMaterialsListAdapter adapter;
    private RecyclerView materialList;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_materials_list);
        setTitle("School Material List");

        materialList = findViewById(R.id.item_list);

        mSchoolMaterials = new ArrayList<>();

        adapter = new SchoolMaterialsListAdapter(this, mSchoolMaterials);
        materialList.setAdapter(adapter);
        materialList.setLayoutManager(new LinearLayoutManager(this));

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getAllSchoolMaterials().observe(this, new Observer<List<SchoolMaterials>>() {
            @Override
            public void onChanged(List<SchoolMaterials> schoolMaterials) {
                if(schoolMaterials.size() != count ){
                    adapter.setSchoolMaterialsList(schoolMaterials);
                }else {
                    new AlertDialog.Builder(SchoolMaterialsList.this)
                            .setTitle("School Material Items")
                            .setMessage("No item to request yet")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(SchoolMaterialsList.this, MakeRequests.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    SchoolMaterialsList.this.finish();
                                }}).show();
                }
            }
        });
    }
}
