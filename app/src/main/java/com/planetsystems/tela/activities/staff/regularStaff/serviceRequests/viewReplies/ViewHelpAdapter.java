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
import com.planetsystems.tela.activities.staff.administration.serviceRequests.ApproveHelpRequests;
import com.planetsystems.tela.data.helprequest.HelpRequest;

import java.util.List;


public class ViewHelpAdapter extends RecyclerView.Adapter<ViewHelpAdapter.TaskViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<HelpRequest> mHelpRequests;

    public ViewHelpAdapter(Context context, List<HelpRequest> mHelpRequests){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mHelpRequests = mHelpRequests;
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
        HelpRequest helpRequest = mHelpRequests.get(position);
        if (mHelpRequests != null){

            holder.setData(helpRequest.getRequestType(), helpRequest.getRequestDate(), helpRequest.getEmployeeName(),
                    helpRequest.getApprovalDate(), helpRequest.getApprovalStatus(),position);

            String L= ""+mHelpRequests.get(position).getRequestType();
            char k = mHelpRequests.get(position).getRequestType().charAt(0);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    HelpRequest helpRequest1 = mHelpRequests.get(position);
//
//                    Intent ALR = new Intent(mContext, ApproveHelpRequests.class);
//                    ALR.putExtra("db_id",helpRequest1.getPrimaryKey());
//                    ALR.putExtra("help", helpRequest1.getRequestType());
//                    ALR.putExtra("employee_Name", helpRequest1.getEmployeeName());
//                    ALR.putExtra("priority", helpRequest1.getPriority());
//                    ALR.putExtra("requestDate", helpRequest1.getRequestDate());
//                    ALR.putExtra("reason", helpRequest1.getComment());
//                    ALR.putExtra("supervisor", supervisorID);
//                    mContext.startActivity(ALR);
//                }
//            });

        }else {
            //holder.It_role.setText("No record");
        }
    }

    @Override
    public int getItemCount() {
        if (mHelpRequests != null){
            return  mHelpRequests.size();
        }else {
            return 0;
        }
    }

    public void setHelpRequestsList(List<HelpRequest> help){
        mHelpRequests = help;
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

    }
}


