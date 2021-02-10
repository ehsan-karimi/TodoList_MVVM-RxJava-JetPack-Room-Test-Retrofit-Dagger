package com.example.todolist.entry;

import androidx.lifecycle.ViewModel;

import com.example.todolist.Model.Entities.EntryResponse;
import com.example.todolist.Model.Repositories.EntryRepository;
import com.example.todolist.Utils.SharedPreference;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import io.reactivex.Single;

public class EntryViewModel extends ViewModel {

    @Inject
    public EntryRepository entryRepository;

    @Inject
    public SharedPreference sharedPreference;

    @Inject
    public EntryViewModel(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Single<EntryResponse> entry(JsonObject body){
        return entryRepository.entry(body);
    }

    public Boolean saveDataSharedPreferences(String key, String value) {
        return sharedPreference.save(key, value);
    }

    public String getDataSharedPreferences(String key) {
        return sharedPreference.get(key);
    }

}
