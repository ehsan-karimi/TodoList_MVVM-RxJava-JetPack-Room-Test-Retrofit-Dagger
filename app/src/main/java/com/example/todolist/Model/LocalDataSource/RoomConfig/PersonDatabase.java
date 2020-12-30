package com.example.todolist.Model.LocalDataSource.RoomConfig;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.LocalDataSource.GroupsDao;
import com.example.todolist.Model.LocalDataSource.TasksDao;

@Database(entities = {Groups.class, Tasks.class}, exportSchema = false, version = 2)
public abstract class PersonDatabase extends RoomDatabase {
//    private static final String DB_NAME = "person_db";
//    private static PersonDatabase instance;
//
//    public static PersonDatabase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, DB_NAME)
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return instance;
//    }

    public abstract GroupsDao groupsDao();
    public abstract TasksDao tasksDao();
}
