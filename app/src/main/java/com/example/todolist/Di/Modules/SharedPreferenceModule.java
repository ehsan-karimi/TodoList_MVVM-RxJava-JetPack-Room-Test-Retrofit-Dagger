package com.example.todolist.Di.Modules;

import android.content.Context;

import com.example.todolist.Utils.ScheduleNotification;
import com.example.todolist.Utils.SharedPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferenceModule {
    @Singleton
    @Provides
    public SharedPreference provideSharedPreference(Context context){
        return new SharedPreference(context);
    }
}
