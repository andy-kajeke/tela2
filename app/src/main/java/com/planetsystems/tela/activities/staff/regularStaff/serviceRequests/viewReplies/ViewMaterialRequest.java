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
import com.planetsystems.tela.activities.staff.administration.serviceRequests.PendingMaterialRequest;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.RequestsMade;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request.MakeRequests;
import com.planetsystems.tela.data.helprequest.HelpRequest;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequests;

import java.util.ArrayList;
import java.util.List;

public class ViewMaterialRequest extends AppCompatActivity {

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private List<SchoolMaterialRequests> mSchoolMaterialRequests;
    private ViewMaterialAdapter adapter;
    private RecyclerView requestList;
    ImageView back;
    String employeeNo, employeeName;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_material_request);

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

        mSchoolMaterialRequests = new ArrayList<>();

        adapter = new ViewMaterialAdapter(this, mSchoolMaterialRequests);
        requestList.setAdapter(adapter);
        requestList.setLayoutManager(new LinearLayoutManager(this));

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        serviceRequestsViewModel.getMaterialRequestsByEmployeeNo(employeeNo).observe(this, new Observer<List<SchoolMaterialRequests>>() {
            @Override
            public void onChanged(List<SchoolMaterialRequests> schoolMaterialRequests) {
                if(schoolMaterialRequests.size() != count ){
                    adapter.setSchoolMaterialsList(schoolMaterialRequests);
                }else {
                    new AlertDialog.Builder(ViewMaterialRequest.this)
                            .setTitle("School Material Request")
                            .setMessage("No request to show")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(ViewMaterialRequest.this, MakeRequests.class);
                                    intent.putExtra("id", employeeNo);
                                    intent.putExtra("name", employeeName);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    ViewMaterialRequest.this.finish();
                                }}).show();
                }
            }
        });
    }
}
