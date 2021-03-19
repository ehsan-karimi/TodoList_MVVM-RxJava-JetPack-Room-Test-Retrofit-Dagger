package com.example.todolist.Di.Modules;

import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.LocalDataSource.GroupsDao;
import com.example.todolist.Model.LocalDataSource.TasksDao;
import com.example.todolist.Model.RemoteDataSource.Api_Interface;
import com.example.todolist.Model.Repositories.GroupsRepository;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DataBaseModule.class, RetrofitModule.class})
public class GroupsRepositoryModule {

    @Provides
    public GroupsRepository getGroupsRepository(GroupsDao groupsDao, Api_Interface api_interface, TasksDao tasksDao) {
        return new GroupsRepository(groupsDao, api_interface, tasksDao);
    }
}
