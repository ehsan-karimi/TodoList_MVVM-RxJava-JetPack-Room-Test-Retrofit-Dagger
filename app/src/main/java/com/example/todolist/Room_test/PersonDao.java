package com.example.todolist.Room_test;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getPerson();

    @Insert()
    void insertPerson(Person person);

    @Update
    int updatePerson(Person person);

    @Delete
    void deletePerson(Person person);
}
