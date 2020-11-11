package com.example.todolist.Di.Modules;

import androidx.fragment.app.Fragment;

import com.example.todolist.AddGroups.AddTaskGroupActivity;
import com.example.todolist.Main.MainActivity;
import com.example.todolist.Main.Month.MonthFragment;
import com.example.todolist.Main.Today.TodayFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity();

    @ContributesAndroidInjector
    abstract AddTaskGroupActivity addTaskGroupActivity();

//    @ContributesAndroidInjector
//    abstract MonthFragment monthFragment();
//
    @ContributesAndroidInjector
    abstract TodayFragment todayFragment();
}
