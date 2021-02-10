package com.example.todolist.AddGroups;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.Main.Today.TodayGroupsAdapter;
import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Icons;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.R;
import com.example.todolist.entry.EntryActivity;
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

public class AddGroupActivity extends AppCompatActivity implements IconListAdapter.OnItemClicked {
    private RecyclerView recyclerView;
    private IconListAdapter iconListAdapter;
    private EditText ed_label;
    private ExtendedFloatingActionButton extended_fab;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private int icon = 0;
    private String category;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private AddGroupViewModel addGroupViewModel;
    private ProgressBar progress_circular;
    private ImageButton ib_back;


    @Inject
    public GroupsRepository groupsRepository;

    @Inject
    public ViewModelFactory viewModelFactory_new;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_group);
        AndroidInjection.inject(this);
        initialize();
        showList();
    }

    private void initialize() {
        recyclerView = findViewById(R.id.iconList);
        ed_label = findViewById(R.id.ed_label);
        extended_fab = findViewById(R.id.extended_fab);
        progress_circular = findViewById(R.id.progress_circular);
        ib_back = findViewById(R.id.ib_back);

        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    radioButton = (RadioButton) findViewById(checkedId);
                    category = radioButton.getText().toString();
                }
        );

        ib_back.setOnClickListener(v -> {
            finish();
        });

        extended_fab.setOnClickListener(v -> {
            if (icon != 0 && ed_label.getText().length() > 0 && category != null) {
                progress_circular.setVisibility(View.VISIBLE);
                saveGroup(category);
            } else {
                Toast.makeText(getApplicationContext(), "Please enter the requested information", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void showList() {
        String result = getResources().getString(R.string.icon);
        java.util.List<Icons> icons = new Gson().fromJson(result, new TypeToken<List<Icons>>() {
        }.getType());

//        recyclerView.setLayoutManager(new LinearLayoutManager(AddTaskGroupActivity.this, RecyclerView.VERTICAL, false));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AddGroupActivity.this, 5);
        recyclerView.setLayoutManager(mLayoutManager);
        iconListAdapter = new IconListAdapter(icons);
        recyclerView.setAdapter(iconListAdapter);

        iconListAdapter.setOnClick(this);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    private void saveGroup(String cat) {


        Groups groups = new Groups(icon, ed_label.getText().toString(), cat, false, false, false, 0, 0);

        //  viewModelFactory = new AddTaskGroupViewModelFactory(groupsRepository);
        addGroupViewModel = new ViewModelProvider(this, viewModelFactory_new).get(AddGroupViewModel.class);

//        addGroupViewModel.getTasksTest().observe(this, t -> {
//
//         Toast.makeText(this, String.valueOf(t.size()), Toast.LENGTH_LONG).show();
//
//        });

        addGroupViewModel.saveGroup(groups)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //  disposable = d;
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {

//                        if (isNetworkConnected()) {
//                            getLastGroup();
//                        } else {
//                            progress_circular.setVisibility(View.GONE);
//                            Toast.makeText(getApplicationContext(), "Insert Successfully, You Can Sync Later", Toast.LENGTH_LONG).show();
//                            finish();
//                        }

                        checkInternet();

                    }

                    @Override
                    public void onError(Throwable e) {
                        progress_circular.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Insert Failed!!!", Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void getLastGroup() {

        addGroupViewModel.getLastGroup()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Groups>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Groups groups) {
                        syncGroup(groups);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progress_circular.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Insert Successfully, You Can Sync Later", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });


    }

    private void syncGroup(Groups groups) {

        addGroupViewModel.syncGroup(groups)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
//                        progress_circular.setVisibility(View.GONE);
//                        Toast.makeText(getApplicationContext(), "Insert Successfully, Sync Successfully", Toast.LENGTH_LONG).show();
//                        finish();
                        updateSyncedGroup(groups);
                    }

                    @Override
                    public void onError(Throwable e) {
                        progress_circular.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Insert Successfully, You Can Sync Later", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    private void updateSyncedGroup(Groups groups){
        Groups groups1 = new Groups(groups.getId(), groups.getIcon(), groups.getLabel(), groups.getCategory(), true, false, false, 0, 0);
        addGroupViewModel.updateGroup(groups1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        progress_circular.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Insert Successfully, Sync Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        progress_circular.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Insert Successfully, You Can Sync Later", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    private void checkInternet() {
        addGroupViewModel.isInternetWorking().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                getLastGroup();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                progress_circular.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Insert Successfully, You Can Sync Later", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @Override
    public void onItemClick(int id) {
        icon = id;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
