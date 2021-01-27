package com.example.todolist.AddTasks;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.AddGroups.AddTaskGroupViewModel;
import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.Repositories.TasksRepository;
import com.example.todolist.R;
import com.example.todolist.Utils.ScheduleNotification;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddTaskActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private Button btn_chooseDate;
    private final Calendar now = Calendar.getInstance();
    private Intent intent;
    private long groupId;
    private int hour;
    private int minute;
    private AddTaskViewModel addTaskViewModel;
    private ExtendedFloatingActionButton extended_fab;
    private EditText ed_title;

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

    private void initialize(){
        btn_chooseDate = findViewById(R.id.btn_chooseDate);
        extended_fab = findViewById(R.id.extended_fab);
        ed_title = findViewById(R.id.ed_title);
        intent = getIntent();
        groupId = intent.getLongExtra("groupId",0);
    }

    private void setOnClick(){
        btn_chooseDate.setOnClickListener(n ->{

            TimePickerDialog dpd = TimePickerDialog.newInstance(
                    this,now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
            );

            dpd.setVersion(TimePickerDialog.Version.VERSION_2);
            dpd.show(getSupportFragmentManager(), "Datepickerdialog");
        });

        extended_fab.setOnClickListener(v ->{
            if (ed_title.getText().toString().trim().length() > 0 && hour > 0 && groupId > 0)
            saveTask(ed_title.getText().toString(), getDelayMilliSecond());
        });
    }

    private long getDelayMilliSecond(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                hour, minute, 0);
        long startTime = calendar.getTimeInMillis();
        Log.e("TAG", "getDelayMilliSecond: " + String.valueOf(startTime) );
        long futureInMillis = SystemClock.elapsedRealtime();
        Log.e("TAG", "current: " + String.valueOf(futureInMillis) );
        return calendar.getTimeInMillis();
    }

    private void saveTask(String title, long delay){
        Tasks tasks = new Tasks(groupId, title, String.valueOf(delay), false);
        addTaskViewModel = new ViewModelProvider(this, viewModelFactory_new).get(AddTaskViewModel.class);
        addTaskViewModel.saveTask(tasks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //  disposable = d;
                     //   compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(), "Insert Successfully", Toast.LENGTH_LONG).show();
                        addTaskViewModel.setScheduleNotification(title, "test", R.drawable.ic_github, delay);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(getApplicationContext(), "Insert Failed!!!", Toast.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        this.hour = hourOfDay;
        this.minute = minute;
    }
}
