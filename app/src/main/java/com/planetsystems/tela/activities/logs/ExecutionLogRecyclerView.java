package com.planetsystems.tela.activities.logs;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.logs.ExecutionLog;

public class ExecutionLogRecyclerView extends RecyclerView.Adapter<ExecutionLogRecyclerView.ExecutionLogViewHolder> {
    @NonNull
    @Override
    public ExecutionLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ExecutionLogViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
            dateView.setText(executionLog.getDate());
            timeView.setText(executionLog.getTime());
            messageView.setText(executionLog.getMessage());

        }
    }
}
