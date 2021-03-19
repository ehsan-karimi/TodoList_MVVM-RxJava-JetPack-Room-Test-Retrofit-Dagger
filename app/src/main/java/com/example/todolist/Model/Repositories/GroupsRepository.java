package com.example.todolist.Model.Repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.LocalDataSource.GroupsDao;
import com.example.todolist.Model.LocalDataSource.TasksDao;
import com.example.todolist.Model.RemoteDataSource.Api_Interface;

import com.example.todolist.Utils.SharedPreference;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class GroupsRepository {
    private GroupsDao groupsDao;
    private Api_Interface api_interface;
    private TasksDao tasksDao;

    @Inject
    public SharedPreference sharedPreference;

    public GroupsRepository(GroupsDao groupsDao, Api_Interface api_interface, TasksDao tasksDao) {
        this.groupsDao = groupsDao;
        this.api_interface = api_interface;
        this.tasksDao = tasksDao;
    }

    public Completable refreshGroups() {
        return api_interface.get_GroupList().doOnSuccess(l -> {
            groupsDao.insertGroupsList(l);
        }).ignoreElement();
    }

    public LiveData<List<Groups>> getGroups() {
        return groupsDao.getGroups();
    }

    public LiveData<List<Groups>> getGroupsToday() {
        return groupsDao.getGroupsToday();
    }

    public Completable updateTask(Tasks tasks){
        return tasksDao.updateTasks(tasks);
    }

    public Completable updateTask2(List<Tasks> tasks){
        return tasksDao.updateTasks2(tasks);
    }

    public LiveData<List<Tasks>> getTasks(int gpId){
        return tasksDao.getTasks(gpId);
    }

    public LiveData<List<Groups>> getGroupsWeek() {
        return groupsDao.getGroupsWeek();
    }

    public LiveData<List<Groups>> getGroupsMonth() {
        return groupsDao.getGroupsMonth();
    }

    public Completable saveGroup(Groups groups) {
        return groupsDao.insertGroups(groups);
    }

    public Single<Groups> getLastGroup() {
        return groupsDao.getLastGroup();
    }

    public Completable syncGroup(JsonObject groups) {
        return api_interface.add_Group(groups).ignoreElement();
    }

    public Completable updateGroup(Groups group){
        return groupsDao.updateGroup(group);
    }

    public LiveData<List<Tasks>> getTasksTest() {
        return groupsDao.getTasksTest();
    }

//    public Boolean saveDataSharedPreferences(String key, String value) {
//        return sharedPreference.save(key, value);
//    }
//
//    public String getDataSharedPreferences(String key, Context s){
//        return sharedPreference.get(key, s);
//    }

}
