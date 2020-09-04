package com.planetsystems.tela.activities.staff.administration.enrollments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivityViewModel;
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolled;
import com.planetsystems.tela.utils.DynamicData;

import java.util.List;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;
import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.School_ID;

public class EnrolledTeachers extends AppCompatActivity {

    TeacherHomeActivityViewModel teacherHomeActivityViewModel;
    CardView btnSubmit;
    EditText totalMale, totalFemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_teachers);
        setTitle("Enrolled Teachers");

        totalMale = findViewById(R.id.totalMale);
        totalFemale = findViewById(R.id.totalFemale);
        btnSubmit = findViewById(R.id.submit);

        teacherHomeActivityViewModel = new ViewModelProvider( this).get(TeacherHomeActivityViewModel.class);

        teacherHomeActivityViewModel.getTeachersEnrolled().observe(this, new Observer<List<TeachersEnrolled>>() {
            @Override
            public void onChanged(List<TeachersEnrolled> teachersEnrolleds) {
                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(teachersEnrolleds.size()), Toast.LENGTH_LONG).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postToTeachersEnrolled();
            }
        });
    }

    private void postToTeachersEnrolled() {
        String male = totalMale.getText().toString();
        String female = totalFemale.getText().toString();

        TeachersEnrolled teachersEnrolled = new TeachersEnrolled(
                School_ID,
                DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                male,
                female,
                DynamicData.getDate(),
                false
        );

        teacherHomeActivityViewModel.insertTeachersEnrolled(teachersEnrolled);

        totalMale.setText("");
        totalFemale.setText("");

        Toast.makeText(getApplicationContext(), "Submitted"+School_ID, Toast.LENGTH_LONG).show();
    }
}
