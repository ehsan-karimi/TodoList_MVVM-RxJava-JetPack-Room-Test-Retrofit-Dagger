package com.example.todolist.Main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Repositories.GroupsRepository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GroupsViewModel extends ViewModel {

    private MutableLiveData<String> error = new MutableLiveData<>();
    private Disposable disposable;
    private GroupsRepository groupsRepository;

    public GroupsViewModel(GroupsRepository groupsRepository) {
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

    public LiveData<List<Groups>> getGroupsToday() {
        return groupsRepository.getGroupsToday();
    }

    public LiveData<List<Groups>> getGroupsWeek() {
        return groupsRepository.getGroupsWeek();
    }

    public LiveData<List<Groups>> getGroupsMonth() {
        return groupsRepository.getGroupsMonth();
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
