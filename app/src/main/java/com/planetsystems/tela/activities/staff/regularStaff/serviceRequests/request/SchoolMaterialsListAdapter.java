package com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.request;

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
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;

import java.util.List;


public class SchoolMaterialsListAdapter extends RecyclerView.Adapter<SchoolMaterialsListAdapter.MaterialViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<SchoolMaterials> mSchoolMaterials;
    private String employeeID, employeeName;

    public SchoolMaterialsListAdapter(Context context, List<SchoolMaterials> mSchoolMaterials, String employeeID, String employeeName){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.mSchoolMaterials = mSchoolMaterials;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.get_school_materials, parent, false);
        MaterialViewHolder viewHolder = new MaterialViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, final int position) {
        SchoolMaterials schoolMaterials = mSchoolMaterials.get(position);
        if (mSchoolMaterials != null){

            holder.setData(schoolMaterials.getItemName(), schoolMaterials.getCode(), position);

            String L= ""+mSchoolMaterials.get(position).getItemName();
            char k = mSchoolMaterials.get(position).getItemName().charAt(0);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SchoolMaterials schoolMaterials1 = mSchoolMaterials.get(position);

                    Intent RSM = new Intent(mContext, RequestSchoolMaterials.class);
                    RSM.putExtra("item_id", schoolMaterials1.getId());
                    RSM.putExtra("item_name", schoolMaterials1.getItemName());
                    RSM.putExtra("emp_id", employeeID);
                    RSM.putExtra("emp_name", employeeName);
                    mContext.startActivity(RSM);
                }
            });

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

    public void setSchoolMaterialsList(List<SchoolMaterials> materialsList){
        mSchoolMaterials = materialsList;
        notifyDataSetChanged();
    }

    public class MaterialViewHolder extends RecyclerView.ViewHolder {
        public TextView item;
        public TextView itemCode;
        public ImageView imageView;
        public int mPosition;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.itemName);
            itemCode = itemView.findViewById(R.id.code);
            imageView = itemView.findViewById(R.id.personphoto);
        }

        public void setData(String itemName, String code, int position) {
            item.setText(itemName);
            itemCode.setText(code);
            mPosition = position;
        }
    }
}


