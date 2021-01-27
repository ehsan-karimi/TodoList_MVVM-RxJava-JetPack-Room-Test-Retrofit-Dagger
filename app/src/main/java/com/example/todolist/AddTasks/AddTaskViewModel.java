package com.example.todolist.AddTasks;

import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.Repositories.TasksRepository;
import com.example.todolist.Utils.ScheduleNotification;

import javax.inject.Inject;

import io.reactivex.Completable;

public class AddTaskViewModel extends ViewModel {

    @Inject
    public TasksRepository tasksRepository;

    @Inject
    public ScheduleNotification scheduleNotification;

    @Inject
    public AddTaskViewModel(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Completable saveTask(Tasks tasks){
        return tasksRepository.saveTask(tasks);
    }

    public void setScheduleNotification(String title, String content, int iconId, long delay){
        scheduleNotification.createNotificationChannel();
        scheduleNotification.scheduleNotification(scheduleNotification.getNotification(iconId, title, content), delay);
    }

}
