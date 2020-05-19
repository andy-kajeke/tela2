package com.planetsystems.tela.activities.staff.administration.editTimeTable;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.planetsystems.tela.R;
import com.planetsystems.tela.data.timetable.SyncTimeTable;

import java.util.List;


public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.TaskViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<SyncTimeTable> mSyncTimeTables;

    public TimeTableAdapter(Context context, List<SyncTimeTable> mSyncTimeTables){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mSyncTimeTables = mSyncTimeTables;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.get_timetable, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        final SyncTimeTable syncTimeTable = mSyncTimeTables.get(position);
        if (mSyncTimeTables != null){

            holder.setData(syncTimeTable.getTaskName(), syncTimeTable.getStartTime(), syncTimeTable.getEndTime(), syncTimeTable.getEmployName(), position);

            String L= ""+mSyncTimeTables.get(position).getSubject();
            char k = mSyncTimeTables.get(position).getSubject().charAt(0);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ET = new Intent(mContext, EditTimeTable.class);
                    ET.putExtra("row_id", syncTimeTable.getPrimaryKey());
                    ET.putExtra("taskName", syncTimeTable.getTaskName());
                    ET.putExtra("startTime", syncTimeTable.getStartTime());
                    ET.putExtra("endTime", syncTimeTable.getEndTime());
                    ET.putExtra("teacherName", syncTimeTable.getEmployName());
                    ET.putExtra("teacherNumber", syncTimeTable.getEmployeeNo());
                    mContext.startActivity(ET);
                }
            });

        }else {
            //holder.It_role.setText("No record");
        }
    }

    @Override
    public int getItemCount() {
        if (mSyncTimeTables != null){
            return  mSyncTimeTables.size();
        }else {
            return 0;
        }
    }

    public void setTaskList(List<SyncTimeTable> task){
        mSyncTimeTables = task;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView task_name;
        public TextView task_start;
        public TextView task_end;
        public TextView teacherName;
        public ImageView imageView;
        public int mPosition;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.taskDescription);
            task_start = itemView.findViewById(R.id.startTime);
            task_end = itemView.findViewById(R.id.endTime);
            teacherName = itemView.findViewById(R.id.teacherName);
            imageView = itemView.findViewById(R.id.personphoto);
        }


        public void setData(String subject, String startTime, String endTime, String employeeName, int position) {
            task_name.setText(subject);
            task_start.setText(startTime);
            task_end.setText(endTime);
            teacherName.setText(employeeName);
            mPosition = position;
        }
    }
}


