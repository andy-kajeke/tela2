package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.data.helprequest.HelpRequest;
import com.planetsystems.tela.utils.DynamicData;
import com.planetsystems.tela.utils.GenerateRandomString;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;

public class RequestHelp extends AppCompatActivity {

    Spinner category;
    EditText hcomment;
    RadioButton high, normal;
    TextView priorityRate;
    Button hbtnFollow;
    ImageView back;
    String id_extra, name_extra;
    String datetoday;

    private ServiceRequestsViewModel serviceRequestsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_help);

        back = findViewById(R.id.back);
        priorityRate = (TextView) findViewById(R.id.priorityRate);
        high = (RadioButton) findViewById(R.id.High);
        normal = (RadioButton) findViewById(R.id.Normal);
        hbtnFollow =(Button) findViewById(R.id.help);
        category = (Spinner) findViewById(R.id.helpCategory);
        hcomment =(EditText) findViewById(R.id.helpcomment);

        Bundle bundle = getIntent().getExtras();
        id_extra = bundle.getString("id");
        name_extra = bundle.getString("name");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(getApplicationContext(), MakeRequests.class);
                bk.putExtra("id", id_extra);
                bk.putExtra("name", name_extra);
                startActivity(bk);
            }
        });

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);
        serviceRequestsViewModel.getAllHelpRequests("Pending").observe(this, new Observer<List<HelpRequest>>() {
            @Override
            public void onChanged(List<HelpRequest> helpRequests) {
                //Toast.makeText(getApplicationContext(),"size : "+String.valueOf(helpRequests.size()) ,Toast.LENGTH_SHORT).show();
            }
        });

        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        datetoday = dateFormat.format(date);

        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(this, R.array.help_category,
                android.R.layout.simple_spinner_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(Adapter);

        //On select specific request
        high.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    normal.setChecked(false);

                    priorityRate.setText("High priority");

                }

            }
        });
        normal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    high.setChecked(false);

                    priorityRate.setText("Normal priority");
                }

            }
        });


        hbtnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(RequestHelp.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to submit?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                HelpRequest();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });

    }

    private void HelpRequest() {
        String priority = priorityRate.getText().toString();
        String helpType = category.getSelectedItem().toString();
        String comment = hcomment.getText().toString();
        String employeeNo = id_extra;
        String employeeName = name_extra;

        HelpRequest helpRequest = new HelpRequest(
                GenerateRandomString.randomString(30),
                "Pending",
                "",
                comment,
                DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                helpType,
                priority,
                employeeNo,
                employeeName,
                DynamicData.getDate(),
                "",
                false,
                false
        );

        serviceRequestsViewModel.addHelpRequest(helpRequest);
        Toast.makeText(getApplicationContext(),"Submitted",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RequestHelp.this, MakeRequests.class);
        intent.putExtra("id", employeeNo);
        intent.putExtra("name", employeeName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        RequestHelp.this.finish();
    }
}
