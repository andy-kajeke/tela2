package com.planetsystems.tela.activities.staff.administration.taskAttendance;

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
import com.planetsystems.tela.activities.staff.administration.timeAttendance.ConfirmTimeAttendance;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;

import java.util.List;


public class TaskAttendanceAdapter extends RecyclerView.Adapter<TaskAttendanceAdapter.StaffViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<SyncClockIn> mSyncClockIn;
    private String supervisor;

    public TaskAttendanceAdapter(Context context, String supervisor){
        layoutInflater = LayoutInflater.from(context);
        supervisor = supervisor;
        mContext = context;
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
        if (mSyncClockIn != null){
            final SyncClockIn syncClockIn = mSyncClockIn.get(position);
            holder.setData(syncClockIn.getFirstName(), syncClockIn.getLastName(), syncClockIn.getEmployeeNo(), position);

            String L= ""+mSyncClockIn.get(position).getFirstName();
            char k = mSyncClockIn.get(position).getFirstName().charAt(0);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

            //Confirm teacher attendance on site
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SyncClockIn syncClockIn1 = mSyncClockIn.get(position);
                    Intent SO = new Intent(mContext, SupervisorObservations.class);
                    SO.putExtra("admin",supervisor);
                    SO.putExtra("employee_No", syncClockIn1.getEmployeeNo());
                    SO.putExtra("employee_Name", syncClockIn1.getFirstName() + " " + syncClockIn1.getLastName());
                    //CTA.putExtra("teacher_name", syncClockIn1.getFirstName() + " " + syncClockIn1.getLastName());
                    mContext.startActivity(SO);
                }
            });

        }else {
           // holder.It_role.setText("No record");
        }
    }

    @Override
    public int getItemCount() {
        if (mSyncClockIn != null){
            return  mSyncClockIn.size();
        }else {
            return 0;
        }
    }

    public void setTeacherDetails(List<SyncClockIn> teacher){
        mSyncClockIn = teacher;
        notifyDataSetChanged();
    }

    public class StaffViewHolder extends RecyclerView.ViewHolder {
        public TextView It_name;
        public TextView It_code;
        public TextView It_role;
        public ImageView imageView;
        public int mPosition;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            It_name = itemView.findViewById(R.id.empName);
            It_code = itemView.findViewById(R.id.empID);
            It_role = itemView.findViewById(R.id.empRole);
            imageView = itemView.findViewById(R.id.personphoto);
        }

        public void setData(String firstName, String lastName, String employeeNo, int position) {
            It_name.setText(firstName + " " + lastName);
            It_code.setText(employeeNo);
            //It_role.setText(clockInTime);
            mPosition = position;
        }

    }
}


