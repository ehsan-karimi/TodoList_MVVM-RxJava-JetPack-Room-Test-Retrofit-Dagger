package com.example.todolist.Di.Modules;

import com.example.todolist.Model.LocalDataSource.GroupsDao;
import com.example.todolist.Model.LocalDataSource.TasksDao;
import com.example.todolist.Model.RemoteDataSource.Api_Interface;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.Model.Repositories.TasksRepository;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DataBaseModule.class, RetrofitModule.class})
public class TasksRepositoryModule {
    private TasksRepository tasksRepository;

    @Provides
    public TasksRepository getTasksRepository(TasksDao tasksDao, Api_Interface api_interface) {
        return tasksRepository = new TasksRepository(tasksDao, api_interface);
    }
}
