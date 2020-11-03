package com.example.todolist.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.Room_test.Person;

import java.util.List;

@Dao
public interface GroupsDao {
    @Query("SELECT * FROM tbl_groups")
    LiveData<List<Person>> getGroups();

    @Insert()
    void insertGroups(Groups groups);

    @Update
    int updateGroups(Groups groups);

    @Delete
    void deleteGroups(Groups groups);
}
