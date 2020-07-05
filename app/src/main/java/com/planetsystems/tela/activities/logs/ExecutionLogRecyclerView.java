package com.planetsystems.tela.activities.logs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.planetsystems.tela.R;
import com.planetsystems.tela.data.logs.ExecutionLog;

public class ExecutionLogRecyclerView {
    public static class ExecutionLogViewHold extends RecyclerView.ViewHolder {
        private TextView dateView;
        private TextView timeView;
        private TextView messageView;

        public ExecutionLogViewHold(@NonNull View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.executionDate);
            timeView = itemView.findViewById(R.id.executionTime);
            messageView = itemView.findViewById(R.id.executionMessage);
        }

        public void bindViewHolder(ExecutionLog executionLog) {
            dateView.setText(executionLog.getTime());

        }
    }
}
