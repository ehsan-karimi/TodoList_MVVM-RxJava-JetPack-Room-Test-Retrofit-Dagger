package com.example.todolist.Model.LocalDataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.Model.Entities.Tasks;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tbl_tasks")
    LiveData<List<Tasks>> getAllTasks();

    @Query("SELECT * FROM tbl_tasks WHERE groupId = :gpId ORDER BY id DESC")
    LiveData<List<Tasks>> getTasks(long gpId);

//    @Query("SELECT * FROM tbl_groups ORDER BY id DESC LIMIT 1")
//    Single<Groups> getLastGroup();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTasksList(List<Tasks> tasks);

    @Insert()
    Completable insertTasks(Tasks tasks);

    @Update
    int updateTasks(Tasks tasks);

    @Delete
    int deleteTasks(Tasks tasks);
}
