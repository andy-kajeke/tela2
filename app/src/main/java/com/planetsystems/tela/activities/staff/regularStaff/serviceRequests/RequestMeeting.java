package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.utils.DynamicData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.planetsystems.tela.activities.mainActivity.MainActivity.SchoolDeviceIMEINumber;

public class RequestMeeting extends AppCompatActivity {

    EditText mdateFrom, mdateTo, mtimeFrom, mtimeTo, mstaff_comment;
    ProgressDialog dialog;
    Button mbtnFollow;
    String id_extra, name_extra;
    String school_extra;
    String datetoday;

    private ServiceRequestsViewModel serviceRequestsViewModel;
    private DatePickerDialog.OnDateSetListener m_fromDatePicker;
    private DatePickerDialog.OnDateSetListener m_toDatePicker;
    private TimePickerDialog.OnTimeSetListener m_fromTimePicker;
    private TimePickerDialog.OnTimeSetListener m_toTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_meeting);
        setTitle("Meeting Request");

        mbtnFollow =(Button) findViewById(R.id.mTimeOut);
        mdateFrom =(EditText) findViewById(R.id.mDateFrom);
        mdateTo =(EditText) findViewById(R.id.mDateTo);
        mtimeFrom =(EditText) findViewById(R.id.mTimeFrom);
        mtimeTo =(EditText) findViewById(R.id.mTimeTo);
        mstaff_comment =(EditText) findViewById(R.id.mcomment);

        Bundle bundle = getIntent().getExtras();
        id_extra = bundle.getString("id");
        name_extra = bundle.getString("name");

        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        datetoday = dateFormat.format(date);

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);
        serviceRequestsViewModel.getAllMeetings("Meeting", "Pending").observe(this, new Observer<List<SyncEmployeeTimeOffRequestDM>>() {
            @Override
            public void onChanged(List<SyncEmployeeTimeOffRequestDM> syncEmployeeTimeOffRequestDMS) {
                Toast.makeText(getApplicationContext(),"size : "+String.valueOf(syncEmployeeTimeOffRequestDMS.size()) ,Toast.LENGTH_SHORT).show();
            }
        });

        ///////////////////////////////////////Date meeting starts//////////////////////////////////////////////
        mdateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RequestMeeting.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,m_fromDatePicker,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setIcon(R.drawable.ic_date_range_black_24dp);
                dialog.setTitle("Select Start Date.");
                dialog.show();
            }
        });

        m_fromDatePicker = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year){
                month = month + 1;
                String date = month + "/" + year + "/" + day;
                mdateFrom.setText(date);
            }
        };

        ///////////////////////////////////////Date meeting ends//////////////////////////////////////////////////
        mdateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RequestMeeting.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,m_toDatePicker,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setIcon(R.drawable.ic_date_range_black_24dp);
                dialog.setTitle("Select End Date.");
                dialog.show();
            }
        });

        m_toDatePicker = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker,int day, int month, int year){
                month = month + 1;
                String date = month + "/" + year + "/" + day;
                mdateTo.setText(date);
            }
        };

        //////////////////////////////////////////////Time to start///////////////////////////////////////////////////
        mtimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);

                // Whether show time in 24 hour format or not.
                boolean is24Hour = false;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RequestMeeting.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,m_fromTimePicker,
                        hour, minute, is24Hour);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.setIcon(R.drawable.ic_access_time_black_24dp);
                timePickerDialog.setTitle("Select Start Time.");
                timePickerDialog.show();
            }
        });

        m_fromTimePicker = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                minute = minute + 1;
                String startTime = hour + " : " + minute;
                mtimeFrom.setText(startTime);
            }
        };
        //////////////////////////////////////////////Time to end///////////////////////////////////////////////////
        mtimeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                int hour = now.get(Calendar.HOUR_OF_DAY);
                int minute = now.get(Calendar.MINUTE);

                // Whether show time in 24 hour format or not.
                boolean is24Hour = false;

                TimePickerDialog timePickerDialog = new TimePickerDialog(RequestMeeting.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,m_toTimePicker,
                        hour, minute, is24Hour);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.setIcon(R.drawable.ic_access_time_black_24dp);
                timePickerDialog.setTitle(" Select End Time.");
                timePickerDialog.show();
            }
        });

        m_toTimePicker = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                minute = minute + 1;
                String startTime = hour + " : " + minute;
                mtimeTo.setText(startTime);
            }
        };


        mbtnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(RequestMeeting.this)
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
        String leaveType = "Meeting";
        String dataFrom_ = mdateFrom.getText().toString();
        String dateTo_ = mdateTo.getText().toString();
        String timeFrom_ = mtimeFrom.getText().toString();
        String timeTo_ = mtimeTo.getText().toString();
        String comment = mstaff_comment.getText().toString();
        String employeeNo = id_extra;
        String employeeName = name_extra;
        String RequestType = "Meeting";

        SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM = new SyncEmployeeTimeOffRequestDM(
                "",
                "",
                1,
                comment,
                "Pending",
                "",
                DynamicData.getSchoolID(SchoolDeviceIMEINumber),
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
    }
}
