package com.planetsystems.tela.activities.logs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.logs.ExecutionLog;

import java.util.List;

public class LogActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LogActivityViewModel logActivityViewModel;
    List<ExecutionLog> executionLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        recyclerView = findViewById(R.id.execution_log_recyclerview);
        final ExecutionLogRecyclerViewAdapter viewAdapter = new ExecutionLogRecyclerViewAdapter(this);

        logActivityViewModel = new ViewModelProvider(this).get(LogActivityViewModel.class);
        recyclerView.setAdapter(viewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        logActivityViewModel.getLogs().observe(this, new Observer<List<ExecutionLog>>() {
            @Override
            public void onChanged(List<ExecutionLog> executionLogs) {
                Toast.makeText(LogActivity.this, String.valueOf(executionLogs.size()), Toast.LENGTH_SHORT).show();
                viewAdapter.setExecutionLog(executionLogs);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bug_section_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_log:
                logActivityViewModel.clearLog();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}