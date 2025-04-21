package com.codebnb.stayflow.superAdmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codebnb.stayflow.R;
import com.codebnb.stayflow.superAdmin.model.LogItem;

import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.LogViewHolder> {

    private final List<LogItem> logList;

    public LogsAdapter(List<LogItem> logList) {
        this.logList = logList;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.superadmin_log_item, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        LogItem item = logList.get(position);
        holder.title.setText(item.title);
        holder.timestamp.setText(item.timestamp);
        holder.description.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView title, timestamp, description;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.logTitle);
            timestamp = itemView.findViewById(R.id.logTimestamp);
            description = itemView.findViewById(R.id.logDescription);
        }
    }
}

