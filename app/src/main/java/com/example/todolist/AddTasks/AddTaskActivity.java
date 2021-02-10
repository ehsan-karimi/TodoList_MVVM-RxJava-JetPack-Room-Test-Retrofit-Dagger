package com.example.todolist.AddTasks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddTaskActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Button btn_chooseDate;
    private final Calendar now = Calendar.getInstance();
    private Intent intent;
    private int groupId;
    private int hour;
    private int minute;
    private String groupLabel;
    private AddTaskViewModel addTaskViewModel;
    private ExtendedFloatingActionButton extended_fab;
    private EditText ed_title;
    private Disposable disposable;
    private static final String TAG = "AddTaskActivity";
    private CheckBox repeat;
    private String groupCategory;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;

    @Inject
    public ViewModelFactory viewModelFactory_new;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        AndroidInjection.inject(this);
        initialize();
        setOnClick();
    }

    private void initialize() {
        repeat = findViewById(R.id.repeat);
        btn_chooseDate = findViewById(R.id.btn_chooseDate);
        extended_fab = findViewById(R.id.extended_fab);
        ed_title = findViewById(R.id.ed_title);
        intent = getIntent();
        groupId = intent.getIntExtra("groupId", 0);
        groupLabel = intent.getStringExtra("groupLabel");
        groupCategory = intent.getStringExtra("groupCategory");

        addTaskViewModel = new ViewModelProvider(this, viewModelFactory_new).get(AddTaskViewModel.class);
    }

    private void setOnClick() {
        btn_chooseDate.setOnClickListener(n -> {


            TimePickerDialog dpd = TimePickerDialog.newInstance(
                    this, now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
            );

            dpd.setVersion(TimePickerDialog.Version.VERSION_2);
            dpd.show(getSupportFragmentManager(), "Datepickerdialog");

            if (groupCategory.equals("Week")) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd2 = DatePickerDialog.newInstance(
                        AddTaskActivity.this,
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_WEEK, 7);
//                    for (int i = 0; i < 6; i++) {
//                        cal.add(Calendar.DAY_OF_WEEK, 1);
//                    }

//                    Calendar min = Calendar.getInstance();
//                    min.set(Calendar.DAY_OF_WEEK, 1);
                dpd2.setMinDate(now);
                dpd2.setMaxDate(cal);

                dpd2.show(getSupportFragmentManager(), "Datepickerdialog");
            } else if (groupCategory.equals("Month")) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd2 = DatePickerDialog.newInstance(
                        AddTaskActivity.this,
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

                dpd2.setMinDate(now);
                dpd2.setMaxDate(cal);

                dpd2.show(getSupportFragmentManager(), "Datepickerdialog");
            }


        });

        extended_fab.setOnClickListener(v -> {

            if (groupCategory.equals("Week") || groupCategory.equals("Month")) {
                if (ed_title.getText().toString().trim().length() > 0 && hour > 0 && groupId > 0 && groupLabel != null)
                    saveTask(ed_title.getText().toString(), addTaskViewModel.getDelayMilliSecond(hour, minute, year, monthOfYear, dayOfMonth));
                else
                    Toast.makeText(this, "Please enter the requested information", Toast.LENGTH_SHORT).show();

            } else if (groupCategory.equals("Day")) {
                if (ed_title.getText().toString().trim().length() > 0 && hour > 0 && groupId > 0 && groupLabel != null)
                    saveTask(ed_title.getText().toString(), addTaskViewModel.getDelayMilliSecond(hour, minute, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH));
                else
                    Toast.makeText(this, "Please enter the requested information", Toast.LENGTH_SHORT).show();

            }

        });
    }


    private void saveTask(String title, long delay) {

        Tasks tasks = new Tasks(groupId, title, delay, repeat.isChecked(), false, false, false, false);
        addTaskViewModel.saveTask(tasks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Long aLong) {

                        Toast.makeText(getApplicationContext(), "Insert Successfully", Toast.LENGTH_LONG).show();
                        addTaskViewModel.setScheduleNotification(title, groupLabel, R.drawable.ic_github, delay, repeat.isChecked(), addTaskViewModel.safeLongToInt(aLong), false);
                        getGroup(groupId);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getApplicationContext(), "Insert Failed!!!", Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void getGroup(int gpId) {
        addTaskViewModel.getGroup(gpId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Groups>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Groups groups) {
                        updateDonePercentGroup(groups);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }
                });
    }

    private void updateDonePercentGroup(Groups groups) {

        Groups groups1 = new Groups(groups.getId(), groups.getIcon(), groups.getLabel(), groups.getCategory(), groups.isSynced(), groups.isDeleted(), groups.isUpdated(), groups.getDonePercent(), groups.getTasksCount() + 1);
        addTaskViewModel.updateGroups(groups1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //       compositeDisposable.add(d);
                disposable = d;
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: updated successfully!!!");
                finish();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: ", e);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        disposable.dispose();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        this.hour = hourOfDay;
        this.minute = minute;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
    }
}
