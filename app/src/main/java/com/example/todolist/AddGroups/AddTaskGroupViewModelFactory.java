package com.example.todolist.AddGroups;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.Model.Repositories.GroupsRepository;

import javax.inject.Inject;

public class AddTaskGroupViewModelFactory implements ViewModelProvider.Factory {

    private GroupsRepository groupsRepository;

   // @Inject
    public AddTaskGroupViewModelFactory(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskGroupViewModel(groupsRepository);
    }
}
