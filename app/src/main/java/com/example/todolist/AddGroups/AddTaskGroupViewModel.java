package com.example.todolist.AddGroups;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Groups;
import com.example.todolist.Model.GroupsRepository;
import com.example.todolist.Network.Api_Interface;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddTaskGroupViewModel extends ViewModel {

    private Api_Interface api_interface;
    private MutableLiveData<List<Groups>> groups = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public AddTaskGroupViewModel(Api_Interface api_interface) {
        this.api_interface = api_interface;

        api_interface.get_GroupList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Groups>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Groups> groupsList) {
                        groups.setValue(groupsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(e.getMessage());
                    }
                });
    }

    public LiveData<List<Groups>> getGroups() {
        return groups;
    }

    public LiveData<String> getError() {
        return error;
    }
}
