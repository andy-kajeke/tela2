package com.planetsystems.tela.activities.staff.administration.taskAttendance;

import android.content.Context;
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
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;

import java.util.List;


public class TasksConfirmedAdapter extends RecyclerView.Adapter<TasksConfirmedAdapter.TaskViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<SynTimeOnTask> mSynTimeOnTask;

    public TasksConfirmedAdapter(Context context, List<SynTimeOnTask> mSynTimeOnTask){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mSynTimeOnTask = mSynTimeOnTask;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.get_task_item_supervision, parent, false);
        TaskViewHolder viewHolder = new TaskViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        SynTimeOnTask synTimeOnTask = mSynTimeOnTask.get(position);
        if (mSynTimeOnTask != null){

            holder.setData(synTimeOnTask.getTaskName(), synTimeOnTask.getStartTime(), synTimeOnTask.getEndTime(), position);

            String L= ""+mSynTimeOnTask.get(position).getTaskName();
            char k = mSynTimeOnTask.get(position).getTaskName().charAt(4);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

            //switch action when toggled
            holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    if (!isChecked) {
//                        //Toast.makeText(getContext(),"Checked",Toast.LENGTH_SHORT).show();
//                        mSynTimeOnTask.get(position).setStatus("Absent");
//                        Toast.makeText(mContext ,mSynTimeOnTask.get(position).getStatus(),Toast.LENGTH_SHORT).show();
//                        //status_ = proList.get(position).setStatus("present");
//
//                    } else {
//                        mSynTimeOnTask.get(position).setStatus("present");
//                        Toast.makeText(mContext,mSynTimeOnTask.get(position).getStatus(),Toast.LENGTH_SHORT).show();
//                        //status_ = proList.get(position).setStatus("absent");
//                    }
                }
            });

            holder.aaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked){

                    }else {

                    }
                }
            });

        }else {
            //holder.It_role.setText("No record");
        }
    }

    @Override
    public int getItemCount() {
        if (mSynTimeOnTask != null){
            return  mSynTimeOnTask.size();
        }else {
            return 0;
        }
    }

    public void setTaskList(List<SynTimeOnTask> task){
        mSynTimeOnTask = task;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView task_name;
        public TextView task_start;
        public TextView task_end;
        public Switch aSwitch, aaSwitch;
        public ImageView imageView;
        public int mPosition;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.taskDescription);
            task_start = itemView.findViewById(R.id.startTime);
            task_end = itemView.findViewById(R.id.endTime);
            aSwitch = itemView.findViewById(R.id.switch1);
            aaSwitch = itemView.findViewById(R.id.switch2);
            imageView = itemView.findViewById(R.id.personphoto);
        }

        public void setData(String TaskName, String startTime, String endTime, int position) {
            task_name.setText(TaskName);
            task_start.setText(startTime);
            task_end.setText(endTime);
            mPosition = position;
        }
    }
}


