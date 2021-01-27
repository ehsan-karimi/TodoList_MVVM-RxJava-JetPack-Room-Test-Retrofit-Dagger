package com.example.todolist.Di.Modules;

import androidx.lifecycle.ViewModel;

import com.example.todolist.AddGroups.AddTaskGroupViewModel;
import com.example.todolist.AddTasks.AddTaskViewModel;
import com.example.todolist.Di.Keys.ViewModelKey;
import com.example.todolist.Main.GroupsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @ViewModelKey(AddTaskGroupViewModel.class)
    @IntoMap
    abstract ViewModel provideAddTaskGroupViewModel(AddTaskGroupViewModel addTaskGroupViewModel);

    @Binds
    @ViewModelKey(AddTaskViewModel.class)
    @IntoMap
    abstract ViewModel provideAddTaskViewModel(AddTaskViewModel addTaskViewModel);

//    @Binds
//    @ViewModelKey(GroupsViewModel.class)
//    @IntoMap
//    abstract ViewModel provideGroupsViewModel(GroupsViewModel groupsViewModel,int s);

}
