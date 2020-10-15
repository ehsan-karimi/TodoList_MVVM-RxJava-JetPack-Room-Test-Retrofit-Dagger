package com.example.todolist.Model.LocalDataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.Model.Entities.Groups;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface GroupsDao {
    @Query("SELECT * FROM tbl_groups")
    LiveData<List<Groups>> getGroups();

    @Query("SELECT * FROM tbl_groups WHERE category = 'Today'")
    LiveData<List<Groups>> getGroupsToday();

    @Query("SELECT * FROM tbl_groups WHERE category = 'Week'")
    LiveData<List<Groups>> getGroupsWeek();

    @Query("SELECT * FROM tbl_groups WHERE category = 'Month'")
    LiveData<List<Groups>> getGroupsMonth();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGroupsList(List<Groups> groups);

    @Insert()
    Completable insertGroups(Groups groups);

    @Update
    int updateGroups(Groups groups);

    @Delete
    int deleteGroups(Groups groups);
}
