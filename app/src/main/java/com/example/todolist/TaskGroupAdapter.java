package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class TaskGroupAdapter extends RecyclerView.Adapter<TaskGroupAdapter.EmployeeViewHolder> {
    private List<TaskEntity> employeeModel;

    public TaskGroupAdapter(List<TaskEntity> employeeModel) {
        this.employeeModel = employeeModel;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_task, parent, false));
    }

    public void addEmployee(TaskEntity employeeModel) {
        this.employeeModel.add(0, employeeModel);
        notifyItemInserted(0);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.bind(employeeModel.get(position));
    }

    @Override
    public int getItemCount() {
        return employeeModel.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
//        private TextView fullNameTv;
//        private TextView courseTitleTv;
//        private TextView scoreTv;
//        private TextView firstCharacterTv;
        private ProgressBar progressBar;
        private MaterialCardView cardView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
//            fullNameTv = itemView.findViewById(R.id.tv_student_fullName);
//            courseTitleTv = itemView.findViewById(R.id.tv_student_course);
//            scoreTv = itemView.findViewById(R.id.tv_student_score);
//            firstCharacterTv = itemView.findViewById(R.id.tv_student_firstCharacter);
            progressBar = itemView.findViewById(R.id.my_progressBar);
           // progressBar = new ProgressBar(itemView.getContext(),null, android.R.attr.);
       //     progressBar.setBackgroundColor(itemView.getResources().getColor(R.color.colorBackgroundMainActivity));
            progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

            cardView = itemView.findViewById(R.id.card);

            cardView.setOnClickListener(v->{
                Intent intent = new Intent(itemView.getContext(),InsideTodayActivity.class);
                itemView.getContext().startActivity(intent);
            });

        }

        @SuppressLint("SetTextI18n")
        public void bind(TaskEntity employeeModel) {
//            fullNameTv.setText(employeeModel.getFirstname() + " " + employeeModel.getLastname());
//            courseTitleTv.setText(employeeModel.getLastname());
//            scoreTv.setText(String.valueOf(employeeModel.getPhonenumber()));
//            firstCharacterTv.setText(employeeModel.getId());
        }
    }
}

