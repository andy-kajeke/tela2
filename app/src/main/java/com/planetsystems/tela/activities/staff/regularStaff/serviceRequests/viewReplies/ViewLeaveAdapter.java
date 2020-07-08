package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.viewReplies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.ApproveLeaveRequest;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.ApproveMeetingRequests;
import com.planetsystems.tela.data.employeeTimeOffRequestDM.SyncEmployeeTimeOffRequestDM;

import java.util.List;


public class ViewLeaveAdapter extends RecyclerView.Adapter<ViewLeaveAdapter.TaskViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<SyncEmployeeTimeOffRequestDM> mSyncEmployeeTimeOffRequestDMS;

    public ViewLeaveAdapter(Context context, List<SyncEmployeeTimeOffRequestDM> mSyncEmployeeTimeOffRequestDMS){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mSyncEmployeeTimeOffRequestDMS = mSyncEmployeeTimeOffRequestDMS;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.view_replies, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        SyncEmployeeTimeOffRequestDM syncEmployeeTimeOffRequestDM = mSyncEmployeeTimeOffRequestDMS.get(position);
        if (mSyncEmployeeTimeOffRequestDMS != null){

            holder.setData(syncEmployeeTimeOffRequestDM.getTypeOfLeave(), syncEmployeeTimeOffRequestDM.getRequestDate(), syncEmployeeTimeOffRequestDM.getEmployee(),
                    syncEmployeeTimeOffRequestDM.getApprovalDate(), syncEmployeeTimeOffRequestDM.getApprovalStatus(),position);

            String L= ""+mSyncEmployeeTimeOffRequestDMS.get(position).getTypeOfLeave();
            char k = mSyncEmployeeTimeOffRequestDMS.get(position).getTypeOfLeave().charAt(0);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

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
        public TextView repliedDate;
        public TextView reply;
        public TextView teacherName;
        public ImageView imageView;
        public int mPosition;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            request_Type = itemView.findViewById(R.id.request);
            request_Date = itemView.findViewById(R.id.requestDate);
            repliedDate = itemView.findViewById(R.id.repliedDate);
            reply = itemView.findViewById(R.id.reply);
            teacherName = itemView.findViewById(R.id.teacherName);
            imageView = itemView.findViewById(R.id.personphoto);
        }

        public void setData(String typeOfLeave, String requestDate, String employee, String approvalDate, String approvalStatus, int position) {
            request_Type.setText(typeOfLeave);
            request_Date.setText(requestDate);
            repliedDate.setText(approvalDate);
            reply.setText(approvalStatus);
            teacherName.setText(employee);
            mPosition = position;
        }

//        public void setData(String ofLeave, String date, String typeOfLeave, String requestDate, String employee, int position) {
//            request_Type.setText(typeOfLeave);
//            request_Date.setText(requestDate);
//            teacherName.setText(employee);
//            mPosition = position;
//        }

//
    }
}


