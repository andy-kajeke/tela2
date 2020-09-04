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
import com.planetsystems.tela.activities.staff.administration.serviceRequests.ApproveMaterialRequests;
import com.planetsystems.tela.data.schoolMaterialRequests.SchoolMaterialRequests;

import java.util.List;


public class ViewMaterialAdapter extends RecyclerView.Adapter<ViewMaterialAdapter.MaterialViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<SchoolMaterialRequests> mSchoolMaterials;
    //private String supervisorID;

    public ViewMaterialAdapter(Context context, List<SchoolMaterialRequests> mSchoolMaterials){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        //this.supervisorID = supervisorID;
        this.mSchoolMaterials = mSchoolMaterials;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.view_replies, parent, false);
        MaterialViewHolder viewHolder = new MaterialViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, final int position) {
        SchoolMaterialRequests schoolMaterials = mSchoolMaterials.get(position);
        if (mSchoolMaterials != null){

            holder.setData(schoolMaterials.getItemName(), schoolMaterials.getRequestDate(), schoolMaterials.getApprovalDate(), schoolMaterials.getEmployeeName(),
                    schoolMaterials.getApprovalStatus(), position);

            String L= ""+mSchoolMaterials.get(position).getItemName();
            char k = mSchoolMaterials.get(position).getItemName().charAt(0);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    SchoolMaterialRequests schoolMaterials1 = mSchoolMaterials.get(position);
//
//                    Intent AMR = new Intent(mContext, ApproveMaterialRequests.class);
//                    AMR.putExtra("db_id", schoolMaterials1.getPrimaryKey());
//                    AMR.putExtra("item_name", schoolMaterials1.getItemName());
//                    AMR.putExtra("requestDate", schoolMaterials1.getRequestDate());
//                    AMR.putExtra("requiredDate", schoolMaterials1.getDateRequired());
//                    AMR.putExtra("reason", schoolMaterials1.getComment());
//                    AMR.putExtra("quantity", schoolMaterials1.getQuantity());
//                    AMR.putExtra("emp_name", schoolMaterials1.getEmployeeName());
//                    AMR.putExtra("supervisor", supervisorID);
//                    mContext.startActivity(AMR);
//                }
//            });

        }else {
            //holder.It_role.setText("No record");
        }
    }

    @Override
    public int getItemCount() {
        if (mSchoolMaterials != null){
            return  mSchoolMaterials.size();
        }else {
            return 0;
        }
    }

    public void setSchoolMaterialsList(List<SchoolMaterialRequests> materialsList){
        mSchoolMaterials = materialsList;
        notifyDataSetChanged();
    }

    public class MaterialViewHolder extends RecyclerView.ViewHolder {
        public TextView request_Type;
        public TextView request_Date;
        public TextView repliedDate;
        public TextView reply;
        public TextView teacherName;
        public ImageView imageView;
        public int mPosition;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            request_Type = itemView.findViewById(R.id.request);
            request_Date = itemView.findViewById(R.id.requestDate);
            repliedDate = itemView.findViewById(R.id.repliedDate);
            reply = itemView.findViewById(R.id.reply);
            teacherName = itemView.findViewById(R.id.teacherName);
            imageView = itemView.findViewById(R.id.personphoto);
        }

        public void setData(String itemName, String requestDate, String approvalDate, String employeeName, String approvalStatus, int position) {
            request_Type.setText(itemName);
            request_Date.setText(requestDate);
            repliedDate.setText(approvalDate);
            reply.setText(approvalStatus);
            teacherName.setText(employeeName);
            mPosition = position;
        }
    }
}


