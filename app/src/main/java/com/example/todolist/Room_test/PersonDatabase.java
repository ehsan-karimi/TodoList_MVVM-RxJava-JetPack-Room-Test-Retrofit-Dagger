package com.example.todolist.Room_test;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolist.Model.Groups;
import com.example.todolist.Model.GroupsDao;

@Database(entities = {Person.class,Groups.class}, exportSchema = false, version = 1)
public abstract class PersonDatabase extends RoomDatabase {
    private static final String DB_NAME = "person_db";
    private static PersonDatabase instance;

    public static PersonDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract PersonDao personDao();

    public abstract GroupsDao groupsDao();
}
