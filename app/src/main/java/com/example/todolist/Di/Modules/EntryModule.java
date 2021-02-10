package com.example.todolist.Di.Modules;

import com.example.todolist.Model.RemoteDataSource.Api_Interface;
import com.example.todolist.Model.Repositories.EntryRepository;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RetrofitModule.class})
public class EntryModule {
    @Provides
    public EntryRepository provideEntryRepository(Api_Interface api_interface){
        return new EntryRepository(api_interface);
    }
}
