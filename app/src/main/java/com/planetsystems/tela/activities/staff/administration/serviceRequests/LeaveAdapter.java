package com.planetsystems.tela.activities.staff.administration.serviceRequests;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.planetsystems.tela.R;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;

import java.util.List;


public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.TaskViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private String supervisorID;
    private List<SyncEmployeeTimeOffRequestDM> mSyncEmployeeTimeOffRequestDMS;

    public LeaveAdapter(Context context, List<SyncEmployeeTimeOffRequestDM> mSyncEmployeeTimeOffRequestDMS, String supervisorID){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.supervisorID = supervisorID;
        this.mSyncEmployeeTimeOffRequestDMS = mSyncEmployeeTimeOffRequestDMS;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.get_pending_requests, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM = mSyncEmployeeTimeOffRequestDMS.get(position);
        if (mSyncEmployeeTimeOffRequestDMS != null){

            holder.setData(syncEmployeeTimeOffRequestDM.getTypeOfLeave(), syncEmployeeTimeOffRequestDM.getRequestDate(), syncEmployeeTimeOffRequestDM.getEmployee(), position);

            String L= ""+mSyncEmployeeTimeOffRequestDMS.get(position).getTypeOfLeave();
            char k = mSyncEmployeeTimeOffRequestDMS.get(position).getTypeOfLeave().charAt(0);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM1 = mSyncEmployeeTimeOffRequestDMS.get(position);

                    if (syncEmployeeTimeOffRequestDM1.getEmployeeRequestType().equals("Time Off")){
                        Intent ALR = new Intent(mContext, ApproveLeaveRequest.class);
                        ALR.putExtra("db_id",syncEmployeeTimeOffRequestDM1.getPrimaryKey());
                        ALR.putExtra("leave", syncEmployeeTimeOffRequestDM1.getTypeOfLeave());
                        ALR.putExtra("employee_Name", syncEmployeeTimeOffRequestDM1.getEmployee());
                        ALR.putExtra("startDate", syncEmployeeTimeOffRequestDM1.getFromDate());
                        ALR.putExtra("endDate", syncEmployeeTimeOffRequestDM1.getToDate());
                        ALR.putExtra("requestDate", syncEmployeeTimeOffRequestDM1.getRequestDate());
                        ALR.putExtra("reason", syncEmployeeTimeOffRequestDM1.getComment());
                        ALR.putExtra("supervisor", supervisorID);
                        mContext.startActivity(ALR);
                    }
                    else if (syncEmployeeTimeOffRequestDM1.getEmployeeRequestType().equals("Meeting")){
                        Intent ALR = new Intent(mContext, ApproveMeetingRequests.class);
                        ALR.putExtra("db_id",syncEmployeeTimeOffRequestDM1.getPrimaryKey());
                        ALR.putExtra("employee_Name", syncEmployeeTimeOffRequestDM1.getEmployee());
                        ALR.putExtra("startDate", syncEmployeeTimeOffRequestDM1.getFromDate());
                        ALR.putExtra("endDate", syncEmployeeTimeOffRequestDM1.getToDate());
                        ALR.putExtra("startTime", syncEmployeeTimeOffRequestDM1.getFromTime());
                        ALR.putExtra("endTime", syncEmployeeTimeOffRequestDM1.getToTime());
                        ALR.putExtra("requestDate", syncEmployeeTimeOffRequestDM1.getRequestDate());
                        ALR.putExtra("reason", syncEmployeeTimeOffRequestDM1.getComment());
                        ALR.putExtra("supervisor", supervisorID);
                        mContext.startActivity(ALR);
                    }
                }
            });

        }else {
            //holder.It_role.setText("No record");
        }
    }

    @Override
    public int getItemCount() {
        if (mSyncEmployeeTimeOffRequestDMS != null){
            return  mSyncEmployeeTimeOffRequestDMS.size();
        }else {
            return 0;
        }
    }

    public void setTaskList(List<SyncEmployeeTimeOffRequestDM> task){
        mSyncEmployeeTimeOffRequestDMS = task;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView request_Type;
        public TextView request_Date;
        public TextView teacherName;
        public ImageView imageView;
        public int mPosition;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            request_Type = itemView.findViewById(R.id.requestDescription);
            request_Date = itemView.findViewById(R.id.requestDate);
            teacherName = itemView.findViewById(R.id.teacherName);
            imageView = itemView.findViewById(R.id.personphoto);
        }

        public void setData(String typeOfLeave, String requestDate, String employee, int position) {
            request_Type.setText(typeOfLeave);
            request_Date.setText(requestDate);
            teacherName.setText(employee);
            mPosition = position;
        }

//
    }
}


