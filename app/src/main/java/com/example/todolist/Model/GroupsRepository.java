package com.example.todolist.Model;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Model.Room.GroupsDao;
import com.example.todolist.Network.Api_Interface;
import com.example.todolist.Network.Api_Service;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GroupsRepository {
    private GroupsDao groupsDao;
    private Api_Interface api_interface;

    public GroupsRepository(GroupsDao groupsDao, Api_Interface api_interface) {
        this.groupsDao = groupsDao;
        this.api_interface = api_interface;
    }


    public void RefreshGroups() {
        api_interface.get_GroupList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Groups>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Groups> groupsList) {
                        groupsDao.insertGroupsList(groupsList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public LiveData<List<Groups>> GetGroups() {
        return groupsDao.getGroups();
    }

}
