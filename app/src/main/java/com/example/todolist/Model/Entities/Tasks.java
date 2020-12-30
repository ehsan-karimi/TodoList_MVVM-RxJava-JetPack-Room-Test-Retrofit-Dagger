package com.example.todolist.Model.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_tasks")
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long groupId;
    private String title;
    private String date;
    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
