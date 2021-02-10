package com.example.todolist.Model.Repositories;

import androidx.lifecycle.LiveData;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.LocalDataSource.TasksDao;
import com.example.todolist.Model.RemoteDataSource.Api_Interface;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


public class TasksRepository {
    private TasksDao tasksDao;
    private Api_Interface api_interface;

    public TasksRepository(TasksDao tasksDao, Api_Interface api_interface) {
        this.tasksDao = tasksDao;
        this.api_interface = api_interface;
    }

    public Completable refreshTasks() {
        return api_interface.get_TasksList().doOnSuccess(l -> {
            tasksDao.insertTasksList(l);
        }).ignoreElement();
    }

    public LiveData<List<Tasks>> getAllTasks() {
        return tasksDao.getAllTasks();
    }

    public Observable<List<Tasks>> getAllTasksRx() {
        return tasksDao.getAllTasksRx();
    }

    public LiveData<List<Tasks>> getTasks(int groupId) {
        return tasksDao.getTasks(groupId);
    }

    public Single<Long> saveTask(Tasks tasks) {
        return tasksDao.insertTasks(tasks);
    }

    public Completable syncTasks(JsonObject tasks) {
        return api_interface.add_Task(tasks).ignoreElement();
    }

    public Completable updateTask(Tasks tasks){
        return tasksDao.updateTasks(tasks);
    }

    public Completable updateGroups(Groups groups){
        return tasksDao.updateGroups(groups);
    }

    public Single<Groups> getGroup(int gpId){
        return tasksDao.getGroup(gpId);
    }
}
