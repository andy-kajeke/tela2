package com.planetsystems.tela.activities;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.staff.regularStaff.TeacherHome;

public class ClockIn_with_StaffId extends Activity {

    ProgressDialog dialog;
    CardView btnFollow;
    EditText staffid;
    RadioButton staff,admin,Smc;

    LocationManager locationManager;
    double longitude;
    double latitude;

    int Employee_ID;
    String Name;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin_with__staff_id);

        btnFollow = findViewById(R.id.google);
        staffid = findViewById(R.id.staffID);

        //Button action
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(staffid.getText().toString().equalsIgnoreCase("")){
                    staffid.setError("Id Missing!");
                }else{

                    String employeeNumber = staffid.getText().toString();

                    //String API = Constants.ServiceType.STAFF_CLOCK_IN;

                    if (!isConnected()) {
                        Toast.makeText(ClockIn_with_StaffId.this, "No internet connection",Toast.LENGTH_LONG).show();

                    } else {
                        //new GET_EMPLOYEE_INFO().execute(API + employeeNumber + "/" + latitude + "/" + longitude);
                        if (employeeNumber.equals("2001")){

                            Intent teacherHome = new Intent(ClockIn_with_StaffId.this, TeacherHome.class);
                            teacherHome.putExtra("id", employeeNumber);
                            teacherHome.putExtra("name","Andrew Kajeke");
                            startActivity(teacherHome);

                        }else if (employeeNumber.equals("3001")){

                        }else if (employeeNumber.equals("5001")){

                        }
                    }

                }
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        // getLocation();
    }

    ///Check Internet connection
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}

