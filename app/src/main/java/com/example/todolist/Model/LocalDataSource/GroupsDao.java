package com.example.todolist.Model.LocalDataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface GroupsDao {
    @Query("SELECT * FROM tbl_groups")
    LiveData<List<Groups>> getGroups();

    @Query("SELECT * FROM tbl_groups WHERE category = 'Today' AND deleted = 0 ORDER BY id DESC")
    LiveData<List<Groups>> getGroupsToday();

    @Query("SELECT * FROM tbl_groups WHERE category = 'Week' AND deleted = 0 ORDER BY id DESC")
    LiveData<List<Groups>> getGroupsWeek();

    @Query("SELECT * FROM tbl_groups WHERE category = 'Month' AND deleted = 0 ORDER BY id DESC")
    LiveData<List<Groups>> getGroupsMonth();

    @Query("SELECT * FROM tbl_groups ORDER BY id DESC LIMIT 1")
    Single<Groups> getLastGroup();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGroupsList(List<Groups> groups);

    @Insert()
    Completable insertGroups(Groups groups);

    @Update
    Completable updateGroup(Groups groups);

    @Query("SELECT * FROM tbl_tasks")
    LiveData<List<Tasks>> getTasksTest();

    @Delete
    int deleteGroups(Groups groups);

}
