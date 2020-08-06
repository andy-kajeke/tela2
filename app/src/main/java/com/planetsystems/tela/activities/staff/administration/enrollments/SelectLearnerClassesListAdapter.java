package com.planetsystems.tela.activities.staff.administration.enrollments;

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
import com.planetsystems.tela.activities.staff.administration.editTimeTable.SelectDay;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;

import java.util.List;


public class SelectLearnerClassesListAdapter extends RecyclerView.Adapter<SelectLearnerClassesListAdapter.SchoolClassesViewHolder> {

    private LayoutInflater layoutInflater;
    private  Context mContext;
    private List<SyncSchoolClasses> mSyncSchoolClasses;
    private String employeeNumber, schoolID;

    public SelectLearnerClassesListAdapter(Context context, List<SyncSchoolClasses> mSyncSchoolClasses, String employeeNumber, String schoolID){
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mSyncSchoolClasses = mSyncSchoolClasses;
        this.employeeNumber = employeeNumber;
        this.schoolID = schoolID;
    }

    @NonNull
    @Override
    public SchoolClassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.get_classes, parent, false);
        SchoolClassesViewHolder viewHolder = new SchoolClassesViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolClassesViewHolder holder, final int position) {
        if (mSyncSchoolClasses != null){
            SyncSchoolClasses syncClockIn = mSyncSchoolClasses.get(position);
            holder.setData(syncClockIn.getClassName(), position);

            String L= ""+mSyncSchoolClasses.get(position).getClassName();
            char k = mSyncSchoolClasses.get(position).getClassName().charAt(2);
            String Cap= ""+k+"".toString().toUpperCase();
            String s = Character.toString(k);

            ColorGenerator generator = ColorGenerator.MATERIAL; // or use

            int color1 = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRoundRect(""+s.toUpperCase(),color1,60); //radius in px

            holder.imageView.setImageDrawable(drawable);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SyncSchoolClasses syncSchoolClasses = mSyncSchoolClasses.get(position);
                    //Toast.makeText(mContext, syncSchoolClasses.getClassName() + " | " + syncSchoolClasses.getId(),Toast.LENGTH_LONG).show();

                    Intent leaner = new Intent(mContext, EnrolledLearners.class);
                    leaner.putExtra("admin", employeeNumber);
                    leaner.putExtra("class", syncSchoolClasses.getClassName());
                    leaner.putExtra("class_id", syncSchoolClasses.getId());
                    mContext.startActivity(leaner);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (mSyncSchoolClasses != null){
            return  mSyncSchoolClasses.size();
        }else {
            return 0;
        }
    }

    public void setSchoolClassesDetails(List<SyncSchoolClasses> schoolClasses){
        mSyncSchoolClasses = schoolClasses;
        notifyDataSetChanged();
    }

    public class SchoolClassesViewHolder extends RecyclerView.ViewHolder {
        public TextView It_name;
        public ImageView imageView;
        public int mPosition;

        public SchoolClassesViewHolder(@NonNull View itemView) {
            super(itemView);
            It_name = itemView.findViewById(R.id.className);
            imageView = itemView.findViewById(R.id.personphoto);
        }

        public void setData(String className, int position) {
            It_name.setText(className);
            mPosition = position;
        }

    }
}


