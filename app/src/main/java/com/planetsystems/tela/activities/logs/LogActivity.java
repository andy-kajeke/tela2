package com.planetsystems.tela.activities.logs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.logs.ExecutionLog;

import java.util.List;

public class LogActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LogActivityViewModel logActivityViewModel;
    ExecutionLogRecyclerViewAdapter viewAdapter;
    List<ExecutionLog> executionLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        recyclerView = findViewById(R.id.execution_log_recyclerview);
        viewAdapter = new ExecutionLogRecyclerViewAdapter();

        logActivityViewModel = new ViewModelProvider(this).get(LogActivityViewModel.class);
        recyclerView.setAdapter(viewAdapter);
        logActivityViewModel.getLogs().observe(this, new Observer<List<ExecutionLog>>() {
            @Override
            public void onChanged(List<ExecutionLog> executionLogs) {
                Toast.makeText(LogActivity.this, String.valueOf(executionLogs.size()), Toast.LENGTH_SHORT).show();
                viewAdapter.setExecutionLog(executionLogs);
            }
        });

    }
}