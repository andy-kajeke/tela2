package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.utils.DynamicData;
import com.planetsystems.tela.utils.GenerateRandomString;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.School_ID;

public class RequestTimeOff extends AppCompatActivity {

    ProgressDialog dialog;
    EditText dateFrom, dateTo, timeFrom, timeTo, tstaff_comment;
    Spinner leave;
    TextView closeTimeOff;
    Button tbtnFollow;
    String id_extra, name_extra;
    String school_extra;
    String datetoday;

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private DatePickerDialog.OnDateSetListener t_fromDatePicker;
    private DatePickerDialog.OnDateSetListener t_toDatePicker;
    private TimePickerDialog.OnTimeSetListener t_fromTimePicker;
    private TimePickerDialog.OnTimeSetListener t_toTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_time_off);
        setTitle("Time off / Leave");

        tbtnFollow =(Button) findViewById(R.id.tTimeOut);
        leave = (Spinner) findViewById(R.id.leaveType);
        dateFrom =(EditText) findViewById(R.id.tDateFrom);
        dateTo =(EditText) findViewById(R.id.tDateTo);
        timeFrom =(EditText) findViewById(R.id.tTimeFrom);
        timeTo =(EditText) findViewById(R.id.tTimeTo);
        tstaff_comment =(EditText) findViewById(R.id.tcomment);

        Bundle bundle = getIntent().getExtras();
        id_extra = bundle.getString("id");
        name_extra = bundle.getString("name");

        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(this, R.array.leave_type,
                android.R.layout.simple_spinner_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leave.setAdapter(Adapter);

        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        datetoday = dateFormat.format(date);

        //Toast.makeText(getApplicationContext(),id_extra ,Toast.LENGTH_SHORT).show();

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);
        serviceRequestsViewModel.getAllTimeOffs().observe(this, new Observer<List<SyncEmployeeTimeOffRequestDM>>() {
            @Override
            public void onChanged(List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS) {
                //Toast.makeText(getApplicationContext(),"size : "+String.valueOf(syncEmployeeTimeOffRequestDMS.size()) ,Toast.LENGTH_SHORT).show();
            }
        });

        ///////////////////////////////////////Date time off starts//////////////////////////////////////////////
        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RequestTimeOff.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,t_fromDatePicker,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setIcon(R.drawable.ic_date_range_black_24dp);
                dialog.setTitle("Select Start Date.");
                dialog.show();
            }
        });

        t_fromDatePicker = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year){
                month = month + 1;
                //String date = year + "/" + day + "/" + month;
                int _day = day;
                int _month = month;
                int _year = year;
                dateFrom.setText( _month + "/" + _year + "/" + _day);
            }
        };

        ///////////////////////////////////////Date for time off ends//////////////////////////////////////////////////
        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RequestTimeOff.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,t_toDatePicker,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setIcon(R.drawable.ic_date_range_black_24dp);
                dialog.setTitle("Select End Date.");
                dialog.show();
            }
        });

        t_toDatePicker = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker,int day, int month, int year){
                month = month + 1;
                String date = month + "/" + year + "/" + day;
                dateTo.setText(date);
            }
        };
        //////////////////////////////////////////////Time to start///////////////////////////////////////////////////
        timeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);

                // Whether show time in 24 hour format or not.
                boolean is24Hour = false;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RequestTimeOff.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,t_fromTimePicker,
                        hour, minute, is24Hour);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.setIcon(R.drawable.ic_access_time_black_24dp);
                timePickerDialog.setTitle("Select Start Time.");
                timePickerDialog.show();
            }
        });

        t_fromTimePicker = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                minute = minute + 1;
                String startTime = hour + " : " + minute;
                timeFrom.setText(startTime);
            }
        };
        //////////////////////////////////////////////Time to end///////////////////////////////////////////////////
        timeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);

                // Whether show time in 24 hour format or not.
                boolean is24Hour = false;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RequestTimeOff.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,t_toTimePicker,
                        hour, minute, is24Hour);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.setIcon(R.drawable.ic_access_time_black_24dp);
                timePickerDialog.setTitle("Select End Time.");
                timePickerDialog.show();
            }
        });

        t_toTimePicker = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                minute = minute + 1;
                String startTime = hour + " : " + minute;
                timeTo.setText(startTime);
            }
        };

        tbtnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(RequestTimeOff.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to submit?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                PostToSyncEmployeeTimeOffRequestDMs();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }

        });

    }

    private void PostToSyncEmployeeTimeOffRequestDMs() {
        String leaveType = leave.getSelectedItem().toString();
        String dataFrom_ = dateFrom.getText().toString();
        String dateTo_ = dateTo.getText().toString();
        String timeFrom_ = timeFrom.getText().toString();
        String timeTo_ = timeTo.getText().toString();
        String comment = tstaff_comment.getText().toString();
        String employeeNo = id_extra;
        String employeeName = name_extra;
        String RequestType = "Time Off";

        SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM = new SyncEmployeeTimeOffRequestDM(
                GenerateRandomString.randomString(30),
                1,
                comment,
                "Pending",
                "",
                School_ID,
                employeeName,
                employeeNo,
                RequestType,
                dataFrom_,
                timeFrom_,
                "",
                DynamicData.getDate(),
                "",
                "",
                dateTo_,
                timeTo_,
                leaveType,
                false,
                false
        );

        serviceRequestsViewModel.addNewTimeOffRequest(syncEmployeeTimeOffRequestDM);
        Toast.makeText(getApplicationContext(),"Submitted" ,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RequestTimeOff.this, MakeRequests.class);
        intent.putExtra("id", employeeNo);
        intent.putExtra("name", employeeName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        RequestTimeOff.this.finish();
    }
}
