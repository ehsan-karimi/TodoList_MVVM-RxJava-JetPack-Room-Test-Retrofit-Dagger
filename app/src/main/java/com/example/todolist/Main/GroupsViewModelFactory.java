package com.example.todolist.Main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.Main.GroupsViewModel;
import com.example.todolist.Model.Repositories.GroupsRepository;

public class GroupsViewModelFactory implements ViewModelProvider.Factory {

    private GroupsRepository groupsRepository;

    public GroupsViewModelFactory(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GroupsViewModel(groupsRepository);
    }
}

