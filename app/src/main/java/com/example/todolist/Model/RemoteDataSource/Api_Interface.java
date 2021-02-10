package com.example.todolist.Model.RemoteDataSource;

import com.example.todolist.Model.Entities.EntryResponse;
import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api_Interface {
    @GET("get_groups.php")
    Single<List<Groups>> get_GroupList();

    @GET("get_tasks.php")
    Single<List<Tasks>> get_TasksList();

    @POST("add_group.php")
    Single<String> add_Group(@Body JsonObject body);

    @POST("add_task.php")
    Single<String> add_Task(@Body JsonObject body);

    @POST("entry.php")
    Single<EntryResponse> entry(@Body JsonObject body);
}
