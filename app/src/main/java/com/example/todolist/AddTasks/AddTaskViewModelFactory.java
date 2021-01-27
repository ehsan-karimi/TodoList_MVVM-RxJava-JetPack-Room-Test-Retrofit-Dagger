package com.example.todolist.AddTasks;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.Model.Repositories.TasksRepository;

public class AddTaskViewModelFactory implements ViewModelProvider.Factory {

    private TasksRepository tasksRepository;

    // @Inject
    public AddTaskViewModelFactory(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskViewModel(tasksRepository);
    }
}

