package com.planetsystems.tela.activities.staff.smc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.school.SchoolConfirmation;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.data.smc.SyncSMC;
import com.planetsystems.tela.utils.DynamicData;
import com.planetsystems.tela.workers.WorkManagerTrigger;

import java.util.List;

import static com.planetsystems.tela.activities.mainActivity.MainActivity.SchoolDeviceIMEINumber;

public class SmcActivity extends AppCompatActivity {

    SmcActivityViewModel smcActivityViewModel;
    TextView status;
    Button btnSubmit;
    EditText staffonsite, staffTeaching_,staffNotTeaching_, staff_comment;
    Switch primo1,primo2,primo3,primo4,primo5,primo6,primo7;
    RadioButton h_present, h_absent;
    ProgressDialog dialog;
    Dialog outDialog;
    String P1;
    String P2;
    String P3;
    String P4;
    String P5;
    String P6;
    String P7;
    String smcCode_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smc);
        setTitle("SMC Observations");

        status = (TextView) findViewById(R.id.status);
        btnSubmit = (Button) findViewById(R.id.out);
        staffonsite = (EditText) findViewById(R.id.staffPresent);
        staffTeaching_ = (EditText) findViewById(R.id.teaching);
        staffNotTeaching_ = (EditText) findViewById(R.id.notTeaching);
        h_present = (RadioButton) findViewById(R.id.present);
        h_absent = (RadioButton) findViewById(R.id.absent);
        //staff_comment = (EditText) findViewById(R.id.comment);
        primo1 = (Switch) findViewById(R.id.primo_1);
        primo2 = (Switch) findViewById(R.id.primo_2);
        primo3 = (Switch) findViewById(R.id.primo_3);
        primo4 = (Switch) findViewById(R.id.primo_4);
        primo5 = (Switch) findViewById(R.id.primo_5);
        primo6 = (Switch) findViewById(R.id.primo_6);
        primo7 = (Switch) findViewById(R.id.primo_7);

        outDialog = new Dialog(this);

        Bundle bundle = getIntent().getExtras();
        smcCode_extra = bundle.getString("employee_No");

        h_present.setChecked(true);
        status.setText("Present");

        if (!isConnected()) {
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }else {
            //Synchronize the school data to phone and to the sever
            WorkManagerTrigger.startFetchWorkers(getApplicationContext());
            WorkManagerTrigger.startUploadWorkers(getApplicationContext());
        }

        smcActivityViewModel = new ViewModelProvider(this).get(SmcActivityViewModel.class);
        smcActivityViewModel.getSmcRecords().observe(this, new Observer<List<SyncSMC>>() {
            @Override
            public void onChanged(List<SyncSMC> syncSMCS) {
                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(syncSMCS.size()), Toast.LENGTH_LONG).show();
            }
        });

        primo1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                    Toast.makeText(SmcActivity.this,"Teacher Absent",Toast.LENGTH_SHORT).show();

                    P1 = "Teacher absent";

                } else {

                    Toast.makeText(SmcActivity.this,"Teacher Present",Toast.LENGTH_SHORT).show();

                    P1 = "Teacher present";
                }
            }
        });
        primo2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                    Toast.makeText(SmcActivity.this,"Teacher Absent",Toast.LENGTH_SHORT).show();

                    P2 = "Teacher absent";

                } else {

                    Toast.makeText(SmcActivity.this,"Teacher Present",Toast.LENGTH_SHORT).show();

                    P2 = "Teacher present";
                }
            }
        });
        primo3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                    Toast.makeText(SmcActivity.this,"Teacher Absent",Toast.LENGTH_SHORT).show();

                    P3 = "Teacher absent";

                } else {

                    Toast.makeText(SmcActivity.this,"Teacher Present",Toast.LENGTH_SHORT).show();

                    P3 = "Teacher present";
                }
            }
        });
        primo4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                    Toast.makeText(SmcActivity.this,"Teacher Absent",Toast.LENGTH_SHORT).show();

                    P4 = "Teacher absent";

                } else {

                    Toast.makeText(SmcActivity.this,"Teacher Present",Toast.LENGTH_SHORT).show();

                    P4 = "Teacher present";
                }
            }
        });
        primo5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                    Toast.makeText(SmcActivity.this,"Teacher Absent",Toast.LENGTH_SHORT).show();

                    P5 = "Teacher absent";

                } else {

                    Toast.makeText(SmcActivity.this,"Teacher Present",Toast.LENGTH_SHORT).show();

                    P5 = "Teacher present";
                }
            }
        });
        primo6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                    Toast.makeText(SmcActivity.this,"Teacher Absent",Toast.LENGTH_SHORT).show();

                    P6 = "Teacher absent";

                } else {

                    Toast.makeText(SmcActivity.this,"Teacher Present",Toast.LENGTH_SHORT).show();

                    P6 = "Teacher present";
                }
            }
        });
        primo7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                    Toast.makeText(SmcActivity.this,"Teacher Absent",Toast.LENGTH_SHORT).show();

                    P7 = "Teacher absent";

                } else {

                    Toast.makeText(SmcActivity.this,"Teacher Present",Toast.LENGTH_SHORT).show();

                    P7 = "Teacher present";
                }
            }
        });

        h_present.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                } else {
                    h_absent.setChecked(false);
                    status.setText("Present");
                }
            }
        });

        h_absent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!bChecked) {

                } else {

                    h_present.setChecked(false);
                    status.setText("Absent");

                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(staffTeaching_.getText().toString().equalsIgnoreCase("") &&
                        staffNotTeaching_.getText().toString().equalsIgnoreCase("")&&
                        staffonsite.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Parameter Missing",Toast.LENGTH_LONG).show();
                }else{
                    new AlertDialog.Builder(SmcActivity.this)
                            .setTitle("Confirmation")
                            .setMessage("Do you really want to submit?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    SMCs();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
            }
        });

        Toast.makeText(SmcActivity.this,"Sch ID: "+ DynamicData.getSchoolID(SchoolDeviceIMEINumber),Toast.LENGTH_SHORT).show();
    }

    private void SMCs() {
        SyncSMC syncSMC = new SyncSMC(
                "Good",
                DynamicData.getDate(),
                DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                status.getText().toString(),
                P1,
                P2,
                P3,
                P4,
                P5,
                P6,
                P7,
                smcCode_extra,
                staffNotTeaching_.getText().toString(),
                staffonsite.getText().toString(),
                staffTeaching_.getText().toString(),
                false
        );

        smcActivityViewModel.insertSmcObservations(syncSMC);
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
