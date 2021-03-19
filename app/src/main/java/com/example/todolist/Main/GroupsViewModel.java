package com.example.todolist.Main;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.R;
import com.example.todolist.Utils.ScheduleNotification;
import com.example.todolist.Utils.SharedPreference;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GroupsViewModel extends ViewModel {

//    private MutableLiveData<String> error = new MutableLiveData<>();
//    private Disposable disposable;
    @Inject
    public GroupsRepository groupsRepository;

    @Inject
    public ScheduleNotification scheduleNotification;

    @Inject
    public SharedPreference sharedPreference;

    @Inject
    public GroupsViewModel(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;

    }

    public Completable sync(){
       return groupsRepository.refreshGroups();
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

    public void setScheduleNotification(String title, String content, int iconId, long delay, boolean repeat, int taskId, boolean cancelAlarm){
        scheduleNotification.createNotificationChannel();
        scheduleNotification.scheduleNotification(scheduleNotification.getNotification(iconId, title, content), delay, repeat, taskId, cancelAlarm);
    }

    public LiveData<List<Groups>> getGroupsToday() {
        return groupsRepository.getGroupsToday();
    }

    public Completable updateTask(Tasks tasks){
        return groupsRepository.updateTask(tasks);
    }

    public Completable updateTask2(List<Tasks> tasks){
        return groupsRepository.updateTask2(tasks);
    }

    public LiveData<List<Tasks>> getTasks(int gpId){
        return groupsRepository.getTasks(gpId);
    }

    public LiveData<List<Groups>> getGroupsWeek() {
        return groupsRepository.getGroupsWeek();
    }

    public LiveData<List<Groups>> getGroupsMonth() {
        return groupsRepository.getGroupsMonth();
    }

//    public LiveData<String> getError() {
//        return error;
//    }

    public Boolean saveDataSharedPreferences(String key, String value) {
        return sharedPreference.save(key, value);
    }

    public String getDataSharedPreferences(String key) {
        return sharedPreference.get(key);
    }

    public Completable updateGroup(Groups groups){
        return groupsRepository.updateGroup(groups);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
//        disposable.dispose();
    }
}
