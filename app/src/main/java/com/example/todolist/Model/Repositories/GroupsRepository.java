package com.example.todolist.Model.Repositories;

import androidx.lifecycle.LiveData;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.LocalDataSource.GroupsDao;
import com.example.todolist.Model.RemoteDataSource.Api_Interface;

import java.util.List;

import io.reactivex.Completable;

public class GroupsRepository {
    private GroupsDao groupsDao;
    private Api_Interface api_interface;

    public GroupsRepository(GroupsDao groupsDao, Api_Interface api_interface) {
        this.groupsDao = groupsDao;
        this.api_interface = api_interface;
    }


    public Completable refreshGroups() {
        return api_interface.get_GroupList().doOnSuccess(l->{groupsDao.insertGroupsList(l);}).ignoreElement();
    }

    public LiveData<List<Groups>> getGroups() {
        return groupsDao.getGroups();
    }

    public LiveData<List<Groups>> getGroupsToday(){
        return groupsDao.getGroupsToday();
    }

    public LiveData<List<Groups>> getGroupsWeek(){
        return groupsDao.getGroupsWeek();
    }

    public LiveData<List<Groups>> getGroupsMonth(){
        return groupsDao.getGroupsMonth();
    }

}
