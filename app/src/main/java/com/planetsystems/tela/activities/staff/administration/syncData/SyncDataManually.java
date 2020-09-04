package com.planetsystems.tela.activities.staff.administration.syncData;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.mainActivity.MainActivity;
import com.planetsystems.tela.activities.school.SchoolConfirmation;
import com.planetsystems.tela.constants.Urls;
import com.planetsystems.tela.workers.WorkManagerTrigger;

public class SyncDataManually extends Activity {

    private static int SPLASH_TIME_OUT = 6000;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_data_manually);
        setTitle("Synchronize Data");

        if (!isConnected()) {
            Toast.makeText(this, "No connection", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(SyncDataManually.this)
                    .setTitle("Confirmation")
                    .setMessage("Network failed. Check your internet connection and Try again... \n")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            SyncDataManually.this.finish();
                        }}).show();
        }
        else {
            //Synchronize the school data to phone and to the sever
            WorkManagerTrigger.startFetchWorkers(getApplicationContext());
            WorkManagerTrigger.startUploadWorkers(getApplicationContext());

            dialog = new ProgressDialog(SyncDataManually.this);
            dialog.setMessage("Synchronizing Data\n Pleas wait...");
            //dialog.setTitle("Loading ..");
            dialog.show();
            dialog.setCancelable(false);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    dialog.cancel();

                    new AlertDialog.Builder(SyncDataManually.this)
                            .setTitle("Confirmation")
                            .setMessage("Successfully Synced Data")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    SyncDataManually.this.finish();
                                }}).show();
                }
            }, SPLASH_TIME_OUT);
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
