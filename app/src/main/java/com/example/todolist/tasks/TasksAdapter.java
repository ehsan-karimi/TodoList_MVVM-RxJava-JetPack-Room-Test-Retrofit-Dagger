package com.example.todolist.tasks;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.AddGroups.IconListAdapter;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.R;

import java.util.List;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.EmployeeViewHolder> {
    private List<Tasks> tasks;
    private int done_Tasks = 0;

    public TasksAdapter(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    //declare interface
    private TasksAdapter.OnItemClicked onClick;

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(Tasks tasks);
        void onItemLongClick(Tasks tasks);
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_task, parent, false));
    }

    public void addEmployee(Tasks tasks) {
        this.tasks.add(0, tasks);
        notifyItemInserted(0);
    }

    public int doneTasks(){
        return done_Tasks;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        //        private TextView fullNameTv;
//        private TextView courseTitleTv;
//        private TextView scoreTv;
//        private TextView firstCharacterTv;
        private ProgressBar progressBar;
        private CheckBox test;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            test = itemView.findViewById(R.id.test);
//            fullNameTv = itemView.findViewById(R.id.tv_student_fullName);
//            courseTitleTv = itemView.findViewById(R.id.tv_student_course);
//            scoreTv = itemView.findViewById(R.id.tv_student_score);
//            firstCharacterTv = itemView.findViewById(R.id.tv_student_firstCharacter);
            //    progressBar = itemView.findViewById(R.id.my_progressBar);
            // progressBar = new ProgressBar(itemView.getContext(),null, android.R.attr.);
            //     progressBar.setBackgroundColor(itemView.getResources().getColor(R.color.colorBackgroundMainActivity));
            //    progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        }

        @SuppressLint("SetTextI18n")
        public void bind(Tasks tasks) {
//            fullNameTv.setText(tasks.getFirstname() + " " + tasks.getLastname());
//            courseTitleTv.setText(tasks.getLastname());
//            scoreTv.setText(String.valueOf(tasks.getPhonenumber()));
//            firstCharacterTv.setText(tasks.getId());
            test.setText(tasks.getContent());
            test.setOnClickListener(v->onClick.onItemClick(tasks));
            test.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onClick.onItemLongClick(tasks);
                    return false;
                }
            });

            if (tasks.isDone()){
                test.setChecked(true);
                test.setPaintFlags(test.getPaintFlags() |   Paint.STRIKE_THRU_TEXT_FLAG);
                done_Tasks++;
            }

        }
    }

    public void setOnClick(TasksAdapter.OnItemClicked onClick) {
        this.onClick = onClick;
    }



}


