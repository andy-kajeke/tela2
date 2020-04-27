package com.planetsystems.tela.staff.administration.editStaff;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.planetsystems.tela.R;
import com.planetsystems.tela.data.Teacher.SyncTeacher;

import java.util.List;


public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.StaffViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<SyncTeacher> mSyncTeacherModels;

    public StaffListAdapter(Context context, List<SyncTeacher> mSyncTeacherModels){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mSyncTeacherModels = mSyncTeacherModels;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.get_staff_list, parent, false);
        StaffViewHolder viewHolder = new StaffViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, final int position) {
        SyncTeacher syncTeacher = mSyncTeacherModels.get(position);
        if (mSyncTeacherModels != null){

            holder.setData(syncTeacher.getFirstName(), syncTeacher.getLastName(), syncTeacher.getEmployeeNumber(), syncTeacher.getRole(), position);

            //Getting first character of fistName to imageView
            String L= ""+mSyncTeacherModels.get(position).getFirstName();
            char k = mSyncTeacherModels.get(position).getFirstName().charAt(0);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

            //RecyclerView on item click
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SyncTeacher syncTeacher = mSyncTeacherModels.get(position);
                    Toast.makeText(mContext, syncTeacher.getFirstName(),Toast.LENGTH_LONG).show();
                    Intent edit = new Intent(mContext, UpdateStaffRecord.class);
                    edit.putExtra("id", mSyncTeacherModels.get(position).getId());
                    edit.putExtra("emp_No", mSyncTeacherModels.get(position).getEmployeeNumber());
                    edit.putExtra("emp_firstName", mSyncTeacherModels.get(position).getFirstName());
                    edit.putExtra("emp_lastName", mSyncTeacherModels.get(position).getLastName());
                    edit.putExtra("emp_emailAddress", mSyncTeacherModels.get(position).getEmailAddress());
                    edit.putExtra("emp_initials", mSyncTeacherModels.get(position).getInitials());
                    edit.putExtra("emp_licensed", mSyncTeacherModels.get(position).isLicensed());
                    edit.putExtra("emp_nationalId", mSyncTeacherModels.get(position).getNationalId());
                    edit.putExtra("emp_phoneNumber", mSyncTeacherModels.get(position).getPhoneNumber());
                    edit.putExtra("emp_deploymentSiteId", mSyncTeacherModels.get(position).getSchoolId());
                    edit.putExtra("emp_employmentRoleId", mSyncTeacherModels.get(position).getRole());
                    edit.putExtra("emp_dob", mSyncTeacherModels.get(position).getDob());
                    edit.putExtra("emp_gender", mSyncTeacherModels.get(position).getGender());
                    mContext.startActivity(edit);
                }
            });

        }else {
            holder.It_role.setText("No record");
        }

    }

    @Override
    public int getItemCount() {
        if (mSyncTeacherModels != null){
            return  mSyncTeacherModels.size();
        }else {
            return 0;
        }
    }

    public void setTeacherDetails(List<SyncTeacher> teacher){
        mSyncTeacherModels = teacher;
        notifyDataSetChanged();
    }

    class StaffViewHolder extends RecyclerView.ViewHolder {
        public TextView It_name;
        public TextView It_code;
        public TextView It_role;
        public ImageView imageView;
        public int mPosition;

        public StaffViewHolder(View itemView) {
            super(itemView);
            It_name = itemView.findViewById(R.id.empName);
            It_code = itemView.findViewById(R.id.empID);
            It_role = itemView.findViewById(R.id.empRole);
            imageView = itemView.findViewById(R.id.personphoto);
        }

        public void setData(String firstName, String lastName, String employeeNumber, String role, int position) {
            It_name.setText(firstName + " " + lastName);
            It_code.setText(employeeNumber);
            It_role.setText(role);
            mPosition = position;
        }

//        @Override
//        public void onClick(View v) {
//            SyncTeacher syncTeacher = mSyncTeacherModels.get(getAdapterPosition());
//            Toast.makeText(mContext, syncTeacher.getFirstName(),Toast.LENGTH_LONG).show();
//
////            Intent intent = new Intent(mContext, UpdateStaffRecord.class);
////            mContext.startActivity(intent);
//        }
    }
}


