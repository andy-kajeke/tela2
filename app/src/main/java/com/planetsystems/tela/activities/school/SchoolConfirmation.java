package com.planetsystems.tela.activities.school;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.utils.DynamicData;
import com.planetsystems.tela.workers.WorkManagerTrigger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class SchoolConfirmation extends AppCompatActivity {
    public static SharedPreferences mSharedPreferences;
    public static final String schoolPreference = "schoolPreference";
    public static final String school_Device_Number = "deviceNumber";
    public static final String school_Name = "deploymentSiteName";
    public static final String school_id = "id";

    TextView schoolName, schoolLocation;
    EditText phoneNumber;
    Button confirm;
    ProgressDialog dialog;
    String data, deploymentSiteName, deploymentSiteLocation, schoolId;
    Boolean mResponse;
    String deviceIMEI_extra, deviceNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_confirmation);

        //schoolName = findViewById(R.id.schoolName);
        //schoolLocation = findViewById(R.id.schoolLocation);
        confirm = findViewById(R.id.confirm);
        phoneNumber = findViewById(R.id.phoneNumber);

        mSharedPreferences = getSharedPreferences(schoolPreference, Context.MODE_PRIVATE);

//        Bundle bundle = getIntent().getExtras();
//        deviceIMEI_extra = bundle.getString("device_imei");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected()) {
                    Toast.makeText(SchoolConfirmation.this, "No connection", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(SchoolConfirmation.this)
                            .setTitle("Confirmation")
                            .setMessage("Network failed. Check your internet connection and Try again... \n")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    SchoolConfirmation.this.finish();
                                }}).show();
                }
                else {
                    //Synchronize the school data to phone and to the sever
                    WorkManagerTrigger.startFetchWorkers(getApplicationContext());
                    WorkManagerTrigger.startUploadWorkers(getApplicationContext());

                    //Get device ownership by IMEI number
                    new Fetch_API_JSONAsyncTask().execute(Urls.DEVICE_OWNERSHIP + phoneNumber.getText().toString());
                }

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }
    }

    //////////////////////GPS Functionality//////////////////////////////////////////////////////
    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }

    private void saveToSharePreference(String deviceIMEI_extra, String deploymentSiteName) {
//        SharedPreferences sharedPreferences = getSharedPreferences("school_infor", )
    }

    //getting tasks online
    class Fetch_API_JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(SchoolConfirmation.this);
            dialog.setMessage("Getting number Ownership \n Pleas wait...");
            //dialog.setTitle("Loading ..");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {

            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("id","cmd"));
                URI uri = new URI(urls[0] + "?" + URLEncodedUtils.format(params, "utf-8"));
                HttpGet httppost = new HttpGet(uri);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);
                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    data = EntityUtils.toString(entity);

                    JSONObject jsono = new JSONObject(data);
                    mResponse = jsono.getBoolean("response");

                    JSONObject objectEmp = jsono.getJSONObject("school");

                    deploymentSiteName =   objectEmp.getString("deploymentSiteName");
                    deviceNumber = objectEmp.getString("deviceNumber");
                    deploymentSiteLocation = objectEmp.getString("location");
                    schoolId = objectEmp.getString("id");

                    return true;
                }else {

                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (URISyntaxException es) {
                es.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            //adapter.notifyDataSetChanged();
            if(result == false){
                new AlertDialog.Builder(SchoolConfirmation.this)
                        .setTitle("Confirmation")
                        .setMessage("This phone is not registered on the TELA System")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                SchoolConfirmation.this.finish();
                            }}).show();
                        //.setNegativeButton("", null).show();

            }else{
                //Toast.makeText(getApplicationContext(), "Unable to fetch", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(school_Device_Number, deviceNumber);
                editor.putString(school_Name, deploymentSiteName);
                editor.putString(school_id, schoolId);
                editor.commit();

//                schoolName.setText(mSharedPreferences.getString(school_Name, ""));
//                schoolLocation.setText(deploymentSiteLocation);
                if(mResponse == true){
                    new AlertDialog.Builder(SchoolConfirmation.this)
                            .setTitle("School Confirmation")
                            .setMessage("This phone number belongs to " + mSharedPreferences.getString(school_Name, "") + " located in " +
                                    deploymentSiteLocation + ".")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Continue", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent home = new Intent(SchoolConfirmation.this, ClockInAndOutActivity.class);
                                    home.putExtra("schoolId", schoolId);
                                    startActivity(home);
                                }})
                            .setNegativeButton("Cancle", null).show();
                }
                else {
                    new AlertDialog.Builder(SchoolConfirmation.this)
                            .setTitle("Confirmation")
                            .setMessage("You can't contiune. This phone number is not registered on the TELA System. \n ")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setNegativeButton("Alright", null).show();
                    //.setNegativeButton("", null).show();
                }
            }
        }
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
