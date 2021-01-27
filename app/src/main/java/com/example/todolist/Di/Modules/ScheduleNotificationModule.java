package com.example.todolist.Di.Modules;

import android.content.Context;

import com.example.todolist.Utils.ScheduleNotification;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ScheduleNotificationModule {

    @Singleton
    @Provides
    public ScheduleNotification provideScheduleNotification(Context context){
        return new ScheduleNotification(context);
    }

}
