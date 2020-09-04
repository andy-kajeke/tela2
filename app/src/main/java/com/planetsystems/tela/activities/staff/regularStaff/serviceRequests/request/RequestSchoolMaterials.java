package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequests;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;
import com.planetsystems.tela.utils.GenerateRandomString;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.School_ID;

public class RequestSchoolMaterials extends AppCompatActivity {

    EditText smdate, qnty, sm_staff_comment;
    EditText itemName;
    ImageView back;
    Button smbtnFollow;
    String emp_id_extra, itemName_extra, itemID_extra;
    String name_extra, school_extra;
    String datetoday;
    private DatePickerDialog.OnDateSetListener sm_DatePicker;
    private ServiceRequestsViewModel serviceRequestsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_school_materials);

        back = findViewById(R.id.back);
        smbtnFollow = findViewById(R.id.sm);
        itemName = findViewById(R.id.smItemName);
        smdate = findViewById(R.id.showDate);
        qnty = findViewById(R.id.Qnty);
        sm_staff_comment = findViewById(R.id.smcomment);

        Bundle bundle = getIntent().getExtras();
        emp_id_extra = bundle.getString("emp_id");
        name_extra = bundle.getString("emp_name");
        itemID_extra = bundle.getString("item_id");
        itemName_extra = bundle.getString("item_name");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), MakeRequests.class);
                bk.putExtra("id", emp_id_extra);
                bk.putExtra("name", name_extra);
                startActivity(bk);
            }
        });

        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        datetoday = dateFormat.format(date);

        itemName.setText(itemName_extra);

        //Toast.makeText(getApplicationContext(),itemName_extra + " // "+ itemID_extra,Toast.LENGTH_SHORT).show();

        ///////////////////////////////////////Date when needed//////////////////////////////////////////////
        smdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RequestSchoolMaterials.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,sm_DatePicker,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        sm_DatePicker = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year){
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                smdate.setText(date);
            }
        };

        smbtnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qnty.getText().toString().equalsIgnoreCase("")){
                    qnty.setError("Quantity missing");
                }else{
                    new AlertDialog.Builder(RequestSchoolMaterials.this)
                            .setTitle("Confirmation")
                            .setMessage("Do you really want to submit?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                   PostToSyncEmployeeMaterialRequests();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
            }
        });

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);
        serviceRequestsViewModel.getMaterialRequests().observe(this, new Observer<List<SchoolMaterialRequests>>() {
            @Override
            public void onChanged(List<SchoolMaterialRequests> schoolMaterialRequests) {
                //Toast.makeText(getApplicationContext(),"size : "+String.valueOf(schoolMaterialRequests.size()) ,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void PostToSyncEmployeeMaterialRequests() {
        String employeeNo = emp_id_extra;
        String employeeName = name_extra;
        String itemName = itemName_extra;
        String itemId = itemID_extra;
        String requestedDate = datetoday;
        String quantity = qnty.getText().toString();
        String dateRequest = smdate.getText().toString();
        String comment = sm_staff_comment.getText().toString();
        String requestId = GenerateRandomString.randomString(30);
        String employeeRequestType = "School Materials";
        String approvalStatus = "Pending";
        String approvalDate = "";
        String supervisorId = "";
        String schoolId = School_ID;

        SchoolMaterialRequests schoolMaterialRequests = new SchoolMaterialRequests(
                employeeNo,
                employeeName,
                employeeRequestType,
                comment,
                requestedDate,
                schoolId,
                "",
                itemName,
                itemId,
                quantity,
                dateRequest,
                approvalStatus,
                approvalDate,
                requestId,
                supervisorId,
                false,
                false
        );

        serviceRequestsViewModel.addNewMaterialRequest(schoolMaterialRequests);
        Toast.makeText(getApplicationContext(),"Submitted",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RequestSchoolMaterials.this, MakeRequests.class);
        intent.putExtra("id", employeeNo);
        intent.putExtra("name", employeeName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        RequestSchoolMaterials.this.finish();
    }
}
