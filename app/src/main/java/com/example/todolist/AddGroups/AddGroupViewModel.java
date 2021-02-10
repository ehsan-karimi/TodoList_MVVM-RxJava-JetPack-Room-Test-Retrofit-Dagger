package com.example.todolist.AddGroups;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class AddGroupViewModel extends ViewModel {

    @Inject
    public GroupsRepository groupsRepository;

    @Inject
    public AddGroupViewModel(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    public Completable saveGroup(Groups groups) {
        return groupsRepository.saveGroup(groups);
    }

    public Single<Groups> getLastGroup(){
        return groupsRepository.getLastGroup();
    }

    public Completable syncGroup(Groups groups){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("icon", groups.getIcon());
        jsonObject.addProperty("label", groups.getLabel());
        jsonObject.addProperty("category", groups.getCategory());
        return groupsRepository.syncGroup(jsonObject);
    }

    public Single<Boolean> isInternetWorking() {
        return Single.fromCallable(() -> {
            try {
                // Connect to Google DNS to check for connection
                int timeoutMs = 1500;
                Socket socket = new Socket();
                InetSocketAddress socketAddress = new InetSocketAddress("8.8.8.8", 53);

                socket.connect(socketAddress, timeoutMs);
                socket.close();

                return true;
            } catch (IOException e) {
                return false;
            }
        });
    }

    public Completable updateGroup(Groups group){
        return groupsRepository.updateGroup(group);
    }

    public LiveData<List<Tasks>> getTasksTest() {
        return groupsRepository.getTasksTest();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
