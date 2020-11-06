package com.example.todolist.AddGroups;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.Model.Repositories.GroupsRepository;

public class AddTaskGroupViewModelFactory implements ViewModelProvider.Factory {

    private GroupsRepository groupsRepository;

    public AddTaskGroupViewModelFactory(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskGroupViewModel(groupsRepository);
    }
}
