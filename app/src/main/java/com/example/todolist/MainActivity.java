package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        showList(getResources().getString(R.string.json));
    }

    private void initialize() {
        recyclerView = findViewById(R.id.taskListRv);
    }

    private void showList(String result) {
        java.util.List<TaskEntity> taskEntityList = new Gson().fromJson(result, new TypeToken<List<TaskEntity>>() {
        }.getType());

      //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        taskAdapter = new TaskAdapter(taskEntityList);
        recyclerView.setAdapter(taskAdapter);
    }

}