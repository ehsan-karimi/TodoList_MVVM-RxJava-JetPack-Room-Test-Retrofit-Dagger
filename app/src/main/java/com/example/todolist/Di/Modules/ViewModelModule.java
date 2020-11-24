package com.example.todolist.Di.Modules;

import androidx.lifecycle.ViewModel;

import com.example.todolist.AddGroups.AddTaskGroupViewModel;
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

//    @Binds
//    @ViewModelKey(GroupsViewModel.class)
//    @IntoMap
//    abstract ViewModel provideGroupsViewModel(GroupsViewModel groupsViewModel,int s);

}
