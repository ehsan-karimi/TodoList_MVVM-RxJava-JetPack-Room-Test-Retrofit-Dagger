package com.example.todolist.InsideGroupToday;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.AddTasks.AddTaskActivity;
import com.example.todolist.Main.MainActivity;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

public class InsideTodayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskListAdapter taskListAdapter;
    private ExtendedFloatingActionButton extended_fab_add_task;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_today);
//        AndroidInjection.inject(this);
        initialize();
        setOnClick();
        showList(getResources().getString(R.string.json));
    }

    private void initialize(){
        recyclerView = findViewById(R.id.taskListRv);
        extended_fab_add_task = findViewById(R.id.extended_fab_add_task);
    }

    private void setOnClick(){
        extended_fab_add_task.setOnClickListener(v->{
            Intent intent = new Intent(this , AddTaskActivity.class);
            startActivity(intent);
        });
    }

    private void showList(String result) {
        java.util.List<Tasks> tasksList = new Gson().fromJson(result, new TypeToken<List<Tasks>>() {
        }.getType());

          recyclerView.setLayoutManager(new LinearLayoutManager(InsideTodayActivity.this, RecyclerView.VERTICAL, false));
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(InsideTodayActivity.this, 2);
//        recyclerView.setLayoutManager(mLayoutManager);
        taskListAdapter = new TaskListAdapter(tasksList);
        recyclerView.setAdapter(taskListAdapter);
    }
}
