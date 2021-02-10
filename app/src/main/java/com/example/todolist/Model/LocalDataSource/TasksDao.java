package com.example.todolist.Model.LocalDataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.Model.Entities.Groups;
import com.example.todolist.Model.Entities.Tasks;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tbl_tasks")
    LiveData<List<Tasks>> getAllTasks();

    @Query("SELECT * FROM tbl_tasks")
    Observable<List<Tasks>> getAllTasksRx();

    @Query("SELECT * FROM tbl_tasks WHERE groupId = :gpId ORDER BY id DESC")
    LiveData<List<Tasks>> getTasks(int gpId);

//    @Query("SELECT * FROM tbl_groups ORDER BY id DESC LIMIT 1")
//    Single<Groups> getLastGroup();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTasksList(List<Tasks> tasks);

    @Insert()
    Single<Long> insertTasks(Tasks tasks);

    @Update
    Completable updateTasks(Tasks tasks);

    @Update
    Completable updateGroups(Groups groups);

    @Delete
    int deleteTasks(Tasks tasks);

    @Query("SELECT * FROM tbl_groups WHERE id = :gpId")
    Single<Groups> getGroup(int gpId);
}
