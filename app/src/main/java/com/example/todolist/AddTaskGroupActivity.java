package com.example.todolist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class AddTaskGroupActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private IconListAdapter iconListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_group);
        initialize();
        showList(getResources().getString(R.string.icon));
    }

    private void initialize() {
        recyclerView = findViewById(R.id.iconList);
    }

    private void showList(String result) {
        java.util.List<IconListModel> iconListModels = new Gson().fromJson(result, new TypeToken<List<IconListModel>>() {
        }.getType());

        recyclerView.setLayoutManager(new LinearLayoutManager(AddTaskGroupActivity.this, RecyclerView.VERTICAL, false));
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(InsideTodayActivity.this, 2);
//        recyclerView.setLayoutManager(mLayoutManager);
        iconListAdapter = new IconListAdapter(iconListModels);
        recyclerView.setAdapter(iconListAdapter);
    }
}
