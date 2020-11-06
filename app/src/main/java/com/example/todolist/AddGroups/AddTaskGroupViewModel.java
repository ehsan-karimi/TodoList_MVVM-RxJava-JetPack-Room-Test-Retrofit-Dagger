package com.example.todolist.AddGroups;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Repositories.GroupsRepository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddTaskGroupViewModel extends ViewModel {

    private MutableLiveData<String> error = new MutableLiveData<>();
    private Disposable disposable;
    private GroupsRepository groupsRepository;

    public AddTaskGroupViewModel(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
        groupsRepository.refreshGroups()
                .subscribeOn(Schedulers.io())
       //         .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        error.postValue(e.getMessage());
                    }
                });
    }

    public LiveData<List<Groups>> getGroups() {
        return groupsRepository.getGroups();
    }

    public LiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
