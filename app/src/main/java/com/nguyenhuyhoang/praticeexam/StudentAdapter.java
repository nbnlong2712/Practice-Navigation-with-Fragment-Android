package com.nguyenhuyhoang.praticeexam;

import android.content.Intent;
import android.media.tv.TvContentRating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    List<Student> mStudentList;
    OnStudentClick mOnStudentClick;

    public StudentAdapter() {}

    public void setOnStudentClick(OnStudentClick onStudentClick) {
        mOnStudentClick = onStudentClick;
    }

    public void setData(List<Student> list)
    {
        mStudentList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = mStudentList.get(position);

        holder.TvBirth.setText(student.getBirth());
        holder.TvName.setText(student.getName());
        holder.TvRegion.setText(student.getRegion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnStudentClick.studentClick(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mStudentList == null)
            return 0;
        return mStudentList.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder  {
        TextView TvName, TvRegion, TvBirth;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            TvName = (TextView) itemView.findViewById(R.id.tv_name);
            TvRegion = (TextView) itemView.findViewById(R.id.tv_region);
            TvBirth = (TextView) itemView.findViewById(R.id.tv_birth);
        }
    }
}
