package com.example.todolist.AddTasks;

import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.Repositories.TasksRepository;
import com.example.todolist.Utils.ScheduleNotification;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class AddTaskViewModel extends ViewModel {

    @Inject
    public TasksRepository tasksRepository;

    @Inject
    public ScheduleNotification scheduleNotification;

    @Inject
    public AddTaskViewModel(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Single<Long> saveTask(Tasks tasks){
        return tasksRepository.saveTask(tasks);
    }

    public void setScheduleNotification(String title, String content, int iconId, long delay, boolean repeat, int taskId, boolean cancelAlarm){
        scheduleNotification.createNotificationChannel();
        scheduleNotification.scheduleNotification(scheduleNotification.getNotification(iconId, title, content), delay, repeat, taskId, cancelAlarm);
    }

    public long getDelayMilliSecond(int hour, int minute, int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                hour, minute, 0);

        return calendar.getTimeInMillis();
    }

    public Completable updateGroups(Groups groups){
        return tasksRepository.updateGroups(groups);
    }

    public Single<Groups> getGroup(int gpId){
        return tasksRepository.getGroup(gpId);
    }

    public int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

}
