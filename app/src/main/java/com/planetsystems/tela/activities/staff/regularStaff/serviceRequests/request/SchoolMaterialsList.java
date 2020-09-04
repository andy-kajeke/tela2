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
import android.view.View;
import android.widget.ImageView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.LeaveAdapter;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.PendingLeaveRequest;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.RequestsMade;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivity;
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
    ImageView back;
    int count = 0;
    String emp_id, emp_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_materials_list);

        back = findViewById(R.id.back);
        materialList = findViewById(R.id.item_list);

        Bundle bundle = getIntent().getExtras();
        emp_id = bundle.getString("id");
        emp_name = bundle.getString("name");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), MakeRequests.class);
                bk.putExtra("id", emp_id);
                bk.putExtra("name", emp_name);
                startActivity(bk);
            }
        });

        mSchoolMaterials = new ArrayList<>();

        adapter = new SchoolMaterialsListAdapter(this, mSchoolMaterials, emp_id, emp_name);
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
                                    intent.putExtra("id", emp_id);
                                    intent.putExtra("name", emp_id);
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    SchoolMaterialsList.this.finish();
                                }}).show();
                }
            }
        });
    }
}
