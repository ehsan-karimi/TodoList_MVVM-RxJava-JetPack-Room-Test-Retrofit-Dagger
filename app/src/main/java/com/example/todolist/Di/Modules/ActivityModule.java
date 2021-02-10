package com.example.todolist.Di.Modules;

import com.example.todolist.AddGroups.AddGroupActivity;
import com.example.todolist.AddTasks.AddTaskActivity;
import com.example.todolist.Di.Scopes.ActivityScope;
import com.example.todolist.Di.Scopes.FragmentScope;
import com.example.todolist.Main.MainActivity;
import com.example.todolist.Main.Month.MonthFragment;
import com.example.todolist.Main.Today.TodayFragment;
import com.example.todolist.Main.Week.WeekFragment;
import com.example.todolist.entry.EntryActivity;
import com.example.todolist.tasks.TasksActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddGroupActivity addTaskGroupActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract AddTaskActivity provideAddTaskActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract EntryActivity provideEntryActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract TasksActivity provideTasksActivity();

    @FragmentScope
    @ContributesAndroidInjector
    abstract WeekFragment weekFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract MonthFragment monthFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract TodayFragment todayFragment();
}
