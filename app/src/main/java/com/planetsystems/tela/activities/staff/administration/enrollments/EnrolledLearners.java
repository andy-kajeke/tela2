package com.planetsystems.tela.activities.staff.administration.enrollments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.learnerAttendance.LearnerAttendanceViewModel;
import com.planetsystems.tela.data.LearnersEnrolled.LearnersEnrolled;
import com.planetsystems.tela.data.TeachersEnrolled.TeachersEnrolled;
import com.planetsystems.tela.utils.DynamicData;

import java.util.List;

import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.SchoolDeviceIMEINumber;
import static com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivity.School_ID;

public class EnrolledLearners extends AppCompatActivity {

    CardView btnSubmit;
    EditText totalMale, totalFemale;
    String class_id, className;
    LearnerAttendanceViewModel learnerAttendanceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_learners);

        totalMale = findViewById(R.id.totalMale);
        totalFemale = findViewById(R.id.totalFemale);
        btnSubmit = findViewById(R.id.submit);

        Bundle bundle = getIntent().getExtras();
        class_id = bundle.getString("class_id");
        className = bundle.getString("class");

        setTitle("Learners Enrolled in" + " " + className);

        learnerAttendanceViewModel = new ViewModelProvider(this).get(LearnerAttendanceViewModel.class);
        learnerAttendanceViewModel.getLearnersEnrolled().observe(this, new Observer<List<LearnersEnrolled>>() {
            @Override
            public void onChanged(List<LearnersEnrolled> learnersEnrolleds) {
                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(learnersEnrolleds.size()), Toast.LENGTH_LONG).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postToLearnersEnrolled();
            }
        });
    }

    private void postToLearnersEnrolled() {
        String male = totalMale.getText().toString();
        String female = totalFemale.getText().toString();

        LearnersEnrolled learnersEnrolled = new LearnersEnrolled(
                School_ID,
                DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                class_id,
                male,
                female,
                DynamicData.getDate(),
                false
        );

        learnerAttendanceViewModel.insertLearnersEnrolled(learnersEnrolled);

        totalMale.setText("");
        totalFemale.setText("");

        Toast.makeText(getApplicationContext(), "Submitted"+class_id, Toast.LENGTH_LONG).show();
    }
}
