package com.example.todolist.AddGroups;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Repositories.GroupsRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddTaskGroupViewModel extends ViewModel {

    private MutableLiveData<String> error = new MutableLiveData<>();
    private GroupsRepository groupsRepository;

    public AddTaskGroupViewModel(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    public Completable saveGroup(Groups groups) {
        return groupsRepository.saveGroup(groups).doOnError(e->{error.postValue("Failed to insert");});
    }

    public LiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
