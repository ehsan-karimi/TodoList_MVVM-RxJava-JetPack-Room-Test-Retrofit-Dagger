package com.example.todolist.Di.Components;

import android.content.Context;

import com.example.todolist.Di.DiApplication;
import com.example.todolist.Di.Modules.ActivityModule;

import com.example.todolist.Di.Modules.BroadcastReceiverModule;
import com.example.todolist.Di.Modules.DataBaseModule;
import com.example.todolist.Di.Modules.EntryModule;
import com.example.todolist.Di.Modules.GroupsRepositoryModule;
import com.example.todolist.Di.Modules.RetrofitModule;
import com.example.todolist.Di.Modules.ScheduleNotificationModule;
import com.example.todolist.Di.Modules.SharedPreferenceModule;
import com.example.todolist.Di.Modules.TasksRepositoryModule;
import com.example.todolist.Di.Modules.ViewModelModule;
import com.example.todolist.Main.ToastClass;
import com.example.todolist.Model.RemoteDataSource.Api_Interface;
import com.example.todolist.Model.Repositories.EntryRepository;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.Model.Repositories.TasksRepository;
import com.example.todolist.Utils.ScheduleNotification;
import com.example.todolist.Utils.SharedPreference;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AndroidInjectionModule.class, EntryModule.class, SharedPreferenceModule.class, BroadcastReceiverModule.class, ActivityModule.class, ScheduleNotificationModule.class, GroupsRepositoryModule.class, TasksRepositoryModule.class, DataBaseModule.class, RetrofitModule.class, ViewModelModule.class})
public interface AppComponent extends AndroidInjector<DiApplication> {

    ToastClass t();

    GroupsRepository getGroupsRepository();

    TasksRepository getTasksRepository();

    ScheduleNotification scheduleNotification();

    SharedPreference sharedPreference();

    EntryRepository entryRepository();

    @Component.Builder
    interface builder {

        AppComponent build();

        @BindsInstance
        builder setContext(Context context);

    }
}
