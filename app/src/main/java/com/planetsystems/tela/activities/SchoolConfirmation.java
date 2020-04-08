package com.planetsystems.tela.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.constants.Urls;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SchoolConfirmation extends AppCompatActivity {

    TextView schoolName, schoolLocation;
    Button confirm;
    ProgressDialog dialog;
    String data, deploymentSiteName, deploymentSiteLocation;
    String deviceIMEI_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_confirmation);

        schoolName = findViewById(R.id.schoolName);
        schoolLocation = findViewById(R.id.schoolLocation);
        confirm = findViewById(R.id.confirm);

        Bundle bundle = getIntent().getExtras();
        deviceIMEI_extra = bundle.getString("device_imei");

        if (!isConnected()) {
            Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show();
        } else {
            new Fetch_API_JSONAsyncTask().execute(Urls.DEVICE_OWNERSHIP + deviceIMEI_extra);
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(SchoolConfirmation.this, ClockInAndOutActivity.class);
                home.putExtra("device_imei", deviceIMEI_extra);
                home.putExtra("schoolName", deploymentSiteName);
                startActivity(home);
            }
        });
    }

    //getting tasks online
    class Fetch_API_JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(SchoolConfirmation.this);
            dialog.setMessage("Getting device ownership...");
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

                    JSONObject objectEmp = jsono.getJSONObject("school");

                    deploymentSiteName =   objectEmp.getString("deploymentSiteName");
                    deploymentSiteLocation = objectEmp.getString("location");

                    JSONArray jarray = jsono.getJSONArray("tasks");


//                    for(int i = 0; i < jarray.length(); i++) {
//                        JSONObject object = jarray.getJSONObject(i);
//
//                        Tasks mark_List = new Tasks();
//
//                        mark_List.setId(object.getString("id"));
//                        mark_List.setTaskDescription(object.getString("taskDescription"));
//                        mark_List.setStartTime(object.getString("startTime"));
//                        mark_List.setEndTime(object.getString("endTime"));
//                        mark_List.setStatus("present");
//                        mark_List.setComment("NA");
//                        markList.add(mark_List);
//                        count++;
//                    }

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
                schoolName.setText(deploymentSiteName);
                schoolLocation.setText(deploymentSiteLocation);
            }else{
                Toast.makeText(getApplicationContext(), "Unable to fetch ", Toast.LENGTH_LONG).show();
                //uiName.setText(""+namez);
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
