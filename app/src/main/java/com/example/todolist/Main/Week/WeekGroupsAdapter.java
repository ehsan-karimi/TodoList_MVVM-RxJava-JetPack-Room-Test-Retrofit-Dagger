package com.example.todolist.Main.Week;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.tasks.TasksActivity;
import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;


public class WeekGroupsAdapter extends RecyclerView.Adapter<WeekGroupsAdapter.weekGroupsViewHolder> {
    private List<Groups> groupsList;

    public WeekGroupsAdapter(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @NonNull
    @Override
    public WeekGroupsAdapter.weekGroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeekGroupsAdapter.weekGroupsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_task, parent, false));
    }

    public void addEmployee(Groups groupsList) {
        this.groupsList.add(0, groupsList);
        notifyItemInserted(0);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekGroupsAdapter.weekGroupsViewHolder holder, int position) {
        holder.bind(groupsList.get(position));
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    public class weekGroupsViewHolder extends RecyclerView.ViewHolder {
        //        private TextView fullNameTv;
//        private TextView courseTitleTv;
//        private TextView scoreTv;
//        private TextView firstCharacterTv;
        private ProgressBar progressBar;
        private MaterialCardView cardView;
        private ImageView icon;
        private TextView label;
        private TextView taskCount;

        public weekGroupsViewHolder(@NonNull View itemView) {
            super(itemView);
//            fullNameTv = itemView.findViewById(R.id.tv_student_fullName);
//            courseTitleTv = itemView.findViewById(R.id.tv_student_course);
//            scoreTv = itemView.findViewById(R.id.tv_student_score);
//            firstCharacterTv = itemView.findViewById(R.id.tv_student_firstCharacter);
            progressBar = itemView.findViewById(R.id.progress_Groups);
            icon = itemView.findViewById(R.id.iv_TodayGroups);
            label = itemView.findViewById(R.id.tv_LabelTodayGroups);
            cardView = itemView.findViewById(R.id.card);
            taskCount = itemView.findViewById(R.id.taskCount);
            // progressBar = new ProgressBar(itemView.getContext(),null, android.R.attr.);
            //     progressBar.setBackgroundColor(itemView.getResources().getColor(R.color.colorBackgroundMainActivity));



        }

        @SuppressLint("SetTextI18n")
        public void bind(Groups groupsList) {
//            fullNameTv.setText(groupsList.getFirstname() + " " + groupsList.getLastname());
//            courseTitleTv.setText(groupsList.getLastname());
//            scoreTv.setText(String.valueOf(groupsList.getPhonenumber()));
//            firstCharacterTv.setText(groupsList.getId());
            icon.setImageResource(groupsList.getIcon());
            label.setText(groupsList.getLabel());
            taskCount.setText(String.valueOf(groupsList.getTasksCount()) + " task");


            cardView.setOnClickListener(v->{
                Intent intent = new Intent(itemView.getContext(), TasksActivity.class);
                intent.putExtra("groupId",groupsList.getId());
                intent.putExtra("groupLabel",groupsList.getLabel());
                intent.putExtra("groupCategory","Week");
                itemView.getContext().startActivity(intent);
            });

            progressBar.setMax(100);
            progressBar.setProgress(groupsList.getDonePercent());

            switch (groupsList.getIcon()){
                case 2131165301:
                    progressBar.getProgressDrawable().setColorFilter(itemView.getResources().getColor(R.color.purple), PorterDuff.Mode.SRC_IN);
                    break;
                case 2131165309:
                    progressBar.getProgressDrawable().setColorFilter(itemView.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_IN);
                    break;
                case 2131165314:
                    progressBar.getProgressDrawable().setColorFilter(itemView.getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_IN);
                    break;
                case 2131165315:
                    progressBar.getProgressDrawable().setColorFilter(itemView.getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_IN);
                    break;
                case 2131165316:
                    progressBar.getProgressDrawable().setColorFilter(itemView.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                    break;
            }

        }
    }
}


