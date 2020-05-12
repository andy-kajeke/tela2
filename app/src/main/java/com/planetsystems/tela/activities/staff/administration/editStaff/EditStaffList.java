package com.planetsystems.tela.activities.staff.administration.editStaff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.ArrayList;
import java.util.List;

public class EditStaffList extends AppCompatActivity {

    RecyclerView staffs;
    StaffListAdapter adapter;
    private List<SyncTeacher> mSyncTeacher;
    private EditStaffListViewModel editStaffListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff_list);
        setTitle("Staff List");

        staffs = findViewById(R.id.staff_list);

//        Bundle bundle = getIntent().getExtras();
//        school_extra = bundle.getString("school");

        mSyncTeacher = new ArrayList<>();

        adapter = new StaffListAdapter(this, mSyncTeacher);
        staffs.setAdapter(adapter);
        staffs.setLayoutManager(new LinearLayoutManager(this));

        editStaffListViewModel = new ViewModelProvider(this).get(EditStaffListViewModel.class);

        editStaffListViewModel.teachers().observe(this, new Observer<List<SyncTeacher>>() {
            @Override
            public void onChanged(List<SyncTeacher> syncTeachers) {
                adapter.setTeacherDetails(syncTeachers);
            }
        });



}
}
