package com.example.todolist.Di.Modules;

import androidx.lifecycle.ViewModel;

import com.example.todolist.AddGroups.AddGroupViewModel;
import com.example.todolist.AddTasks.AddTaskViewModel;
import com.example.todolist.Di.Keys.ViewModelKey;
import com.example.todolist.Main.GroupsViewModel;
import com.example.todolist.entry.EntryViewModel;
import com.example.todolist.tasks.TasksViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @ViewModelKey(AddGroupViewModel.class)
    @IntoMap
    abstract ViewModel provideAddTaskGroupViewModel(AddGroupViewModel addGroupViewModel);

    @Binds
    @ViewModelKey(AddTaskViewModel.class)
    @IntoMap
    abstract ViewModel provideAddTaskViewModel(AddTaskViewModel addTaskViewModel);

    @Binds
    @ViewModelKey(GroupsViewModel.class)
    @IntoMap
    abstract ViewModel provideGroupsViewModel(GroupsViewModel groupsViewModel);

    @Binds
    @ViewModelKey(EntryViewModel.class)
    @IntoMap
    abstract ViewModel provideEntryViewModel(EntryViewModel entryViewModel);

    @Binds
    @ViewModelKey(TasksViewModel.class)
    @IntoMap
    abstract ViewModel provideTasksViewModel(TasksViewModel tasksViewModel);

}
