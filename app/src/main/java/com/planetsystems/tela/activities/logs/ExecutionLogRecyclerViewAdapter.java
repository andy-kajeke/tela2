package com.planetsystems.tela.activities.logs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.logs.ExecutionLog;

import java.util.List;

public class ExecutionLogRecyclerViewAdapter extends RecyclerView.Adapter<ExecutionLogRecyclerViewAdapter.ExecutionLogViewHolder> {

    private List<ExecutionLog> executionLogs;

    @NonNull
    @Override
    public ExecutionLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.execution_log_item, parent, false);
        return new ExecutionLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExecutionLogViewHolder holder, int position) {
        if (executionLogs != null) {
            ExecutionLog executionLog = executionLogs.get(position);
            holder.bindViewHolder(executionLog);
        } else holder.bindViewHolder(new ExecutionLog("No Logs", null, null, null, null, null));

    }

    @Override
    public int getItemCount() {
        if (executionLogs != null) {
            return executionLogs.size();
        } else return 0;
    }

    public static class ExecutionLogViewHolder extends RecyclerView.ViewHolder {
        private TextView dateView;
        private TextView timeView;
        private TextView messageView;

        public ExecutionLogViewHolder(@NonNull View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.executionDate);
            timeView = itemView.findViewById(R.id.executionTime);
            messageView = itemView.findViewById(R.id.executionMessage);
        }

        public void bindViewHolder(ExecutionLog executionLog) {
            if (executionLog.getDate() == null ) {
                dateView.setVisibility(View.GONE);
                timeView.setVisibility(View.GONE);
            } else {
                dateView.setText(executionLog.getDate());
                timeView.setText(executionLog.getTime());
            }
            messageView.setText(executionLog.getMessage());

        }
    }

    public void setExecutionLog(List<ExecutionLog> executionLogs) {
        this.executionLogs = executionLogs;
        notifyDataSetChanged();
    }
}
