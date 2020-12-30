package com.example.todolist.Di.Modules;

import android.content.Context;

import androidx.room.Room;

import com.example.todolist.Model.LocalDataSource.GroupsDao;
import com.example.todolist.Model.LocalDataSource.RoomConfig.PersonDatabase;
import com.example.todolist.Model.LocalDataSource.TasksDao;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    private static final String DB_NAME = "person_db";

    @Singleton
    @Provides
    public PersonDatabase provideDataBase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(), PersonDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    GroupsDao provideGroupsDao(PersonDatabase db) { return db.groupsDao(); }

    @Singleton
    @Provides
    TasksDao provideTasksDao(PersonDatabase db) { return db.tasksDao(); }

}
