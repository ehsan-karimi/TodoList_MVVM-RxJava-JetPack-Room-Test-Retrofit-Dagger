package com.example.todolist.Di.Modules;

import com.example.todolist.Utils.ScheduleAfterReboot;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BroadcastReceiverModule {
    @ContributesAndroidInjector
    abstract ScheduleAfterReboot provideScheduleAfterReboot();
}
