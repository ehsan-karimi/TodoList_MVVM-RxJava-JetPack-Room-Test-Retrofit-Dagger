package com.example.todolist.Model.RemoteDataSource;

import com.example.todolist.Model.Entities.Groups;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api_Interface {
    @GET("get_groups.php")
    Single<List<Groups>> get_GroupList();

    @POST("add_group.php")
    Single<String> add_Group(@Body JsonObject body);
}
