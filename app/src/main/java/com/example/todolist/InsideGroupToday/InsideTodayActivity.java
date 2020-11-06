package com.example.todolist.InsideGroupToday;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class InsideTodayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskListAdapter taskListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_today);
        initialize();
        showList(getResources().getString(R.string.json));
    }

    private void initialize(){
        recyclerView = findViewById(R.id.taskListRv);
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
