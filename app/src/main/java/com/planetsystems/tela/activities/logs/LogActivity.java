package com.planetsystems.tela.activities.logs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.planetsystems.tela.R;

public class LogActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LogActivityViewModel logActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        recyclerView = findViewById(R.id.execution_log_recyclerview);
        logActivityViewModel = new ViewModelProvider(this).get(LogActivityViewModel.class);
    }
}