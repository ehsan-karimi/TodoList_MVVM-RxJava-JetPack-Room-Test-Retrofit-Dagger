package com.example.todolist.Model.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.Model.Groups;
import com.example.todolist.Room_test.Person;

import java.util.List;

@Dao
public interface GroupsDao {
    @Query("SELECT * FROM tbl_groups")
    LiveData<List<Groups>> getGroups();

    @Insert()
    void insertGroupsList(List<Groups> groups);

    @Insert()
    void insertGroups(Groups groups);

    @Update
    int updateGroups(Groups groups);

    @Delete
    int deleteGroups(Groups groups);
}
