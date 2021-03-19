package com.example.todolist.tasks;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.AddGroups.AddGroupViewModel;
import com.example.todolist.AddTasks.AddTaskActivity;
import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.Main.Today.TodayGroupsAdapter;
import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TasksActivity extends AppCompatActivity implements TasksAdapter.OnItemClicked {
    private RecyclerView recyclerView;
    private TasksAdapter tasksAdapter;
    private ExtendedFloatingActionButton extended_fab_add_task;
    private TasksViewModel tasksViewModel;
    private CompositeDisposable compositeDisposable;
    private TextView taskCount;
    private static final String TAG = "TasksActivity";

    @Inject
    public ViewModelFactory viewModelFactory_new;
    private Intent intent;
    private int groupId;
    private String groupLabel;
    private TextView group_Label;
    private TextView groupCategory;
    private String group_Category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_today);
        AndroidInjection.inject(this);
        initialize();
        setOnClick();
        showList();
    }

    private void initialize() {
        recyclerView = findViewById(R.id.taskListRv);
        extended_fab_add_task = findViewById(R.id.extended_fab_add_task);
        taskCount = findViewById(R.id.taskCount);
        groupCategory = findViewById(R.id.groupCategory);
        group_Label = findViewById(R.id.groupLabel);
        tasksViewModel = new ViewModelProvider(this, viewModelFactory_new).get(TasksViewModel.class);

        intent = getIntent();
        groupId = intent.getIntExtra("groupId", 0);
        groupLabel = intent.getStringExtra("groupLabel");
        group_Label.setText(groupLabel);
        group_Category = intent.getStringExtra("groupCategory");
        groupCategory.setText(group_Category);
    }

    private void setOnClick() {
        extended_fab_add_task.setOnClickListener(v -> {

            intent = new Intent(this, AddTaskActivity.class);
            intent.putExtra("groupId", groupId);
            intent.putExtra("groupLabel", groupLabel);
            intent.putExtra("groupCategory", group_Category);
            startActivity(intent);
        });
    }

    private void showList() {
        tasksViewModel.getTasks(groupId).observe(this, t -> {


            if (t.size() < 1) {
                //  emptyStateToday.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                tasksAdapter = new TasksAdapter(t);
                recyclerView.setAdapter(tasksAdapter);
                tasksAdapter.setOnClick(this);
                taskCount.setText(String.valueOf(tasksAdapter.getItemCount()) + " task");
                getGroup(groupId);
            }

        });

    }

    private void updateTask(Tasks tasks) {
        Tasks tasks1 = new Tasks(tasks.getId(), tasks.getGroupId(), tasks.getContent(), tasks.getDate(), tasks.isRepeatTask(), !tasks.isDone(), tasks.isSynced(), tasks.isDeleted(), tasks.isUpdated());
        tasksViewModel.updateTask(tasks1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                long time = System.currentTimeMillis();
                if (tasks1.getDate() > time)
                    tasksViewModel.setScheduleNotification(groupLabel, tasks1.getContent(), R.drawable.ic_github, tasks1.getDate(), tasks1.isRepeatTask(), tasks1.getId(), tasks1.isDone());
                else
                    tasksViewModel.setScheduleNotification(groupLabel, tasks1.getContent(), R.drawable.ic_github, tasks1.getDate(), tasks1.isRepeatTask(), tasks1.getId(), true);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: ", e);
            }
        });
    }

    private void getGroup(int gpId) {
        tasksViewModel.getGroup(gpId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Groups>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Groups groups) {
                        updateDonePercentGroup(groups, tasksAdapter.doneTasks());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }
                });
    }

    private void updateDonePercentGroup(Groups groups, int doneTasks) {
        int count = tasksAdapter.getItemCount();
        int percent = doneTasks * 100 / count;
        Groups groups1 = new Groups(groups.getId(), groups.getIcon(), groups.getLabel(), groups.getCategory(), groups.isSynced(), groups.isDeleted(), groups.isUpdated(), percent, groups.getTasksCount());
        tasksViewModel.updateGroups(groups1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //       compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: updated successfully!!!");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: ", e);
            }
        });
    }

    private void deleteTask(Tasks tasks) {
        Tasks tasks1 = new Tasks(tasks.getId(), tasks.getGroupId(), tasks.getContent(), tasks.getDate(), tasks.isRepeatTask(), tasks.isDone(), tasks.isSynced(), true, tasks.isUpdated());
        tasksViewModel.updateTask(tasks1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
//                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                Toast.makeText(TasksActivity.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                tasksViewModel.setScheduleNotification(groupLabel, tasks1.getContent(), R.drawable.ic_github, tasks1.getDate(), tasks1.isRepeatTask(), tasks1.getId(), true);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(TasksActivity.this, "Deleted unsuccessfully!!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError: ", e);
            }
        });
    }

    private void dialog(Tasks tasks) {
        new AlertDialog.Builder(this)
                .setTitle("Delete task")
                .setMessage("Are you sure you want to delete this task?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        deleteTask(tasks);
                    }
                })

//                .setNeutralButton("Edit task", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(this, AddTaskActivity)
//                    }
//                })


                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                //  .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onItemClick(Tasks tasks) {
        Log.e(TAG, "onItemClick: " + tasks.getId());
        updateTask(tasks);
    }

    @Override
    public void onItemLongClick(Tasks tasks) {
        dialog(tasks);
    }
}
