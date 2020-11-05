package com.example.todolist.Network;

import com.example.todolist.Model.Groups;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api_Interface {
    @GET("get_groups.php")
    Single<List<Groups>> get_GroupList();

    @POST("?.php")
    Single<String> add_Group(@Body JsonObject body);
}
