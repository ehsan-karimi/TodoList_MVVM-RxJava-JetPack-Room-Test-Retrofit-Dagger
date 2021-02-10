package com.example.todolist.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.Model.Repositories.TasksRepository;
import com.example.todolist.Utils.ScheduleNotification;
import com.example.todolist.Utils.SharedPreference;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class TasksViewModel extends ViewModel {

    @Inject
    public TasksRepository tasksRepository;

    @Inject
    public ScheduleNotification scheduleNotification;

    @Inject
    public TasksViewModel(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Completable updateTask(Tasks tasks){
        return tasksRepository.updateTask(tasks);
    }

    public Completable updateGroups(Groups groups){
        return tasksRepository.updateGroups(groups);
    }

    public Single<Groups> getGroup(int gpId){
        return tasksRepository.getGroup(gpId);
    }

    public LiveData<List<Tasks>> getTasks(int gpId) {
        return tasksRepository.getTasks(gpId);
    }

    public void setScheduleNotification(String title, String content, int iconId, long delay, boolean repeat, int taskId, boolean cancelAlarm){
        scheduleNotification.createNotificationChannel();
        scheduleNotification.scheduleNotification(scheduleNotification.getNotification(iconId, title, content), delay, repeat, taskId, cancelAlarm);
    }
}
