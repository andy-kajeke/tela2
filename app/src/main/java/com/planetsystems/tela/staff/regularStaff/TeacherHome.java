package com.planetsystems.tela.staff.regularStaff;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity;
import com.planetsystems.tela.staff.regularStaff.serviceRequests.MakeRequests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TeacherHome extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ProgressDialog dialog;
    int count =0;
    int count2 =0;
    Bundle bundle;
    String  data;
    ListView lstView1;
    String test="";
    TextView datetoday;
    String namez="";
    String empNo="";
    TextView emp_Name;
    TextView emp_Id;
    TextView tvname;
    //TextView datetoday;
    Button submit, selfmenu;
//    ArrayList<Tasks> markList;
//    TaskAdapter adapter;
    public String id_extra;
    String lat_extra, long_extra;
    String emp_id_extra, emp_name_extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        lstView1 = findViewById(R.id.card_listView);
        emp_Name = findViewById(R.id.namexx);
        emp_Id = findViewById(R.id.staffId);
        datetoday = findViewById(R.id.datetoday);
        submit= findViewById(R.id.submit);
        selfmenu= findViewById(R.id.menuBtn);

        Date currentTime = Calendar.getInstance().getTime();
        datetoday.setText(""+currentTime.toString());

        Bundle bundle = getIntent().getExtras();
        emp_id_extra = bundle.getString("id");
        emp_name_extra = bundle.getString("name");

        emp_Name.append(emp_name_extra);
        emp_Id.append(emp_id_extra);

        //submit attendance commitment
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(TeacherHome.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to submit your attendance?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                //new API_JSONAsyncTask().execute();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        //more options
        selfmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(TeacherHome.this, v);
                popup.setOnMenuItemClickListener(TeacherHome.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.request) {

            Intent intent = new Intent(TeacherHome.this, MakeRequests.class);
            intent.putExtra("name", emp_name_extra);
            //intent.putExtra("school_id", school_extra);
            intent.putExtra("id", emp_id_extra);
            startActivity(intent);
            //return true;
        }
        else if (id == R.id.myStatus) {

//            Intent intent = new Intent(Emp_Home.this, MyStatus.class);
//            intent.putExtra("name", emp_name_extra);
//            intent.putExtra("id", emp_id_extra);
//            startActivity(intent);
            //return true;
        }
        else if (id == R.id.Logout) {
            Intent intent = new Intent(TeacherHome.this, ClockInAndOutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            TeacherHome.this.finish();
            //return true;
        }

        return super.onOptionsItemSelected(item);
        //return false;
    }
}