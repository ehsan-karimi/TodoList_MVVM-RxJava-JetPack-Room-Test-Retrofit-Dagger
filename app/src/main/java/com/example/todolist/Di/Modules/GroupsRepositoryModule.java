package com.example.todolist.Di.Modules;

import com.example.todolist.Model.LocalDataSource.GroupsDao;
import com.example.todolist.Model.RemoteDataSource.Api_Interface;
import com.example.todolist.Model.Repositories.GroupsRepository;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DataBaseModule.class, RetrofitModule.class})
public class GroupsRepositoryModule {

    private GroupsRepository groupsRepository;

    @Provides
    public GroupsRepository getGroupsRepository(GroupsDao groupsDao, Api_Interface api_interface) {
        return groupsRepository = new GroupsRepository(groupsDao, api_interface);
    }
}
