package com.example.todolist.Model.Repositories;

import com.example.todolist.Model.Entities.EntryResponse;
import com.example.todolist.Model.RemoteDataSource.Api_Interface;
import com.google.gson.JsonObject;

import io.reactivex.Single;

public class EntryRepository {

    private Api_Interface api_interface;

    public EntryRepository(Api_Interface api_interface) {
        this.api_interface = api_interface;
    }

    public Single<EntryResponse> entry(JsonObject body){
        return api_interface.entry(body);
    }
}
